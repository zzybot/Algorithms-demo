package chapter2.section4.excerise.ex3;

import chapter2.tools.Utils;

import java.util.NoSuchElementException;

/**
 * 无序链表实现优先队列
 *
 * @author ZZY
 */
public class UnorderedLinkedListPQ<E extends Comparable<E>> {
    /**
     * 单向链表
     */
    private class Node {
        E item;
        Node next;

        public Node() {
        }

        public Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * header 指向第一个元素前的 Node
     */
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
            header.next = new Node(e, null);
            size++;
            return;
        }
        header.next = new Node(e, header.next);
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ IS EMPTY");
        }
        Node max = header.next;
        Node maxPrev = null;
        Node current = header.next;
        //一遍选择排序找到最大值和最大值的前一个 Node
        while (current.next != null) {
            if (Utils.less(max.item, current.next.item)) {
                max = current.next;
                maxPrev = current;
            }
            current = current.next;
        }
        //如果第一个元素就是最大的
        if (maxPrev == null) {
            E e = header.next.item;
            header.next = header.next.next;
            size--;
            System.out.println(e);
            return e;
        } else {
            E e = max.item;
            maxPrev.next = max.next;
            size--;
            System.out.println(e);
            return e;
        }
    }

    public static void main(String[] args) {
        UnorderedLinkedListPQ<Integer> pq = new UnorderedLinkedListPQ<>();
        pq.enqueue(1);
        pq.enqueue(2);
        pq.enqueue(5);
        pq.enqueue(3);
        pq.enqueue(0);
        pq.enqueue(6);
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
        pq.dequeue();
    }
}
