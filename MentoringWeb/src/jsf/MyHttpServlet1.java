package jsf; /**
 * Created by olomakovskyi on 9/19/2014.
 */

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MyHttpServlet1 extends HttpServlet{
    private String mark = "";
    private String energySource;
    private String transmission = "manual";
    private List<String> colors = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(generateHtml());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mark = req.getParameter("mark");
        energySource = req.getParameterValues("Energy_source")[0];
        String[] colorsArray = req.getParameterValues("colors");
        transmission = req.getParameter("Transmission");
        if (colorsArray != null) {
            colors = Arrays.asList(colorsArray);
        }
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(generateHtml());
        resp.getWriter().println("Info was sent");
    }

    private String compareStrings(String variable, String elemValue, String result){

        if (elemValue.equals(variable)){
            return result;
        } else {
            return "";
        }
    }

    private String compareStrings(List<String> variable, String elem, String result){
        if (variable.contains(elem)){
            return result;
        } else {
            return "";
        }
    }

    private String generateHtml(){
        return  "<!DOCTYPE html>\n" +
                "<html xmlns=\"http://www.w3.org/1999/html\">\n" +
                "<head lang=\"en\">\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Transport</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form method=\"post\">\n" +
                "        <table>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <span>Марка</span>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"text\" name=\"mark\" value=\""+mark+"\"/>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <span>Источник инергии</span>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <select name=\"Energy_source\" >\n" +
                "                        <option value=\"gas\" " + compareStrings(energySource,"gas","selected") + ">gas</option>\n" +
                "                        <option value=\"petrol\" " + compareStrings(energySource,"petrol","selected") + ">petrol</option>\n" +
                "                        <option value=\"electricity\" " + compareStrings(energySource,"electricity","selected") + ">electricity</option>\n" +
                "                    </select>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <span>Коробка передач</span>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                        <input type=\"radio\" name=\"Transmission\" value=\"manual\" " + compareStrings(transmission,"manual","checked") + "/>Manual\n" +
                "                        <input type=\"radio\" name=\"Transmission\" value=\"automate\" " + compareStrings(transmission,"automate","checked") + "/>Automate\n" +
                "                   </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <span>Цвета</span>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"Black\" " + compareStrings(colors, "Black", "checked") +">Black\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"White\" " + compareStrings(colors, "White", "checked") +">White\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"Red\" " + compareStrings(colors, "Red", "checked") +">Red\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"Green\" " + compareStrings(colors, "Green", "checked") +">Green\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"Blue\" " + compareStrings(colors, "Blue", "checked") +">Blue\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"Yellow\" " + compareStrings(colors, "Yellow", "checked") +">Yellow\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "        <input type=\"submit\" value=\"send\"/>\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>";
    }
}
/**
 * 1) чтобы оставался источник энергии
 * 2) чтобы при нажатии кнопки выводилось сообщение
 * 3) добавить на страницу поля с радио батонами и чекбоксами*/