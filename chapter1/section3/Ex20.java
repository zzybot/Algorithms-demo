package chapter1.section3;

import java.util.NoSuchElementException;

/**
 * @author ZZY
 * 为链表添加一个方法，可以删除第 k 个结点，前提是该结点存在
 */
public class Ex20<E> {
    private Node first;
    private int size;

    private class Node {
        private Node next;
        private E item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(E e) {
        if (size == 0) {
            first = new Node();
            first.item = e;
        } else {
            Node current;
            for (current = first; current.next != null; current = current.next) {
            }
            Node newNode = new Node();
            newNode.item = e;
            current.next = newNode;
        }
        size++;
    }

    /**
     * 删除第 k 个结点，包括三种情况：
     * 1. 结点不足 k 个或链表为空（链表为空的情况一开始忘记了）
     * 2. 结点等于 k 个
     * 3. 结点大于 k 个
     */
    public E delete(int k) {

        //排除三种不合理情况
        if (size < k || isEmpty() || k <= 0) {
            throw new NoSuchElementException();
            //此时 k 和 size 均大于零，且 k 小于等于 size
        } else if (size == k) {
            Node current = first;
            for (int i = 0; i < k - 2; i++) {
                current = current.next;
            }
            E e = current.next.item;
            current.next = null;
            return e;
        } else {
            Node current = first;
            Node preNode = null;
            int count = 0;
            for (int i = 0; i < k; i++) {
                count++;
                current = current.next;
                //找到当前 Node 的前一个 Node
                if (count == k - 2){
                    preNode = current;
                }
            }
            //此时，current 第 k 个 Node 的后一个 Node
            assert preNode != null;
            E e = preNode.next.item;
            preNode.next = current;
            size--;
            return e;
        }
    }

    public static void main(String[] args) {
        Ex20<Integer> list = new Ex20<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        System.out.println(list.delete(5));
        System.out.println(list.delete(3));

    }
}
