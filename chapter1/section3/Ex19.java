package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * 编写一个可以删除尾结点的链表
 *
 * @author ZZY
 */
public class Ex19<E> implements Iterable<E> {
    private Node first;
    private int size;

    @Override
    public Iterator<E> iterator() {
        return new NodeIterator();
    }

    private class NodeIterator implements Iterator<E> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E e = current.item;
            current = current.next;
            return e;
        }
    }

    private class Node {
        private E item;
        private Node next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 找到最后一个 Node，尾插
     */
    public void add(E e) {
        if (isEmpty()) {
            first = new Node();
            first.item = e;
        } else {
            Node current;
            for (current = first; current.next != null; current = current.next) {
            }

            Node newNode = new Node();
            newNode.item = e;
            current.next = newNode;

        }
        size++;
    }

    /**
     * 找到倒数第二个 Node
     */
    public void deleteLastNode() {
        if (!isEmpty()) {
            if (size == 1) {
                first = null;
            } else {
                Node current = first;
                for (int i = 0; i < size - 2; i++) {
                    current = current.next;
                }
                current.next = null;
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        Ex19<Integer> linkedList = new Ex19<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        StdOut.println("Before removing last node");

        StringJoiner listBeforeRemove = new StringJoiner(" ");
        for (int number : linkedList) {
            listBeforeRemove.add(String.valueOf(number));
        }

        StdOut.println(listBeforeRemove.toString());
        StdOut.println("Expected: 0 1 2 3");

        linkedList.deleteLastNode();

        StdOut.println("\nAfter removing last node");

        StringJoiner listAfterRemove = new StringJoiner(" ");
        for (int number : linkedList) {
            listAfterRemove.add(String.valueOf(number));
        }

        StdOut.println(listAfterRemove.toString());
        StdOut.println("Expected: 0 1 2");
    }
}
