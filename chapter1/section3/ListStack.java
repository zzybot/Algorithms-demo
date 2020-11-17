package chapter1.section3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 先进后出LIFO）栈（的链表实现（简易）
 *
 * @author ZZY*/
public class ListStack<E> implements Iterable<E>{
    /**栈长度*/
    private int n;
    /**首节点，最先入栈的是首节点*/
    private Node first;

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E>{
        private Node current = first;
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

    /**链表类*/
    private class Node{
        /**指向下一个 Node*/
        private Node next;
        /**数据域，当前 Node 保存的数据*/
        private E item;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return n;
    }

    public void push(E e){
        if (n == 0){
            /*debug:NullPointerException
            * 错误：没有 new Node 赋值给 first，直接写了 first.item = e; 导致报错
            * */
            first = new Node();
            first.item = e;
        }else {
            Node oldfirst = first;
            first = new Node();
            first.item = e;
            first.next = oldfirst;
        }
        n++;
    }

    public E pop(){
        E e = first.item;
        first = first.next;
        n--;
        return e;
    }
    /**
     * Ex07，返回最近添加的元素而不弹出它
     * */
    public E peek(){
        return first.item;
    }

    public static void main(String[] args) {
        ListStack<String> ls = new ListStack<>();
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!"-".equals(item)){
                ls.push(item);
            }else if (!ls.isEmpty()){
                StdOut.print(ls.pop() + " ");
            }
        }
        System.out.println("(" + ls.size() + " left on stack)");
    }

}
