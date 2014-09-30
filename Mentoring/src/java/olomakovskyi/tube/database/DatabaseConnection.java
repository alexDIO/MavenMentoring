package olomakovskyi.tube.database;

import olomakovskyi.tube.TubePropertiesHolder;

import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by olomakovskyi on 9/1/2014.
 */
public class DatabaseConnection {

    private static TubePropertiesHolder tubePropertiesHolder;

    public static Set<String> getQueryResult(String query) throws ClassNotFoundException, SQLException, IOException {
        Set<String> resultList = new HashSet<>();
        tubePropertiesHolder = new TubePropertiesHolder();
        //using of sybase driver
        Class.forName(tubePropertiesHolder.getDriverSybase());
        //connection to server
        Connection conn = DriverManager.getConnection(tubePropertiesHolder.getConnectionURL(), tubePropertiesHolder.getConnectionLogin(), tubePropertiesHolder.getConnectionPassword());
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()){
            resultList.add(rs.getString(1));
        }

        return resultList;
    }

    public static void executeStatement(String query) throws ClassNotFoundException, SQLException {
        //using of sybase driver
        Class.forName(tubePropertiesHolder.getDriverSybase());
        //connection to server
        Connection conn = DriverManager.getConnection(tubePropertiesHolder.getConnectionURL(), tubePropertiesHolder.getConnectionLogin(), tubePropertiesHolder.getConnectionPassword());
        Statement statement = conn.createStatement();
        statement.executeQuery(query);
    }
}
