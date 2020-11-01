package chapter2.example;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/**
 * 一个固定长度的栈，它只能接收 String 类型
 * */
public class FixedCapacityStackOfStrings {
    /**栈空间*/
    private String[] a;
    /**栈长度*/
    private int n;

    public FixedCapacityStackOfStrings(int cap) {
        this.a = new String[cap];
    }

    /**入栈：最大索引处添加一个元素并使栈长度加一*/
    public void push(String item){
        a[n++] = item;
    }

    /**出栈：返回最大索引处元素并使长度减一*/
    public String pop(){
        return a[--n];
    }

    /**栈是否为空*/
    public boolean isEmpty(){
        return n == 0;
    }

    /**返回栈长度*/
    public int size(){
        return n;
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings s = new FixedCapacityStackOfStrings(100);
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
