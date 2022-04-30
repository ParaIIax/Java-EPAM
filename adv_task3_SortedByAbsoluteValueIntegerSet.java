package ua.advanced.task3;

import java.util.*;

public class SortedByAbsoluteValueIntegerSet implements SortedSet<Integer> {
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

    @Override
    public boolean add(Integer element) {
        Node newElem = new Node(element);
        if (first == null) {
            first = newElem;
        }
        else {
            Node cur = first;
            while (cur.next != null)
                cur = cur.next;
            cur.next = newElem;
            sort();
        }
        return true;
    }

    public void sort() {
        Node cur = first;
        while (cur.next != null) {
            if (abs(cur.next.data) < abs(cur.data)) {
                Integer data = cur.next.data;
                cur.next.data = cur.data;
                cur.data = data;
                cur = first;
                if (abs(first.data) > abs(first.next.data)) {
                    Integer data1 = first.next.data;
                    first.next.data = first.data;
                    first.data = data1;
                }
            }
            cur = cur.next;
        }
    }

    public static int abs(int a) {
        if (a < 0)
            return -a;
        else
            return a;
    }

    @Override
    public boolean remove(Object element) {
        if(first == null)
            return false;
        if(first.data.equals(element)) {
            first = first.next;
            return true;
        }
        Node cur = first;
        Node cur2 = cur;
        while(cur != null) {
            if (cur.data.equals(element)) {
                cur2.next = cur2.next.next;
                return true;
            }
            cur2 = cur;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> collection) {
        for (Integer element: collection)
            add(element);
        return true;
    }

    @Override
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
    }

    @Override
    public String toString() {
        String str = "[ ";
        Node cur = first;
        while(cur != null) {
            str += cur.data + " ";
            cur = cur.next;
        }
        return str + "]";
    }

    public static void main(String[] args) {
        SortedByAbsoluteValueIntegerSet set = new SortedByAbsoluteValueIntegerSet();
        set.add(6);
        System.out.println(set);
        set.add(-3);
        System.out.println(set);
        set.add(-8);
        System.out.println(set);
        set.add(2);
        System.out.println(set);
        set.add(7);
        System.out.println("After adding new elements: " + set);
        set.remove(2);
        set.remove(7);
        System.out.println("After removing 2 and 7: " + set);

        List<Integer> list = new ArrayList();
        list.add(5);
        list.add(15);
        list.add(-4);
        list.add(1);
        set.addAll(list);
        System.out.println("After adding the collection: " + set);
        System.out.println("Displaying a set using an iterator:");
        var iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            System.out.print(element + " ");
        }
    }

    @Override
    public int size() { return 0; }
    @Override
    public boolean contains(Object o) { return false; }
    @Override
    public Comparator<? super Integer> comparator() { return null; }
    @Override
    public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) { return null; }
    @Override
    public SortedSet<Integer> headSet(Integer toElement) { return null; }
    @Override
    public SortedSet<Integer> tailSet(Integer fromElement) { return null; }
    @Override
    public Integer first() { return null; }
    @Override
    public Integer last() { return null; }
    @Override
    public Object[] toArray() { return new Object[0]; }
    @Override
    public <T> T[] toArray(T[] a) { return null; }
    @Override
    public boolean isEmpty() { return false; }
    @Override
    public boolean containsAll(Collection<?> c) { return false; }
    @Override
    public boolean retainAll(Collection<?> c) { return false; }
    @Override
    public boolean removeAll(Collection<?> c) { return false; }
    @Override
    public void clear() { }
    @Override
    public Spliterator<Integer> spliterator() {return SortedSet.super.spliterator();}

}
