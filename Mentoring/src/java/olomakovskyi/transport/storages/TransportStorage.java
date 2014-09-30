package olomakovskyi.transport.storages;

import olomakovskyi.transport.classes.Transport;

import java.io.IOException;
import java.util.Map;

/**
 * Created by olomakovskyi on 9/3/2014.
 */
public interface TransportStorage {
    public void addTransport(int id) throws IOException;

    public void updateTransport(int id) throws IOException;

    public void deleteTransport(int id) throws IOException;

    public Map<Integer, Transport> getAllTransport() throws IOException;
}
