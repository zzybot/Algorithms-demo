package chapter1.section3.Ex38;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * Ex38：链表实现
 * 如果为 first 和 last 单独创建 Node，first 是队头前的 Node，last 是队头后的 Node
 * 就不用重新设置 first 和 last
 * @author ZZY
 */
public class LinkedListGeneralizedQueue<E> implements GeneralizedQueue<E> {
    private class Node {
        E item;
        private Node pre;
        private Node next;

        public Node(E item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }

        public Node() {
        }

        //删除当前结点
        E delete() {
            E e = item;
            item = null;
            if (pre != null) {
                pre.next = next;
            }
            if (next != null) {
                next.pre = pre;
            }
            return e;
        }

        //当前结点后插入结点
        void insertAfter(E item) {
            Node newNode = new Node(item, this, this.next);
            if (next != null) {
                next.pre = newNode;
            }
            next = newNode;
        }
    }

    private int size;
    /**
     * 指向第一个结点
     */
    private Node first;
    /**
     * 指向最后一个结点
     */
    private Node last;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(E e) {
        if (isEmpty()) {
            first = new Node(e, null, null);
            last = first;
            size++;
            StdOut.println(this);
            return;
        }
        last.insertAfter(e);
        last = last.next;
        StdOut.println(this);
        size++;
    }

    @Override
    public E delete(int k) {
        if (k > size || k <= 0){
            throw new NoSuchElementException("k " + k + " is out of array's bounds");
        }
        Node current = first;
        int kk = k;
        //应该在 while 前判断，因为 k-- 总是会使 k == 1，导致每次删除都会使 first 向后移动一位
        if (k == 1){
            first = current.next;
        }
        //找到第 k 个结点，循环 k - 1 次
        while (k - 1 > 0) {
            current = current.next;
            k--;
        }
        E e = current.delete();
        size--;
        StdOut.println(this + "          k = " + kk + " delete : " + e);
        return e;
    }

    @Override
    public String toString() {
        if (isEmpty()){
            return "[empty]";
        }
        StringBuilder sb = new StringBuilder();
        Node current;
        for (current = first; current.next != null ; current = current.next) {
            sb.append(current.item + "->");
        }
        sb.append(current.item);
        return sb.toString();
    }

    public static void main(String[] args) {
        GeneralizedQueue<Integer> queue = new LinkedListGeneralizedQueue<Integer>();
        for(int i = 0; i < 8; i++) {
            queue.insert(i);
        }
        for(int i = 0; i < 4; i++) {
            queue.delete(1);
        }
        for(int i = 8; i < 12; i++) {
            queue.insert(i);
        }
        queue.delete(1);
        queue.delete(4);
        queue.delete(2);
        queue.delete(3);
        queue.delete(1);
        queue.delete(2);
        queue.delete(1);
        queue.delete(1);
    }
}
