/**
 * Created by olomakovskyi on 9/19/2014.
 */

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class MyHttpServlet1 extends HttpServlet{
    private String mark;
    private String energySource;
    private String transmission;
    private List<String> colors;

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
        transmission = req.getParameterValues("Transmission")[0];
        if (colorsArray.length > 0) {
            colors = Arrays.asList(colorsArray);
        }
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(generateHtml());
        resp.getWriter().println("Info was sent");
    }

    private String generateHtml(){
        return  "<!DOCTYPE html>\n" +
                "<html xmlns=\"http://www.w3.org/1999/html\">\n" +
                "<head lang=\"en\">\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Transport</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form method=\"get\">\n" +
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
                "                    <select name=\"Energy_source\" value=\""+energySource+"\" >\n" +
                "                        <option value=\"g\">gas</option>\n" +
                "                        <option value=\"p\">petrol</option>\n" +
                "                        <option value=\"e\">electricity</option>\n" +
                "                    </select>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <span>Коробка передач</span>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <input type=\"radio\" name=\"Transmission\" value=\"manual\" checked/>Manual\n" +
                "                    <input type=\"radio\" name=\"Transmission\" value=\"automate\" checked/>Automate\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>\n" +
                "                    <span>Цвета</span>\n" +
                "                </td>\n" +
                "                <td>\n" +
                "                    <form action=\"multiplevalues.do\" method=\"post\">\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"Black\">Black\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"White\">White\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"Red\">Red\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"Green\">Green\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"Blue\">Blue\n" +
                "                        <input type=\"checkbox\" name=\"colors\" value=\"Yellow\">Yellow\n" +
                "                    </form>\n" +
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