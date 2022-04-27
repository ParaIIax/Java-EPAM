package ua.advanced.task3;

import java.util.*;

public class IntStringCappedMap implements Map<Integer, String> {
    static class Node {
        Integer key;
        String value;
        Node next;

        Node(Integer key, String value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        Node(Integer key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node first;
    public int capacity = 0;
    private int capacity_limit;
    private int size = 0;

    public IntStringCappedMap(int capacity_limit) {
        this.capacity_limit = capacity_limit;
    }
    
    @Override
    public Set<Entry<Integer, String>> entrySet() {
        Set<Entry<Integer, String>> set = new AbstractSet<Entry<Integer, String>>() {
            @Override
            public Iterator<Entry<Integer, String>> iterator() {
                Iterator iter = new Iterator<Entry<Integer, String>>() {
                    private Node iterator = new Node(null, null, first);
                    @Override
                    public boolean hasNext() {
                        return iterator.next != null;
                    }
                    @Override
                    public Entry<Integer, String> next() {
                        if (hasNext()) {
                            iterator = iterator.next;
                            return new Entry<Integer, String>() {
                                @Override
                                public Integer getKey() {
                                    return iterator.key;
                                }
                                @Override
                                public String getValue() {
                                    return iterator.value;
                                }
                                @Override
                                public String setValue(String value) {
                                    return null;
                                }
                            };
                        }
                        else {
                            throw new NoSuchElementException();
                        }
                    }
                };
                return iter;
            }
            @Override
            public int size() {
                return 0;
            }
        };
        return set;
    }

    @Override
    public String get(Object key) {
        Node cur = first;
        while (cur != null) {
            if (cur.key.equals(key))
                return cur.value;
            cur = cur.next;
        }
        return null;
    }
    
    @Override
    public String put(Integer key, String value) {
        Node newElem = new Node(key, value);
        Node cur = first;
        if (value.length() > capacity_limit)
            throw new IllegalArgumentException();
        if (first == null) {
            first = newElem;
        }
        else {
             while (capacity_limit < (capacity + value.length())) {
                 capacity = capacity - first.value.length();
                 first = first.next;
                 size--;
                 if (first == null)
                     first = newElem;
             }
             while (cur.next != null)
                 cur = cur.next;
             cur.next = newElem;
        }
        size++;
        capacity += value.length();
        return null;
    }

    @Override
    public String remove(Object key) {
        if (first == null)
            return null;
        if (first.key.equals(key)) {
            first = first.next;
            return null;
        }
        if (get(key) == null)
            throw new IllegalArgumentException();
        Node cur = first;
        Node cur2 = cur;
        while(cur != null) {
            if (cur.key.equals(key)) {
                capacity -= cur2.next.value.length();
                cur2.next = cur2.next.next;
                size--;
                return null;
            }
            cur2 = cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    ////////////////////////////////////////////////
    @Override
    public boolean isEmpty() { return false; }
    @Override
    public boolean containsKey(Object key) { return false; }
    @Override
    public boolean containsValue(Object value) { return false; }
    @Override
    public void putAll(Map<? extends Integer, ? extends String> m) { }
    @Override
    public void clear() { }
    @Override
    public Set<Integer> keySet() { return null; }
    @Override
    public Collection<String> values() { return null; }
    ////////////////////////////////////////////////


    public static void main(String[] args) {
        IntStringCappedMap map = new IntStringCappedMap(25);
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(12, "Twelve");
        map.put(9, "Nine");
        map.put(1, "One");

        System.out.println(new TreeMap<>(map));
        //{1=One, 7=Seven, 8=Eight, 9=Nine, 12=Twelve}
    }

}
