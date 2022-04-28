package ua.advanced.task3;

import java.util.*;

public class PairStringList {
    static class Node {
        String data;
        Node next;

        Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node(String data) {
            this.data = data;
        }
    }

    private Node first;

    public void add(String element) {
        Node newElem = new Node(element);
        Node newElemCopy = new Node(element);
        Node cur = first;
        if (first == null) {
            first = newElem;
            first.next = newElemCopy;
        }
        else {
            while (cur.next != null)
                cur = cur.next;
            cur.next = newElem;
            cur.next.next = new Node(element);;
        }
    }

    public void add(int index, String element) {
        Node cur = first;
        if (getByIndex(index) == null)
            throw new IllegalArgumentException();
        if (first == null) {
            first = new Node(element);;
            first.next = new Node(element);
        }
        else if (index == 0 || index == 1) {
            first = new Node(element, first);
            first.next = new Node(element, first.next);
        }
        else {
            if(index % 2 == 1)
                index--;
            for (int i = 1; cur != null; ++i) {
                if (i == index) {
                    cur.next = new Node(element, cur.next);
                    cur.next.next = new Node(element, cur.next.next);
                }
                cur = cur.next;
            }
        }
    }

    public void add(Collection<String> collection) {
        for (String obj: collection)
            add(obj);
    }

    public void add(int index, Collection<String> collection) {
        Collections.reverse((List<?>) collection);
        for (String element: collection)
            add(index, element);
    }

    public void remove() {
        if(first == null)
            return;
        if(first.next.next == null)
            first = null;
        else
            first = new Node(first.next.next.data, first.next.next.next);
    }

    public void remove(int index) {
        if(first == null)
            return;
        if(first.next.next == null)
            first = null;
        else if (index == 0 || index == 1)
            first = new Node(first.next.next.data, first.next.next.next);
        else {
            if (index % 2 == 1)
                index--;
            index--;
            if (getByIndex(index) != null) {
                Node needed = getByIndex(index);
                needed.next = new Node(needed.data, needed.next.next.next.next);
            } else
                throw new NullPointerException();
        }
    }

    public Node getByIndex(int index) {
        Node cur = first;
        if (index == 0)
            return first;
        if(index % 2 == 1)
            index--;
        for (int i = 0; cur != null; ++i) {
            if (i == index) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public void setByIndex(int index, String element) {
        Node cur = first;
        if (getByIndex(index) == null)
            throw new IllegalArgumentException();
        if (index == 0 || index == 1) {
            first.data = element;
            first.next.data = element;
        }
        else {
            if(index % 2 == 1)
                index--;
            for (int i = 1; cur != null; ++i) {
                if (i == index) {
                    cur.next.data = element;
                    cur.next.next.data = element;
                }
                cur = cur.next;
            }
        }
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
        public void remove() { }
    }


    public static void main(String[] args) {
        //index: 0 or 1 = first pair, 2 or 3 = second pair, 4 or 5 = third pair...
        PairStringList list = new PairStringList();
        list.add("A");
        list.add("B");
        list.add("C");
        System.out.println("After adding: " + list);
        list.add(2, "D");
        System.out.println("After adding \'D\' to the 2 position: " + list);

        List<String> list1 = new ArrayList();
        list1.add("E");
        list1.add("F");
        list1.add("G");
        list.add(4, list1);
        System.out.println("After adding collection to the 4 position: " + list);
        System.out.println("Element on 4th position: " + list.getByIndex(4));
        list.setByIndex(5, "H");
        System.out.println("After setting \'H\' to the 5 position: " + list);
        list.remove();
        list.remove(6);
        System.out.println("After removing first element and element at the 6th position: " + list);

        System.out.println("Using iterator:\n");
        var iterator = list.iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (element.equals("B"))
                System.out.println("here");
        }
    }

}
