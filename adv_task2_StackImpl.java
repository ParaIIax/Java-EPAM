package ua.advanced.task2;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    class Node {
        Object data;
        Node prev;

        Node(Object data) {
            this.data = data;
        }

        Node(Object data, Node prev) {
            this.data = data;
            this.prev = prev;
        }

    }

    private Node top;

    @Override
    public void push(Object element) {
        Node node = new Node(element);
        node.prev = top;
        top = node;
    }

    @Override
    public Object pop() {
        if (top == null)
            return null;
        Object tmp = top.data;
        top = top.prev;
        return tmp;
    }

    @Override
    public Object top() {
        return top.data;
    }

    @Override
    public void clear() {
        top = null;
    }

    @Override
    public int size() {
        int size = 0;
        Node cur = top;
        while (cur != null) {
            ++size;
            cur = cur.prev;
        }
        return size;
    }

    @Override
    public String toString() {
        String str = "[ ";
        Node cur = top;
        while (cur != null) {
            str += cur.data + " ";
            cur = cur.prev;
        }
        str += "]";
        return str;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }


    class IteratorImpl implements Iterator<Object> {
        private Node iterator = new Node(null, top);

        @Override
        public boolean hasNext() {
            return iterator.prev != null;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                iterator = iterator.prev;
                return iterator.data;
            }
            else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if (iterator.data.equals(top.data))
                StackImpl.this.pop();
            Node cur = top;
            while (cur.prev != null) {
                if (cur.prev.data.equals(iterator.data)) {
                    cur.prev = cur.prev.prev;
                    return;
                }
                cur = cur.prev;
            }
        }

    }


    public static void main(String[] args) throws IOException {
        Document document = DataAccess.getDocument("city_map.xml");
        StackImpl stack = new StackImpl();
        NodeList nodeList = document.getElementsByTagName("City");
        for (int i = 0; i < nodeList.getLength(); i++)
            stack.push(DataAccess.getCity(nodeList.item(i)));
        System.out.println("After reading from XML:\n" + stack + "\n");

        City paris = new City(5, "Paris");
        stack.push(paris);
        stack.push(new City(6, "New York"));
        stack.push(new City(7, "Los Angeles"));
        stack.push(new City(8, "Rome"));
        stack.push(new City(9, "Washington"));
        stack.push(new City(10, "Milan"));
        System.out.println("After adding new cities:\n" + stack + "\n");

        stack.pop();
        System.out.println("After removing the top element:\n" + stack + "\n");

        System.out.println("Stack top: " + stack.top() + "\n");
        System.out.println("Size: " + stack.size() + "\n");

        var iterator = stack.iterator();
        while(iterator.hasNext()){
            City city = (City) iterator.next();
            if(city.getCityName().equals("Los Angeles")){
                iterator.remove();
            }
        }
        System.out.println("After removing using iterator:\n" + stack + "\n");

        DataAccess.writeCities(stack.iterator(), "cityMap.json");
    }

}
