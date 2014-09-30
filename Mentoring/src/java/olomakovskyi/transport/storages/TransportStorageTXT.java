package olomakovskyi.transport.storages;

import olomakovskyi.transport.TransportPropertiesHolder;
import olomakovskyi.transport.TransportStorageManager;
import olomakovskyi.transport.classes.Transport;
import olomakovskyi.transport.classes.TransportManager;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 9/7/2014.
 */
public class TransportStorageTXT implements TransportStorage {
    private static TransportPropertiesHolder propertiesHolder = null;

    @Override
    public void addTransport(int id) throws IOException {
        Transport newTransport = TransportManager.createTransport(id);
        TransportStorageManager.storedCars.put(newTransport.getId(), newTransport);
        String newString = TransportManager.convertTransportToString(newTransport, propertiesHolder.getTextSeparator());

        //getting of all existing pojo from file
        Map<Integer, String> stringMap = getAllFromFile();
        stringMap.put(id, newString);

        writeAllToFile(stringMap);
    }

    @Override
    public void updateTransport(int id) throws IOException {
        Transport targetTransport = TransportManager.updateTransport(TransportStorageManager.storedCars.get(id));
        TransportStorageManager.storedCars.put(id, targetTransport);

        Map<Integer, String> stringMap = getAllFromFile();
        stringMap.put(id, TransportManager.convertTransportToString(targetTransport, propertiesHolder.getTextSeparator()));

        writeAllToFile(stringMap);
    }

    @Override
    public void deleteTransport(int id) throws IOException {
        Map<Integer, String> stringMap = getAllFromFile();
        stringMap.remove(id);
        TransportStorageManager.storedCars.remove(id);
        writeAllToFile(stringMap);
    }

    @Override
    public Map<Integer, Transport> getAllTransport() throws IOException {
        Map<Integer, Transport> resultMap = new HashMap<>();
        Map<Integer, String> strings = getAllFromFile();

        for (Map.Entry<Integer, String> entry : strings.entrySet()) {
            resultMap.put(entry.getKey(), TransportManager.convertStringToTransport(entry.getValue(), propertiesHolder.getTextSeparator()));
        }

        return resultMap;
    }

    public Map<Integer, String> getAllFromFile() throws IOException {
        propertiesHolder = new TransportPropertiesHolder();
        Map<Integer, String> resultMap = new HashMap<>();

        File transportFile = new File(propertiesHolder.getTextFilename());
        if (transportFile.exists()) {
            String s;
            String id;

            BufferedReader in = new BufferedReader(new FileReader(transportFile.getAbsoluteFile()));

            while ((s = in.readLine()) != null) {
                id = s.substring(0, s.indexOf(propertiesHolder.getTextSeparator()));
                resultMap.put(Integer.parseInt(id), s + "\n");
            }
        }
        return resultMap;
    }

    public void writeAllToFile(Map<Integer, String> inMap) throws IOException {
        File transportFile = new File(propertiesHolder.getTextFilename());
        if (!transportFile.exists()) {
            transportFile.createNewFile();
        }

        PrintWriter writer = new PrintWriter(transportFile.getAbsoluteFile());
        for (Map.Entry<Integer, String> entry : inMap.entrySet()) {
            writer.write(entry.getValue());
        }
        writer.close();
    }
}
