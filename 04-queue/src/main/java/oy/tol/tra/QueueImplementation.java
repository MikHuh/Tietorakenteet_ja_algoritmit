package oy.tol.tra;

import java.lang.reflect.Array;

public class QueueImplementation<E> implements QueueInterface<E> {

    private int size = 0, tail = 0, head = 0, capacity = 0;
    private E[] itemArray;
    private Class<E> Class;

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int count() {
        return size;
    }

    @Override
    public E dequeue() throws QueueIsEmptyException {

        if (empty()) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        E x = itemArray[head];
        itemArray[head] = null;
        head++;
        size--;
        if (head >= capacity) {
            head = 0;
        }
        return x;
    }

    @Override
    public E element() throws QueueIsEmptyException {
        if (count() <= 0) {
            throw new QueueIsEmptyException("Queue is empty");
        }
        return itemArray[head];
    }

    @Override
    public boolean empty() {
        if (count() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {

        if (element == null) {
            throw new NullPointerException("null");
        }

        if (size == capacity) {
            int newcapacity = capacity * 2;
            E[] newItemArray = (E[]) Array.newInstance(Class, newcapacity);

            int i = head;
            int j = 0;
            int k = 0;
            for (i = head; i < capacity; i++) {
                newItemArray[j] = itemArray[i];
                j++;
            }
            for (k = 0; k < tail; k++) {
                newItemArray[j] = itemArray[k];
                j++;
            }
            tail = capacity;
            head = 0;
            capacity = newcapacity;
            itemArray = newItemArray;
            if (capacity != newcapacity) {
                throw new QueueAllocationException("Allocation needed");
            }
        }
        if (tail >= capacity && tail >= head) {
            tail = 0;
        }
        itemArray[tail++] = element;
        size++;
    }

    @Override
    public void init(Class<E> elementClass, int size) throws QueueAllocationException {

        if (size < 2) {
            throw new QueueAllocationException("size should be greater than two");
        }

        try {
            Class = elementClass;
            capacity = size;
            itemArray = (E[]) Array.newInstance(elementClass, size);
        } catch (Exception e) {
            throw new QueueAllocationException(e.getMessage());
        }
    }

    @Override
    public void reset() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i <= size; i++) {
            dequeue();
        }
    }

    @Override
    public String toString() {
        String s = "[";
        if (empty()) {
            s = "[]";
            return s;
        }
        if (size == 1) {
            s = s + itemArray[head] + "]";
            return s;
        }
        int i = head;
        while (itemArray[i] != null) {
            if (i != tail - 1) {
                s = s + itemArray[i] + ", ";
            } else {
                s = s + itemArray[i];
            }
            if (i > capacity) {
                i = 0;
            }
            i++;
        }
        s = s + "]";
        return s;
    }
}
