package olomakovskyi.tube;

import olomakovskyi.tube.database.DataBaseServiceTube;
import olomakovskyi.tube.database.DatabaseConnection;

import java.io.*;
import java.sql.SQLException;
import java.util.*;


/**
 * Created by olomakovskyi on 8/26/2014.
 */
public class TestTube {

    private static Deque<String> tubeContent = new ArrayDeque<>();
    private static Map<String, Map<String, String>> rulesMap = new HashMap<>();
    private static boolean isAddingToEnd = true;
    private static TubePropertiesHolder tubePropertiesHolder;
    private static DataBaseServiceTube databaseService;

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        tubePropertiesHolder = new TubePropertiesHolder();
        databaseService = new DataBaseServiceTube(tubePropertiesHolder.getDriverSybase(), tubePropertiesHolder.getConnectionURL(), tubePropertiesHolder.getConnectionLogin(), tubePropertiesHolder.getConnectionPassword(),
                tubePropertiesHolder.getSQLPreparedInsert(), tubePropertiesHolder.getSQLPreparedDelete(), tubePropertiesHolder.getSQLSelect());
        Set<String> content;
        try {
            content = DatabaseConnection.getQueryResult(tubePropertiesHolder.getSQLSelect());
        } catch (Exception e) {
            System.out.println("Rules weren't read from database and will be read from file.");
            content = readOldContent("rules.txt");
        }

        try {
            addRulesFromSet(content);
        } catch (RuleException e) {
            System.out.println(e);
        }

//        writeRule("A", "B", "C");
//        writeRule("A", "C", "D");
//        writeRule("B", "D", "E");
//        writeRule("C", "E", "D");
//        writeRule("C", "D", NULL);

        System.out.println("Enter 'beginning' to add ingredients to beginning, 'end' to add ingredients to end(by default) and 'mix' to view result of reactions.");
        Scanner scanner = new Scanner(System.in);
        String inString = scanner.nextLine().toUpperCase();

        while (!"EXIT".equals(inString)) {
            switch (inString) {
                case "BEGINNING":
                    isAddingToEnd = false;
                    break;
                case "END":
                    isAddingToEnd = true;
                    break;
                default:
                    mixing(inString);
            }

            inString = scanner.nextLine().toUpperCase();
        }

        scanner.close();

        for (String element : tubeContent) {
            System.out.println(element);
        }
    }

    public static void mixing(String inValue) {
        Map<String, String> inValueRule = rulesMap.get(inValue);
        if (!(TestTube.tubeContent.size() == 0 || inValueRule == null)) {
            if (isAddingToEnd) {
                String lastTubeElement = tubeContent.getLast();
                if (inValueRule.containsKey(lastTubeElement)) {
                    tubeContent.removeLast();
                    String ruleResult = inValueRule.get(lastTubeElement);
                    if (ruleResult != null) {
                        tubeContent.addLast(ruleResult);
                    }
                    return;
                }
            } else {
                String firstTubeElement = tubeContent.getFirst();
                if (inValueRule.containsKey(firstTubeElement)) {
                    tubeContent.removeFirst();
                    String ruleResult = inValueRule.get(firstTubeElement);
                    if (ruleResult != null) {
                        tubeContent.addFirst(ruleResult);
                    }
                    return;
                }
            }
        }


        tubeContent.add(inValue);
    }

    public static void addRulesFromSet(Set<String> content) throws RuleException {
        String[] resultArray;

        for (String element : content) {
            resultArray = element.split(" ");
            if (!"+".equals(resultArray[1]) || !"=".equals(resultArray[3])){
                throw new RuleException();
            }
            addRule(resultArray[0], resultArray[2], resultArray[4]);
        }

    }

    public static Set<String> readOldContent(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            Set<String> rulesList = new HashSet<>();

            try {
                BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
                try {
                    String s;
                    while ((s = in.readLine()) != null) {
                        rulesList.add(s + "\n");
                    }
                } finally {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rulesList;
        } else {
            return null;
        }
    }

    public static void writeRule(String ingredient1, String ingredient2, String result) {
        File rulesFile = new File("rules.txt");
        String rule = String.format("%s + %s = %s%s", ingredient1, ingredient2, result, "\n");
        Set<String> oldContent = new HashSet<>();
        try {
            if (!rulesFile.exists()) {
                rulesFile.createNewFile();
            } else {
                oldContent = readOldContent("rules.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        oldContent.add(rule);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(rulesFile.getAbsoluteFile());
            for (String element : oldContent) {
                writer.write(element);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Rule won't be written to file.");
        }

        try {
            databaseService.executePreparedInsert(rule);
            DatabaseConnection.executeStatement(String.format(tubePropertiesHolder.getSQLInsert(), rule));
        } catch (Exception e) {
            System.out.println("Rule won't be inserted to database.");
        }

    }

    public static void addRule(String ingredient1, String ingredient2, String result) {
        if (!rulesMap.containsKey(ingredient1)) {
            Map<String, String> innerMap1 = new HashMap<>();
            innerMap1.put(ingredient2, result);
            rulesMap.put(ingredient1, innerMap1);
        } else if (!rulesMap.get(ingredient1).containsKey(ingredient2)) {
            rulesMap.get(ingredient1).put(ingredient2, result);
        } else {
            return;
        }
        addRule(ingredient2, ingredient1, result);
    }

    public static void removeRule(String ingredient1, String ingredient2) {
        if (rulesMap.containsKey(ingredient1) && rulesMap.get(ingredient1).containsKey(ingredient2)) {
            if (rulesMap.get(ingredient1).size() == 1) {
                rulesMap.remove(ingredient1);
            } else {
                rulesMap.get(ingredient1).remove(ingredient2);
            }
        } else {
            return;
        }
        removeRule(ingredient2, ingredient1);
    }

    public static void removeRuleFromFileAndDB(String ingredient1, String ingredient2, String result) {
        File rulesFile = new File("rules.txt");
        String rule = String.format("%s + %s = %s", ingredient1, ingredient2, result);
        Set<String> content = readOldContent("rules.txt");
        content.remove(rule);
        try {
            PrintWriter writer = new PrintWriter(rulesFile.getAbsoluteFile());
            for (String element : content) {
                writer.write(element);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            databaseService.executePreparedDelete(rule);
            DatabaseConnection.executeStatement(String.format(tubePropertiesHolder.getSQLDelete(), rule));
        } catch (Exception e) {
            System.out.println("Rule won't be removed from DB");
        }
    }
}
