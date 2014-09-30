package olomakovskyi.tube.database;

import java.sql.*;

/**
 * Created by olomakovskyi on 9/2/2014.
 */
public class DataBaseServiceTube {
    private final PreparedStatement insertStatement;
    private final PreparedStatement deleteStatement;
    private final PreparedStatement selectStatement;

    public DataBaseServiceTube(String driverName, String databaseURL, String databaseLogin, String databasePassword, String insertStatement, String deleteStatement,
                               String selectStatement) throws SQLException, ClassNotFoundException {
        Class.forName(driverName);
        //connection to server
        Connection conn = DriverManager.getConnection(databaseURL, databaseLogin, databasePassword);
        this.insertStatement = conn.prepareStatement(insertStatement);
        this.deleteStatement = conn.prepareStatement(deleteStatement);
        this.selectStatement = conn.prepareStatement(selectStatement);
    }

    public void executePreparedInsert(String rule) throws SQLException {
        insertStatement.setString(1, rule);
        insertStatement.execute();
    }

    public void executePreparedDelete(String rule) throws SQLException {
        deleteStatement.setString(1, rule);
        deleteStatement.execute();
    }

    public ResultSet executePreparedSelect() throws SQLException {
        return selectStatement.executeQuery();
    }
}
