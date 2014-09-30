package olomakovskyi.collections.lists;

import java.util.*;

/**
 * Created by olomakovskyi on 8/21/2014.
 */
public class CustomList<E> implements List<E> {

    private E[] internalArray;
    private int listSize = 0;

    public CustomList(int initialCapacity) {
        this.internalArray = (E[]) new Object[initialCapacity];
    }


    public CustomList() {
        this.internalArray = (E[]) new Object[10];
    }

    public void sort() {
        Arrays.sort(internalArray, 0, listSize);
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return (listSize == 0);
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < internalArray.length; i++) {
            if (internalArray[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() { //skiped
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOfRange(internalArray, 0, listSize);
    }

    @Override
    public boolean add(E o) {
        internalArray[listSize] = o;
        listSize++;
        if (listSize == internalArray.length) {
            internalArray = Arrays.copyOf(internalArray, internalArray.length * 2);
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < internalArray.length; i++) {
            if (internalArray[i].equals(o)) {
                for (int j = i; j < listSize; j++) {
                    internalArray[j] = internalArray[j + 1];
                }
                internalArray[listSize] = null;
                listSize--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        if (index >= 0 && index < listSize) {
            return internalArray[index];
        } else {
            return null;
        }

    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {
        if (index >= 0 && index < listSize) {
            if (listSize >= internalArray.length - 1) {
                internalArray = Arrays.copyOf(internalArray, internalArray.length * 2);
            }
            System.arraycopy(internalArray, index, internalArray, index + 1, listSize - index);

            internalArray[index] = (E) element;
            listSize++;
        }
    }

    @Override
    public E remove(int index) {
        if (index >= 0 && index < listSize) {
            System.arraycopy(internalArray, index + 1, internalArray, index, listSize - index - 1);
            internalArray[listSize - 1] = null;
            listSize--;
        }
        return null;

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public <T> T[] toArray(T[] a) {

        return (T[]) internalArray;
    }
}