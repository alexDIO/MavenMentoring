package olomakovskyi.transport.storages;

import olomakovskyi.transport.TransportPropertiesHolder;
import olomakovskyi.transport.TransportStorageManager;
import olomakovskyi.transport.classes.Transport;
import olomakovskyi.transport.classes.TransportManager;
import olomakovskyi.transport.classes.TransportPojo;
import olomakovskyi.tube.TubePropertiesHolder;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by olomakovskyi on 9/17/2014.
 */
public class TransportStorageDB extends TransportStorageDBAbstract {
    private static TransportPropertiesHolder propertiesHolder = null;

    @Override
    public void deleteTransport(int id) throws IOException {
        TransportStorageManager.storedCars.remove(id);
        String deleteStatement = String.format(propertiesHolder.getSqlDelete(), id);

        try {
            executeStatement(deleteStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, Transport> getAllTransport() throws IOException {
        propertiesHolder = new TransportPropertiesHolder();
        Map<Integer, Transport> resultMap = new HashMap<>();

        try {
            Class.forName(propertiesHolder.getDriverSybase());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(propertiesHolder.getConnectionUrl(), propertiesHolder.getConnectionLogin(), propertiesHolder.getConnectionPassword());
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(propertiesHolder.getSqlSelect());

            while (rs.next()) {
                int id = rs.getInt(1);
                TransportPojo newPojo = new TransportPojo(id,rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getInt(9));
                resultMap.put(id, TransportManager.convertPojoToTransport(newPojo));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    protected void writePojo(TransportPojo inPojo) {
        String arguments = TransportManager.convertTransportToString(TransportManager.convertPojoToTransport(inPojo),",", "'");
        String insertStatement = String.format(propertiesHolder.getSqlInsert(),arguments);
        try {
            executeStatement(insertStatement);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeStatement(String query) throws ClassNotFoundException, SQLException {
        //using of sybase driver
        Class.forName(propertiesHolder.getDriverSybase());
        //connection to server
        Connection conn = DriverManager.getConnection(propertiesHolder.getConnectionUrl(), propertiesHolder.getConnectionLogin(), propertiesHolder.getConnectionPassword());
        Statement statement = conn.createStatement();
        statement.executeUpdate(query);
    }
}
