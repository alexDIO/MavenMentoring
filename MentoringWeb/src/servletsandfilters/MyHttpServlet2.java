package servletsandfilters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by olomakovskyi on 9/26/2014.
 */
public class MyHttpServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "text/plain");

        FileInputStream stream = new FileInputStream(new File("D:\\Private\\Linkin Park - The Hunting Party - 2014 - Mastered for iTunes - AAC 256 WEB\\folder.jpg"));
        byte[] data = new byte[stream.available()];
        while (stream.available() > 0) {
            int bytesCount = stream.read(data);
            resp.getOutputStream().write(data, 0, bytesCount);

        }


    }

    @Override
    public void destroy() {
        System.out.println("Http destroyed");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Http inited");
    }
}
