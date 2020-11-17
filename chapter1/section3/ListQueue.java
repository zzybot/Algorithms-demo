package chapter1.section3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 先进先出（FIFO）队列，在队尾添加元素，在队头删除元素
 */
public class ListQueue<E> implements Iterable<E>{
    private int n;
    private Node first;
    private Node last;

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
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

    private class Node {
        private E item;
        private Node next;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }
    /**
     * 入栈：队尾插入元素，并且注意该队列只有一个元素时，first 与 last 都指向该元素
     * */
    public void enqueue(E e){
        Node oldlast = last;
        last = new Node();
        last.item = e;
        last.next = null;
        if (isEmpty()){
            first = last;
        }else {
            oldlast.next = last;
        }
        n++;
    }

    public E dequeue(){
        //从队头删除元素
        E e = first.item;
        first = first.next;
        n--;
        return e;
    }

    public static void main(String[] args) {
        ListQueue<String> lq = new ListQueue<>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!"-".equals(item)){
                lq.enqueue(item);
            }else if (!lq.isEmpty()){
                StdOut.print(lq.dequeue() + " ");
            }
        }
        System.out.println("(" + lq.size() + " left on stack)");
    }
}
