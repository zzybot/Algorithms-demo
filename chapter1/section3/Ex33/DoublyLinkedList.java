package chapter1.section3.Ex33;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 实现双向链表，要求：表头插入节点、表尾插入节点、表头删除结点、表尾删除结点；
 * 删除指定结点、在指定结点前插入新结点、在指定结点后插入新结点
 *
 * @author ZZY
 */
public class DoublyLinkedList<E> implements Iterable<E> {
    //首结点
    private DoubleNode first;
    //尾结点
    private DoubleNode last;
    //链表长度
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    //在表头添加元素
    public void addFirst(E e) {
        if (isEmpty()) {
            first = new DoubleNode();
            first.item = e;
            last = first;
            size++;
        } else {
            DoubleNode oldFirst = first;
            first = new DoubleNode();
            first.item = e;
            first.next = oldFirst;
            oldFirst.pre = first;
            size++;
        }
    }

    //在表尾添加元素
    public void addLast(E e) {
        if (isEmpty()) {
            last = new DoubleNode();
            last.item = e;
            first = last;
            size++;
        } else {
            DoubleNode oldLast = last;
            last = new DoubleNode();
            last.item = e;
            oldLast.next = last;
            last.pre = oldLast;
            size++;
        }
    }

    //在指定结点前插入结点
    public void insertBefore(DoubleNode node, E e) {
        if (node == null || isEmpty()) {
            throw new NoSuchElementException();
        } else if (node.item.equals(first)) {
            addFirst(e);
        } else {
            DoubleNode current;
            for (current = first.next; current != null; current = current.next) {
                if (current.item.equals(node.item)) {
                    DoubleNode previous = current.pre;
                    DoubleNode parameterNode = new DoubleNode();
                    parameterNode.item = e;
                    //与前一个 Node 连接
                    previous.next = parameterNode;
                    parameterNode.pre = previous;
                    //与后一个 Node 连接
                    parameterNode.next = current;
                    current.pre = parameterNode;
                    size++;
                }
            }
        }
    }

    //在指定结点后插入结点
    public void insertAfter(DoubleNode node, E e) {
        if (node == null || isEmpty()) {
            throw new NoSuchElementException();
        } else if (node.item.equals(last)) {
            addLast(e);
        } else {
            DoubleNode current;
            for (current = first; current != null; current = current.next) {
                if (current.item.equals(node.item)) {
                    DoubleNode next = current.next;
                    DoubleNode parameterNode = new DoubleNode();
                    parameterNode.item = e;
                    //与前一个 Node 连接
                    current.next = parameterNode;
                    parameterNode.pre = current;
                    //与后一个 Node 连接
                    parameterNode.next = next;
                    next.pre = parameterNode;

                    size++;
                }
            }
        }
    }

    //在表头删除元素
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            E e = first.item;
            first = null;
            size--;
            return e;
        } else {
            DoubleNode oldFirst = first;
            //原 first 的数据域
            E e = oldFirst.item;
            first = first.next;
            oldFirst.next = null;
            first.pre = null;
            size--;
            return e;
        }
    }

    //在表尾删除元素
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            E e = last.item;
            last = null;
            size--;
            return e;
        } else {
            DoubleNode oldLast = last;
            E e = oldLast.item;
            last = last.pre;
            last.next = null;
            oldLast = null;
            size--;
            return e;
        }
    }

    //删除指定元素,node 为参数
    public E remove(DoubleNode node) {
        if (isEmpty() || node == null) {
            throw new NoSuchElementException();
        } else if (node.item.equals(first)) {
            removeFirst();
        } else if (node.item.equals(last)) {
            removeLast();
        } else {
            DoubleNode current;
            for (current = first.next; current.next != null; current = current.next) {
                if (node.item.equals(current.item)) {
                    DoubleNode previous = current.pre;
                    DoubleNode next = current.next;

                    //删除 current
                    E e = current.item;
                    //直接将 currnt 赋值 null，回收该对象的引用
                    current = null;
                    previous.next = next;
                    next.pre = previous;
                    size--;
                    return e;
                }
            }
        }
        return null;
    }

    //删除指定元素，以 int （第几个 Node）为参数？
    public E remove(int index) {
        //排除不合理情况
        if (isEmpty() || index < 1 || index > size) {
            throw new NoSuchElementException();
        }

        //删除首尾
        if (index == 1) {
            removeFirst();
        } else if (index == size) {
            removeLast();
        }

        //index 小于 size/2 时从 first 开始遍历
        if (index <= size / 2) {
            DoubleNode current = first;
            for (int i = 0; i < index -1; i++) {
                current = current.next;
            }
            DoubleNode previous = current.pre;
            DoubleNode next = current.next;
            E e = current.item;
            current = null;
            previous.next = next;
            next.pre = previous;
            size--;
            return e;
            //index 大于 size/2 时从 first 开始遍历
        } else {
            DoubleNode current = last;
            for (int i = 0; i < (size - index); i++) {
                current = current.pre;
            }
            DoubleNode previous = current.pre;
            DoubleNode next = current.next;
            E e = current.item;
            current = null;
            previous.next = next;
            next.pre = previous;
            size--;
            return e;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new DoubleIterator();
    }

    private class DoubleIterator implements Iterator<E> {
        DoubleNode current = first;

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

    /**
     * @author ZZY
     */
    private class DoubleNode {
        //数据域
        private E item;
        //后一个结点
        private DoubleNode next;
        //前一个结点
        private DoubleNode pre;
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);

        for (Integer i : list
        ) {
            System.out.print(i);
        }
        System.out.println("");
        System.out.println(list.size());
        list.removeLast();
        for (Integer i : list
        ) {
            System.out.print(i);
        }
        System.out.println("");
        list.removeFirst();
        for (Integer i : list
        ) {
            System.out.print(i);
        }
        System.out.println("");

        list.remove(3);
        for (Integer i : list
        ) {
            System.out.print(i);
        }
    }
}
