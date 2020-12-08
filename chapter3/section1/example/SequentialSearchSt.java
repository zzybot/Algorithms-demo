package chapter3.section1.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 无序链表实现符号表的基本 API
 *
 * @author ZZY
 */
public class SequentialSearchSt<Key, Value> implements Iterable<Key> {

    @Override
    public Iterator<Key> iterator() {
        return new KeyIterator();
    }

    class KeyIterator implements Iterator<Key> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Key next() {
            Key key = current.key;
            current = current.next;
            return key;
        }
    }

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node first;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加键值对
     */
    public void put(Key key, Value value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("key or value can't be null!");
        }

        if (isEmpty()) {
            first = new Node(key, value, null);
        } else {

            for (Node current = first; current != null; current = current.next) {
                if (key.equals(current.key)) {
                    current.value = value;
                    return;
                }
            }

            Node oldFirst = first;
            first = new Node(key, value, oldFirst);
        }
        size++;
    }

    /**
     * 查找值
     */
    public Value get(Key key) {
        check(key);
        for (Node current = first; current != null; current = current.next) {
            if (key.equals(current.key)) {
                return current.value;
            }
        }
        return null;
    }

    private void check(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key can not be null!");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("table is empty");
        }
    }

    /**
     * 即时删除
     */
    public Value delete(Key key) {
        check(key);

        if (key.equals(first.key)) {
            Value value = first.value;
            first = first.next;
            return value;
        }

        for (Node current = first; current.next != null; current = current.next) {
            if (key.equals(current.next.key)) {
                Value value = current.next.value;
                current.next = current.next.next;
                return value;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        SequentialSearchSt<Integer, Integer> searchSt = new SequentialSearchSt<>();
//        System.out.println(searchSt.get(1)); java.util.NoSuchElementException: table is empty
        searchSt.put(1, 30);
        searchSt.put(4, 20);
        searchSt.put(6, 10);
        searchSt.put(8, 40);

        //40
        System.out.println(searchSt.get(8));
//        System.out.println(searchSt.get(null)); java.lang.IllegalArgumentException: key can not be null!
//        searchSt.put(null,3); java.lang.IllegalArgumentException: key or value can't be null!
        //null
        System.out.println(searchSt.get(10));
        System.out.println(searchSt.delete(8));
        System.out.println(searchSt.delete(1));
    }
}
