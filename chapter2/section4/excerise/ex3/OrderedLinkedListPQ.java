package chapter2.section4.excerise.ex3;

import chapter2.tools.Utils;

import java.util.NoSuchElementException;

/**
 * 有序链表实现优先队列
 * enqueue 时排序，保证第一个元素是最大的
 *
 * @author ZZY
 */
public class OrderedLinkedListPQ<E extends Comparable<E>> {

    private class Node {
        Node next;
        E item;

        public Node() {
        }

        public Node(Node next, E item) {
            this.next = next;
            this.item = item;
        }

    }

    private final Node header = new Node();
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(E e) {
        if (isEmpty()) {
            header.next = new Node(null, e);
            size++;
            return;
        }
        Node current = header.next;
        Node prev = null;
        //如果 current.item 比 e 小，就把 e 插入 current 前
        while (current != null && Utils.less(e, current.item)) {
            prev = current;
            current = current.next;
        }
        // e 是最大的，直接插到 header 后
        if (prev == null) {
            header.next = new Node(header.next, e);
            size++;
        } else {
            prev.next = new Node(current, e);
            size++;
        }

    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("pq is empty");
        }
        E e = header.next.item;
        header.next = header.next.next;
        size--;
        System.out.println(e);
        return e;
    }

    public static void main(String[] args) {
        OrderedLinkedListPQ<Integer> pq = new OrderedLinkedListPQ<>();
        pq.enqueue(1);
        pq.enqueue(0);
        pq.enqueue(5);
        pq.enqueue(3);
        pq.enqueue(9);
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
    }
}
