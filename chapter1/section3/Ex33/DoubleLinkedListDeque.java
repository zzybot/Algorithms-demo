package chapter1.section3.Ex33;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Ex33：链表实现双向队列 API
 * @author ZZY
 */
public class DoubleLinkedListDeque<E> implements Iterable<E> {

    private Node first;
    private Node last;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void pushLeft(E e) {
        if (isEmpty()) {
            first = new Node(e, null, null);
            last = first;
        } else {
            first.insertBefore(e);
            first = first.pre;
        }
        size++;
    }

    public void pushRight(E e) {
        if (isEmpty()) {
            last = new Node(e, null, null);
            first = last;
        } else {
            last.insertAfter(e);
            last = last.next;
        }
        size++;
    }

    public E popLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            size--;
            E e = first.delete();
            first = first.next;
            return e;
        }
    }

    public E popRight() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            size--;
            E e = last.delete();
            last = last.pre;
            return e;
        }
    }

    private class Node {
        private E item;
        private Node pre;
        private Node next;

        public Node(E item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }

        //当前 Node 前插入结点
        public void insertBefore(E e) {
            Node node = new Node(e, pre, this);
            if (pre != null) {
                pre.next = node;
            }
            pre = node;
        }

        //当前 Node 后插入结点
        public void insertAfter(E e) {
            Node node = new Node(e, this, next);
            if (next != null) {
                next.pre = node;
            }
            next = node;
        }

        //删除当前结点
        public E delete() {
            E e = item;
            if (next != null) {
                next.pre = pre;
            }
            if (pre != null) {
                pre.next = next;
            }
            return item;
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[empty]";
        } else {
            StringBuilder sb = new StringBuilder();
            Node current = first;
            while (current.next != null) {
                sb.append(current.item).append("-");
                current = current.next;
            }
            sb.append(current.item);
            return sb.toString();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
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

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        DoubleLinkedListDeque<Integer> listDeque2 = new DoubleLinkedListDeque<>();
        listDeque2.pushRight(1);
        listDeque2.pushRight(2);
        listDeque2.pushRight(3);
        listDeque2.pushRight(4);
        listDeque2.pushRight(5);
        System.out.println(listDeque2);
        listDeque2.pushLeft(1);
        listDeque2.pushLeft(2);
        listDeque2.pushLeft(3);
        listDeque2.pushLeft(4);
        listDeque2.pushLeft(5);
        System.out.println(listDeque2);
        listDeque2.popLeft();
        System.out.println(listDeque2);
        listDeque2.popRight();
        System.out.println(listDeque2);
    }
}
