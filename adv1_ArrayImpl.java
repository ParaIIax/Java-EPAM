package ua.advanced.task1;

import ua.advanced.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
  
    public static void main(String[] args) {
        ArrayImpl array = new ArrayImpl();
        array.add("J");
        array.add("Q");
        array.add("P");
        array.add("A");
        array.add("L");
        array.add("M");
        System.out.println("After adding elements: " + array);

        array.remove(1);
        System.out.println("After removing element with index 1: " + array);

        System.out.println("Element with index 1: " + array.get(1));

        array.set(0, "E");
        System.out.println("After changing element with index 3: " + array);

        System.out.println("Size: " + array.size());

        System.out.println("Index of element \"M\": " + array.indexOf("M"));

        var iterator = array.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (element.equals("L")) {
                iterator.remove();
            }
        }
        System.out.println("After using iterator: " + array);

        array.clear();
        System.out.println("After clearing array: " + array);
    }

    private Object[] objects;
    private int size = 1;

    @Override
    public void add(Object element) {
        Object[] ob = new Object[size];
        if (!isEmpty()) {
            for (int i = 0; objects.length > i; ++i)
                ob[i] = objects[i];
        }
        objects = new Object[size];
        for (int i = 0; ob.length > i; ++i)
            objects[i] = ob[i];
        int a = size - 1;
        for (; a < objects.length; ++a) {
            if (objects[a] == null)
                objects[a] = element;
        }
        ++size;
    }

    @Override
    public void set(int index, Object element) {
        if (!isEmpty())
            objects[index] = element;
    }

    @Override
    public Object get(int index) {
        if (!isEmpty())
            return objects[index];
        return -1;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i].equals(element))
                return i;
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        size--;
        Object[] ob = new Object[size - 1];
        if (!isEmpty()) {
            int a = 0;
            for (int i = 0; objects.length - 1 > i; ++i) {
                if (index == i) {
                    a = i + 1;
                }
                ob[i] = objects[a];
                ++a;
            }
        }
        objects = new Object[ob.length];
        for (int i = 0; ob.length > i; ++i)
            objects[i] = ob[i];
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            for (int i = 0; i < objects.length; ++i) {
                remove(i);
                --i;
            }
        }
    }

    @Override
    public int size() {
        return objects.length;
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "Empty";
        String str = "";
        for(int i = 0; i < objects.length; ++i) {
            str += objects[i] + " ";
        }
        return str;
    }

    public boolean isEmpty() {
        return (size <= 1);
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }


    class IteratorImpl implements Iterator<Object> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < objects.length;
        }

        @Override
        public Object next() {
            if (hasNext())
                return objects[index++];
            else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            ArrayImpl.this.remove(--index);
        }

    }
  

}
