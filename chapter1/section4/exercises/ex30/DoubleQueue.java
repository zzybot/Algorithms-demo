package chapter1.section4.exercises.ex30;

import chapter1.section4.exercises.ex29.DoubleLinkedListStack;
import chapter1.section4.exercises.ex29.Steque;

/**
 * 一个栈和一个 steque 实现双向队列
 * API：pushLeft、pushRight、popLeft、popRight
 * <p>
 * 思路：栈负责出队的方法，调用 popLeft 时，直接调用栈的 pop 方法，
 * 调用 popRight 时，出栈再入栈转置元素顺序，调用 pop 方法，再次出栈入栈还原元素顺序。
 * <p>
 * steque 负责入队的方法，pushleft 对应 enqueue，pushRight 对应 push 方法
 * @author ZZY
 */
public class DoubleQueue<E> {
    private final Steque<E> queue = new Steque<>();
    private final DoubleLinkedListStack<E> stack = new DoubleLinkedListStack<>();
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void pushLeft(E e) {
        queue.enqueue(e);
        size++;
    }

    public void pushRight(E e) {
        queue.push(e);
        size++;
    }

    public E popLeft() {
        check();
        size--;
        return stack.pop();
    }

    public E popRight() {
        check();
        return reversePop();
    }

    private E reversePop() {
        int n = stack.size();
        E[] items = (E[]) new Object[n];
        for (int i = 0; i < n; i++) {
            items[i] = stack.pop();
        }

        E e = items[n - 1];
        if (n > 1) {
            for (int i = n - 2; i > 0; i--) {
                stack.push(items[i]);
            }
        }
        return e;
    }

    private void check() {
        if (stack.isEmpty()) {
            while (!queue.isEmpty()) {
                stack.push(queue.dequeue());
            }
        }
    }

    public static void main(String[] args) {
        DoubleQueue<Integer> queue = new DoubleQueue<>();
        queue.pushLeft(1);
        queue.pushLeft(2);
        queue.pushLeft(3);
        System.out.println(queue.popRight());
    }
}
