package ua.advanced.task2;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    private static class Node {
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

    private Node head;

    @Override
    public void enqueue(Object element) {
        Node newElem = new Node(element);
        Node cur = head;
        if(head == null) {
            head = newElem;
        }
        else {
            while (cur.next != null)
                cur = cur.next;
            cur.next = newElem;
        }
    }

    @Override
    public Object dequeue() {
        if(head == null)
            return null;
        Node head1 = head;
        head = head.next;
        return head1.data;
    }

    @Override
    public Object top() {
        return head.data;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public int size() {
        int size = 0;
        Node cur = head;
        while (cur != null) {
            cur = cur.next;
            ++size;
        }
        return size;
    }

    @Override
    public String toString() {
        String str = "[ ";
        Node cur = head;
        while (cur != null) {
            str += cur.data + " ";
            cur = cur.next;
        }
        str += "]";
        return str;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }


    class IteratorImpl implements Iterator<Object> {
        private Node iterator = new Node(null, head);

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
            if (iterator.data.equals(head.data))
                QueueImpl.this.dequeue();
            Node cur = head;
            while (cur.next != null) {
                if (cur.next.data.equals(iterator.data)) {
                    cur.next = cur.next.next;
                    return;
                }
                cur = cur.next;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        Document document = DataAccess.getDocument("city_map.xml");
        QueueImpl queue = new QueueImpl();
        NodeList nodeList = document.getElementsByTagName("City");
        for (int i = 0; i < nodeList.getLength(); i++)
            queue.enqueue(DataAccess.getCity(nodeList.item(i)));
        System.out.println("After reading from XML:\n" + queue + "\n");

        City paris = new City(5, "Paris");
        queue.enqueue(paris);
        queue.enqueue(new City(6, "New York"));
        queue.enqueue(new City(7, "Los Angeles"));
        queue.enqueue(new City(8, "Rome"));
        queue.enqueue(new City(9, "Washington"));
        queue.enqueue(new City(10, "Milan"));
        System.out.println("After adding new cities:\n" + queue + "\n");

        queue.dequeue();
        System.out.println("After removing the top element:\n" + queue + "\n");

        System.out.println("Queue top: " + queue.top() + "\n");
        System.out.println("Size: " + queue.size() + "\n");

        var iterator = queue.iterator();
        while(iterator.hasNext()){
            City city = (City) iterator.next();
            if(city.getCityName().equals("Rome")){
                iterator.remove();
            }
        }
        System.out.println("After removing using iterator:\n" + queue + "\n");

        DataAccess.writeCities(queue.iterator(), "cityMap.json");
    }

}
