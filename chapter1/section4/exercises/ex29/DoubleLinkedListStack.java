package chapter1.section4.exercises.ex29;

import java.util.NoSuchElementException;

/**
 * 双向链表实现栈(FIFO)
 *
 * @author ZZY
 */
public class DoubleLinkedListStack<E> {
    private final Node header = new Node();
    private int size;

    public DoubleLinkedListStack() {
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

        public void addAfter(E e) {
            Node newNode = new Node(e, this, this.next);
            if (next != null){
                next.prev = newNode;
            }
            next = newNode;
        }

        public E delete() {
            E e = this.item;
            if (prev != null){
                prev.next = next;
            }
            if (next != null){
                next.prev = prev;
            }
            return e;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void push(E e){
        header.addAfter(e);
        size++;
    }

    public E pop(){
        if (isEmpty()){
            throw new NoSuchElementException("no element in stack!");
        }
        E e = header.next.delete();
        size--;
        return e;
    }
}
