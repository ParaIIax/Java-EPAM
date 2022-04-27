package ua.advanced.task3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangedOpsIntegerSet implements Iterable<Integer> {
    static class Node {
        Integer data;
        Node next;

        Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node(Integer data) {
            this.data = data;
        }
    }

    private Node first;
    private int size;

    public boolean add(Integer element) {
        Node newElem = new Node(element);
        Node cur = first;
        if (first == null) {
            first = newElem;
        }
        else {
            while (cur.next != null)
                cur = cur.next;
            cur.next = newElem;
        }
        size++;
        return true;
    }

    public Integer search(Integer element) {
        Node cur = first;
        while(cur != null) {
            if (cur.data.equals(element))
                return cur.data;
            cur = cur.next;
        }
        return null;
    }


    public boolean remove(Integer element) {
        if (first == null)
            return false;
        if (first.data.equals(element)) {
            first = first.next;
            return true;
        }
        if (search(element) == null)
            return false;
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

    public boolean add(int first, int last) {
        boolean success = false;
        for (int i = first; i < last; ++i) {
            if (add(i))
                success = true;
        }
        return success;
    }

    public boolean remove(int first, int last) {
        boolean success = false;
        for (int i = first; i < last; ++i) {
            if (remove(i))
                success = true;
        }
        return success;
    }

    public int size() {
        return size;
    }

    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }

    class IteratorImpl implements Iterator<Integer> {
        private Node iterator = new Node(null, first);

        @Override
        public boolean hasNext() {
            return iterator.next != null;
        }

        @Override
        public Integer next() {
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
            RangedOpsIntegerSet.this.remove(iterator.data);
        }

    }

    public static void main(String[] args) {
        RangedOpsIntegerSet set = new RangedOpsIntegerSet();
        set.add(1, 5);
        for (Integer el : set) {
            System.out.println(el);
        }
        RangedOpsIntegerSet set1 = new RangedOpsIntegerSet();
        set1.add(1, 5);
        set1.add(9, 11);
        for (Integer el : set1) {
            System.out.println(el);
        }
        RangedOpsIntegerSet set2 = new RangedOpsIntegerSet();
        set2.add(1, 15);
        set2.remove(3, 12);
        for (Integer el : set2) {
            System.out.println(el);
        }
        System.out.println("\n" + set2.size());
    }

}
