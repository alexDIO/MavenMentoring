package olomakovskyi.tube;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by olomakovskyi on 9/2/2014.
 */
public class TubePropertiesHolder {
    private final static String PROPERTIES_FILE_NAME = "tube.properties";
    private final static String DRIVER_SYBASE_NAME = "driver.sybase";
    private final static String CONNECTION_URL_NAME = "connection.url";
    private final static String CONNECTION_LOGIN_NAME = "connection.login";
    private final static String CONNECTION_PASSWORD_NAME = "connection.password";
    private final static String SQL_INSERT_NAME = "sql.insert";
    private final static String SQL_DELETE_NAME = "sql.delete";
    private final static String SQL_SELECT_NAME = "sql.select";
    private final static String SQL_PREPARED_INSERT_NAME = "sql.prepared.insert";
    private final static String SQL_PREPARED_DELETE_NAME = "sql.prepared.delete";

    private final Properties tubeProperties;

    public TubePropertiesHolder() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
        tubeProperties = new Properties();
        tubeProperties.load(stream);
    }

    public String getDriverSybase(){
        return String.valueOf(tubeProperties.get(DRIVER_SYBASE_NAME));
    }

    public String getConnectionURL(){
        return String.valueOf(tubeProperties.getProperty(CONNECTION_URL_NAME));
    }

    public String getConnectionLogin(){
        return String.valueOf(tubeProperties.getProperty(CONNECTION_LOGIN_NAME));
    }

    public String getConnectionPassword(){
        return String.valueOf(tubeProperties.getProperty(CONNECTION_PASSWORD_NAME));
    }

    public String getSQLInsert(){
        return String.valueOf(tubeProperties.getProperty(SQL_INSERT_NAME));
    }

    public String getSQLDelete(){
        return String.valueOf(tubeProperties.getProperty(SQL_DELETE_NAME));
    }

    public String getSQLSelect(){
        return String.valueOf(tubeProperties.getProperty(SQL_SELECT_NAME));
    }

    public String getSQLPreparedInsert(){
        return String.valueOf(tubeProperties.getProperty(SQL_PREPARED_INSERT_NAME));
    }

    public String getSQLPreparedDelete(){
        return String.valueOf(tubeProperties.getProperty(SQL_PREPARED_DELETE_NAME));
    }
}
