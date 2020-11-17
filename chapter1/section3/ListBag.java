package chapter1.section3;

import java.util.Iterator;

/**
 * 背包，链表实现
 *
 * @author ZZY
 * */
public class ListBag<E> implements Iterable<E> {

    private int n;
    private Node first;

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public void push(E e){
        Node older = first;
        first = new Node();
        first.item = e;
        first.next = older;
        n++;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class Node{
        private Node next;
        private E item;
    }

    private class ListIterator implements Iterator<E>{
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
}
