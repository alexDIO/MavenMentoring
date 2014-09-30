package olomakovskyi.collections.mapsandsets;

import olomakovskyi.collections.lists.CustomList;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by olomakovskyi on 8/28/2014.
 */
public class CustomHashMap<K, V> implements Map<K, V> {
    CustomList<CustomEntry<K, V>>[] entries;
    int size = 0;

    public CustomHashMap(int length) {
        entries = (CustomList<CustomEntry<K, V>>[]) new CustomList[length];

//        for (int i = 0; i < entries.length; i++){
//            entries[i] = new CustomList<CustomEntry<K,V>>();
//        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        int keyHash = key.hashCode();
        int index = Math.abs(keyHash % entries.length);
        if (entries[index] != null) {
            for (int i = 0; i < entries[index].size(); i++) {
                if (key.equals(entries[index].get(i).getKey())) {
                    entries[index].get(i).setValue(value);
                    return value;
                }
            }
        } else {
            entries[index] = new CustomList<CustomEntry<K, V>>(1);
        }
        entries[index].add(new CustomEntry<K, V>(key, value));
        return value;

    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
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

class CustomEntry<K, V> {
    K key;
    V value;

    public CustomEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}