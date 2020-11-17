package chapter2.example;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/**
 * 一个固定长度的栈，它通过泛型接收任意类型
 * */
public class FixedCapacityStack<E> {
    private E[] a;
    private int n;

    public FixedCapacityStack(int n) {
        a = (E[]) new Object[n];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size(){
        return n;
    }

    public void push(E e){
        a[n++] = e;
    }

    public E pop(){
        return a[--n];
    }

    public static void main(String[] args) {
        FixedCapacityStack s = new FixedCapacityStack(100);
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            if (!"-".equals(item)){
                s.push(item);
            }else if (!s.isEmpty()){
                StdOut.print(s.pop() + " ");
            }
        }
        System.out.println("(" + s.size() + " left on stack)");
    }
}
