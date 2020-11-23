package chapter1.section4.exercises.ex28;

import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

/**
 * 使用队列实现一个栈
 * 思路：出栈时，将队列出队，拿到最后一个元素，入队剩余元素。
 * @author ZZY
 */
public class QueueStack<E> {
    /**
     * 栈长度
     */
    private int size;
    private final DoubleLinkedListQueue<E> queue = new DoubleLinkedListQueue<>();

    /**
     * 入栈
     */
    public void push(E e) {
        queue.enqueue(e);
        size++;
    }

    /**
     * 出栈
     */
    @SuppressWarnings("uncheked")
    public E pop() {
        if (isEmpty()){
            throw new NoSuchElementException("no element in stack!");
        }
        //数组保存出栈的元素
        int i = 0;
        E[] array = (E[])new Object[queue.size()];
        while (!queue.isEmpty()) {
            array[i++] = queue.dequeue();
        }
        //取出最后一个元素
        E e = array[array.length - 1];
        for (int j = 0; j < array.length - 1; j++) {
            //剩下的元素入栈
            queue.enqueue(array[j]);
        }
        size--;
        return e;
    }

    /** 返回栈是否为空 */
    public boolean isEmpty(){
        return size == 0;
    }

    /** 返回栈长度 */
    public int size(){
        return size;
    }

    public static void main(String[] args) {
        QueueStack<Integer> stack = new QueueStack<>();
        for (int i = 1; i < 6; i++) {
            stack.push(i);
        }
        StdOut.print(stack.pop());
    }
}
