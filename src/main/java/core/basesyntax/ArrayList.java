package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_MULTIPLICATION_NUMBER = 1.5;
    private T[] elementsData;
    private int capacity;
    private int size;

    public ArrayList() {
        //noinspection unchecked
        this.elementsData = (T[]) new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            resizeArray();
        }
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == capacity) {
            resizeArray();
        }
        checkIndexForAdd(index);
        System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
        elementsData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = index(element);
        if (index == -1) {
            throw new NoSuchElementException("No such element: " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        double newCapacity = capacity * DEFAULT_MULTIPLICATION_NUMBER;
        this.capacity = (int) newCapacity;
        //noinspection unchecked
        T[] newElementsData = (T[]) new Object[capacity];
        System.arraycopy(elementsData, 0, newElementsData, 0, size);
        elementsData = newElementsData;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " doesn't found");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " doesn't found");
        }
    }

    private int index(T element) {
        for (int i = 0; i < size(); i++) {
            if (elementsData[i] == element || elementsData[i] != null
                    && elementsData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
