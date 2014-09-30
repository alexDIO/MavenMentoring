package olomakovskyi.collections.mapsandsets;

import java.util.*;

/**
 * Created by olomakovskyi on 8/22/2014.
 */
public class CustomMap<K, V> implements Map<K, V> {
    private ArrayList<K> keys;
    private ArrayList<V> values;

    private int mapSize = 0;

    public CustomMap() {
        keys = (ArrayList<K>) new ArrayList<>();
        values = (ArrayList<V>) new ArrayList<>();
    }

    @Override
    public int size() {
        return mapSize;
    }

    @Override
    public boolean isEmpty() {
        return (mapSize == 0);
    }

    @Override
    public boolean containsKey(Object key) {
        return (keys.contains(key));
    }

    @Override
    public boolean containsValue(Object value) {
        return (values.contains(value));
    }

    @Override
    public V get(Object key) {
        if (keys.contains(key)) {
            int index = keys.indexOf(key);
            return values.get(index);
        }
        return null;
    }

    @Override
    public V put(K key, V value) {

        if (keys.contains(key)) {
            values.set(keys.indexOf(key), value);
        } else {
            keys.add(key);
            values.add(value);
            mapSize++;
        }
        return value;
    }

    @Override
    public V remove(Object key) {
        if (keys.contains(key)) {
            int index = keys.indexOf(key);
            keys.remove(key);
            V removedValue = values.get(index);
            values.remove(index);
            mapSize--;
            return removedValue;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> pair : m.entrySet()) {
            if (keys.contains(pair.getKey())) {
                m.remove(pair.getKey());
            }
        }

        keys.addAll(m.keySet());
        values.addAll(m.values());
        mapSize = +m.size();
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
        mapSize = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keysSet = new HashSet<>();
        keysSet.addAll(keys);
        return keysSet;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}