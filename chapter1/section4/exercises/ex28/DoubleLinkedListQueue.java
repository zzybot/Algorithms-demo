package chapter1.section4.exercises.ex28;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向链表队列
 * @author ZZY
 */
public class DoubleLinkedListQueue<E> implements Iterable<E> {
    private final Node  header = new Node();
    private final Node  tailer = new Node();
    private int size;

    public DoubleLinkedListQueue() {
        header.next = tailer;
    }

    private class Node {
        private E item;
        private Node prev;
        private Node next;

        public Node() {
        }

        public Node(E item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        /** 删除当前结点 */
        public E delete() {
            E e = item;
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            return e;
        }
        /** 在当前结点后添加结点*/
        public void addAfter(E e) {
            Node newNode = new Node(e,this,this.next);
            if (next != null){
                next.prev = newNode;
            }
            next = newNode;
        }

    }
    /** 返回队列长度 */
    public int size(){
        return size;
    }
    /** 返回队列是否为空 */
    public boolean isEmpty(){
        return size == 0;
    }
    /** 入队，首插*/
    public void enqueue(E e){
        header.addAfter(e);
        size++;
    }
    /** 出队，尾删 */
    public E dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        E e = tailer.prev.delete();
        size--;
        return e;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E>{
        Node current = header.next;
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
