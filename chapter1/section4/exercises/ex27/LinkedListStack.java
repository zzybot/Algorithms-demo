package chapter1.section4.exercises.ex27;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 单向链表栈
 *
 * @author ZZY
 * */
public class LinkedListStack<E> implements Iterable<E>{
    @Override
    public Iterator<E> iterator() {
        return new stackIterator();
    }

    private class stackIterator implements Iterator<E>{
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

    private class Node{
        private E item;
        private Node next;

        public Node(E item) {
            this.item = item;
        }
    }

    private Node first;
    private int size;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    /** 入栈 */
    public void push(E e){
        if (isEmpty()){
            first = new Node(e);
        }else {
            Node oldFirst = first;
            first = new Node(e);
            first.next = oldFirst;
        }
        size++;
    }
    /** 出栈 */
    public E pop(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }else {
            E e = first.item;
            first = first.next;
            size--;
            return e;
        }
    }
}
