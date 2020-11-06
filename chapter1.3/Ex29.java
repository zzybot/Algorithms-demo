package chapter1.section3;

import java.util.NoSuchElementException;

/**
 * 唤醒链表实现 Queue：
 * 1.环形链表，非空时 last.next == first
 *      *删除元素时，last.next = last.next.next，队列不能为空，队列只有一个元素时是特殊情况
 *      *增加元素时，last 赋予新 Node，新的 last.next == first。注意链表为空时的情况
 * 2.只能使用一个 Node 类型实例变量 last
 * 3.Queue 先进先出，队尾增加元素，队头删除元素
 *
 * @author ZZY*/
public class Ex29<E> {
    private int size;
    private Node last;

    private class Node{
        private E item;
        private Node next;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enqueue(E e){
        if (isEmpty()){
            last = new Node();
            last.item = e;
            last.next = last;
            size++;
            return;
        }

        Node oldLast = last;
        last = new Node();
        last.item = e;
        last.next = oldLast.next;
        oldLast.next = last;
        size++;
    }

    public E dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        if (size == 1){
            E e = last.item;
            last = null;
            size--;
            return e;
        }
        //删除时，至少保证有队头存在，也就是说链表中至少有两个 Node
        E e = last.next.item;
        last.next = last.next.next;
        size--;
        return e;
    }

}
