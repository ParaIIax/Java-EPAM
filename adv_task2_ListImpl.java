package ua.advanced.task2;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ListImpl implements List {
    static class Node {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node(Object data) {
            this.data = data;
        }
    }

    private Node first;
    private int size = 0;

    @Override
    public void addFirst(Object element) {
        Node newElem = new Node(element);
        newElem.next = first;
        first = newElem;
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node newElem = new Node(element);
        Node cur = first;
        if(first == null) {
            first = newElem;
        }
        else {
            while (cur.next != null)
                cur = cur.next;
            cur.next = newElem;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        if(first == null)
            return;
        first = first.next;
        size--;
    }

    @Override
    public void removeLast() {
        if(first == null)
            return;
        Node cur = first;
        while(cur.next.next != null) {
            cur = cur.next;
        }
        cur.next = null;
        size--;
    }

    @Override
    public Object getFirst() {
        return first.data;
    }

    @Override
    public Object getLast() {
        Node cur = first;
        Node cur2 = cur;
        while(cur != null) {
            cur2 = cur;
            cur = cur.next;
        }
        return cur2.data;
    }

    @Override
    public Object search(Object element) {
        Node cur = first;
        while(cur != null) {
            if (cur.data.equals(element))
                return cur.data;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if(first == null)
            return false;
        if(first.data.equals(element)) {
            removeFirst();
            return true;
        }
        Node cur = first;
        Node cur2 = cur;
        while(cur != null) {
            if (cur.data.equals(element)) {
                cur2.next = cur2.next.next;
                size--;
                return true;
            }
            cur2 = cur;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "[ ";
        Node cur = first;
        while(cur != null) {
            str += cur.data + " ";
            cur = cur.next;
        }
        str += "]";
        return str;
    }

    @Override
    public void clear() {
        while(first != null)
            removeFirst();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }


    class IteratorImpl implements Iterator<Object> {
        private Node iterator = new Node(null, first);

        @Override
        public boolean hasNext() {
            return iterator.next != null;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                iterator = iterator.next;
                return iterator.data;
            }
            else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            ListImpl.this.remove(iterator.data);
        }

    }


    public static void main(String[] args) throws IOException {
        Document document = DataAccess.getDocument("city_map.xml");
        ListImpl list = new ListImpl();
        NodeList nodeList = document.getElementsByTagName("City");
        for (int i = 0; i < nodeList.getLength(); i++)
            list.addLast(DataAccess.getCity(nodeList.item(i)));
        System.out.println("After reading from XML:\n" + list + "\n");

        City paris = new City(5, "Paris");
        list.addLast(paris);
        list.addLast(new City(6, "New York"));
        list.addFirst(new City(7, "Los Angeles"));
        list.addFirst(new City(8, "Rome"));
        list.addLast(new City(9, "Washington"));
        list.addLast(new City(10, "Milan"));
        System.out.println("After adding new cities (some to the first place):\n" + list + "\n");

        System.out.println("First element: " + list.getFirst() + "\n");
        System.out.println("Last element: " + list.getLast() + "\n");
        list.removeFirst();
        System.out.println("After removing the first element:\n" + list + "\n");
        list.removeLast();
        System.out.println("After removing the last element:\n" + list + "\n");

        System.out.println("Size: " + list.size + "\n");

        System.out.println("Search: " + list.search(paris) + "\n");

        System.out.println("Specified element removed?: " + list.remove(paris) + "\n");
        System.out.println("After removing the specified element:\n" + list + "\n");

        var iterator = list.iterator();
        while (iterator.hasNext()){
            City city = (City) iterator.next();
            if(city.getCityName().equals("Philadelphia")){
                iterator.remove();
            }
        }
        System.out.println("After removing using iterator:\n" + list + "\n");

        DataAccess.writeCities(list.iterator(), "cityMap.json");
    }

}
