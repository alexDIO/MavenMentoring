package olomakovskyi.transport.storages;

import olomakovskyi.transport.TransportPropertiesHolder;

import java.io.IOException;

/**
 * Created by olomakovskyi on 9/4/2014.
 */
public class TransportStorageFactory {
    //pattern factory
    public static TransportStorage getStorage() throws IOException, TransportStorageException {
        TransportPropertiesHolder propertiesHolder = new TransportPropertiesHolder();
        switch (propertiesHolder.getSource().toLowerCase()) {
            case "csv":
                return new TransportStorageCSV();
            case "text":
                return new TransportStorageTXT();
            case "xls":
                return new TransportStorageXLS();
            case "db":
                return new TransportStorageDB();
            case "db.prepared":
                return new TransportStorageDBPrepared();
            default:
                throw new TransportStorageException();
        }
    }
}
