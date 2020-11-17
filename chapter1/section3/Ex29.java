package chapter1.section3;

import java.util.NoSuchElementException;

/**
 * 环形链表实现 Queue：
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

    //删除当前 Node，供 josephus 调用
    private E dequeue(Node node){
        if (isEmpty() || node == null){
            throw new NoSuchElementException();
        }
        //bug：没有写 return 直接调用了 dequeue 导致程序继续运行报错
        if (node.item.equals(last.item)){
            return dequeue();
        }

        Node current;
        for (current = last;current.next != last; current = current.next) {
            if (node.item.equals(current.next.item)){
                E e = current.next.item;
                current.next = current.next.next;
                //bug:没写 size--
                size--;
                return e;
            }
        }

        throw new NoSuchElementException();
    }

    //为 Ex37 提供的方法
    public void josephus(int M){
        //循环，从 last.next 开始
        int count = 1;
        Node current = last.next;
        while (size != 0){
            if (count == M){
                count = 0;
                E num = dequeue(current);
                System.out.print(num + " ");
            }
            current = current.next;
            count++;
        }
    }

}
