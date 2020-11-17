package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Ex32：通过链表实现一个队列，要求：实现三种方法，push,pop,enqueue
 * push 与 pop 对同一端操作，enqueue 对另一端操作。
 * pop 与 enqueue 实现基本队列操作,push 是额外的
 */
public class Steque<E> implements Iterable<E> {
    private int size;
    private Node first;
    private Node last;

    @Override
    public Iterator<E> iterator() {
        return new StequeIterator();
    }

    private class StequeIterator implements Iterator<E> {
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
        private Node next;
        private E item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //队尾添加元素
    public void enqueue(E e) {
        Node oldLast = last;
        last = new Node();
        last.item = e;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    //队头删除元素
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            E e = first.item;
            first = null;
            size--;
            return e;
        } else {
            E e = first.item;
            first = first.next;
            size--;
            return e;
        }

    }

    //队头添加元素
    public void push(E e) {
        Node oldFirst = first;
        first = new Node();
        first.item = e;
        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldFirst;
        }
        size++;
    }

    public static void main(String[] args) {
        Steque<Integer> steque = new Steque<>();
        steque.enqueue(1);
        steque.enqueue(2);
        steque.enqueue(3);
        steque.enqueue(4);
        steque.enqueue(5);
        steque.enqueue(6);
        for (Integer i : steque
        ) {
            System.out.print(i);
        }
        System.out.println("");
        steque.pop();
        for (Integer i : steque
        ) {
            System.out.print(i);
        }
        System.out.println("");
        steque.push(9);
        for (Integer i : steque
        ) {
            System.out.print(i);
        }
    }
}
