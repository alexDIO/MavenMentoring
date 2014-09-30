package olomakovskyi.transport.storages;

import olomakovskyi.transport.TransportStorageManager;
import olomakovskyi.transport.classes.Transport;
import olomakovskyi.transport.classes.TransportManager;
import olomakovskyi.transport.classes.TransportPojo;

import java.io.IOException;

/**
 * Created by olomakovskyi on 9/17/2014.
 */
public abstract class TransportStorageDBAbstract implements TransportStorage {
    @Override
    public void addTransport(int id) throws IOException {
        Transport newTransport = TransportManager.createTransport(id);
        TransportStorageManager.storedCars.put(newTransport.getId(), newTransport);
        TransportPojo newPojo = TransportManager.convertTransportToPojo(newTransport);

        writePojo(newPojo);
    }

    @Override
    public void updateTransport(int id) throws IOException {
        Transport targetTransport = TransportManager.updateTransport(TransportStorageManager.storedCars.get(id));
        TransportStorageManager.storedCars.put(id, targetTransport);

        deleteTransport(id);

        writePojo(TransportManager.convertTransportToPojo(targetTransport));
    }

    protected abstract void writePojo(TransportPojo inPojo);
}
