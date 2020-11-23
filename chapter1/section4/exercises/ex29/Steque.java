package chapter1.section4.exercises.ex29;

import edu.princeton.cs.algs4.StdOut;

/**
 * 使用两个栈实现 Steque
 * 要求除了实现 enqueue 和 dequeue 外，实现 push 方法，该方法可以从队尾插入元素
 *
 * 思路：栈1 负责队列的存入操作，栈2 负责队列的删除操作，当 栈2 为空时，将 栈1 的所有元素出栈存入 栈2
 *  push 方法直接把元素存入 栈2.
 * */
public class Steque<E> {
    private final DoubleLinkedListStack<E> stack1 = new DoubleLinkedListStack<>();
    private final DoubleLinkedListStack<E> stack2 = new DoubleLinkedListStack<>();
    private int size;

    public Steque() {
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enqueue(E e){
        stack1.push(e);
        size++;
    }

    public E dequeue(){
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                 stack2.push(stack1.pop());
            }
        }
        size--;
        return stack2.pop();
    }

    public void push(E e){
        stack2.push(e);
        size++;
    }

    public static void main(String[] args) {
        Steque<Integer> steque = new Steque<>();
        for (int i = 0; i < 5; i++) {
            steque.push(i);
        }
        for (int i = 5; i < 10; i++) {
            steque.enqueue(i);
        }
        StdOut.printf("Steque has %d elements", steque.size());
        while (!steque.isEmpty()) {
            StdOut.printf("pop %d from steque, now steque has %d elements\n",
                    steque.dequeue(), steque.size());
        }
    }
}
