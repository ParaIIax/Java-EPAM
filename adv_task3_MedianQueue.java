package ua.advanced.task3;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MedianQueue implements Queue<Integer> {
    private static class Node {
        Integer data;
        Node next;

        public Node(Integer data) {
            this.data = data;
        }

        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }

    }

    private Node first;

    @Override
    public boolean offer(Integer element) {
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
        return true;
    }

    @Override
    public Integer poll() {
        if(first == null)
            return null;
        Integer median = getMedian();
        first = first.next;
        return median;
    }

    @Override
    public Integer peek() {
        return first.data;
    }

    @Override
    public int size() {
        int size = 0;
        Node cur = first;
        while (cur != null) {
            cur = cur.next;
            ++size;
        }
        return size;
    }

    @Override
    public String toString() {
        String str = "[ ";
        Node cur = first;
        while (cur != null) {
            str += cur.data + " ";
            cur = cur.next;
        }
        return str + "]";
    }

    public MedianQueue sortAscending() {
        MedianQueue queue = new MedianQueue();
        Node cur1 = first;
        while (cur1 != null) {
            queue.offer(cur1.data);
            cur1 = cur1.next;
        }
        Node cur = queue.first;
        while (cur.next != null) {
            if (cur.next.data < cur.data) {
                Integer data = cur.next.data;
                cur.next.data = cur.data;
                cur.data = data;
                cur = queue.first;
                if (queue.first.data > queue.first.next.data) {
                    Integer data1 = queue.first.next.data;
                    queue.first.next.data = queue.first.data;
                    queue.first.data = data1;
                }
            }
            cur = cur.next;
        }
        return queue;
    }

    public Integer getMedian() {
        MedianQueue queue = sortAscending();
        Node cur = queue.first;
        int median = size() / 2;
        if (size() % 2 == 0)
            median--;
        for (int i = 0; cur.next != null; ++i) {
            if (i == median)
                return cur.data;
            cur = cur.next;
        }
        return 0;
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
    public void clear() { first = null; }

    public static void main(String[] args) {
        MedianQueue queue = new MedianQueue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        System.out.println("After adding new elements: " + queue);
        System.out.println("Returning the median after pulling an element from the queue: " + queue.poll());
        System.out.println("After pulling the element from the queue: " + queue);
        queue.clear();//Clearing the queue
        queue.offer(1);
        queue.offer(987);
        queue.offer(4);
        queue.offer(2);
        queue.offer(3);
        System.out.println("After clearing the queue and adding new elements: " + queue);
        System.out.println("Getting an element on the top of the queue: " + queue.peek());
        System.out.println("Size of the queue: " + queue.size());
        System.out.println("Getting the median of the queue: " + queue.getMedian());
        System.out.println("Displaying a queue using an iterator:");
        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            System.out.print(element + " ");
        }
    }

    @Override
    public Integer remove() { return null; }
    @Override
    public Integer element() { return null; }
    @Override
    public boolean isEmpty() { return false; }
    @Override
    public boolean contains(Object o) { return false; }
    @Override
    public Object[] toArray() { return new Object[0]; }
    @Override
    public <T> T[] toArray(T[] a) { return null; }
    @Override
    public boolean add(Integer integer) { return false; }
    @Override
    public boolean remove(Object o) { return false; }
    @Override
    public boolean containsAll(Collection<?> c) { return false; }
    @Override
    public boolean addAll(Collection<? extends Integer> c) { return false; }
    @Override
    public boolean removeAll(Collection<?> c) { return false; }
    @Override
    public boolean retainAll(Collection<?> c) { return false;}

}
