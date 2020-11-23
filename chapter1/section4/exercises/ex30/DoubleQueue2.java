package chapter1.section4.exercises.ex30;

import chapter1.section4.exercises.ex29.DoubleLinkedListStack;
import chapter1.section4.exercises.ex29.Steque;

/**
 * 用 steque 实现 pushLeft、pushRight、popRight
 * 用 stack 实现 popLeft
 */
public class DoubleQueue2<E> {
    private final Steque<E> queue = new Steque<>();
    private final DoubleLinkedListStack<E> stack = new DoubleLinkedListStack<>();
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void pushLeft(E e) {
        //steque 左插
        queue.enqueue(e);
        size++;
    }

    public void pushRight(E e) {
        //steque 右插
        queue.push(e);
        size++;
    }

    public E popRight(){
        if (queue.isEmpty()){
            while (!stack.isEmpty()){
                queue.push(stack.pop());
            }
        }
        return queue.dequeue();
    }

    public E popLeft(){
        if (stack.isEmpty()){
            while (!queue.isEmpty()){
                stack.push(queue.dequeue());
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        DoubleQueue2<Integer> queue = new DoubleQueue2<>();
        queue.pushLeft(1);
        queue.pushLeft(2);
        queue.pushLeft(3);
        System.out.println(queue.popLeft());
        System.out.println(queue.popLeft());
        System.out.println(queue.popLeft());
        queue.pushRight(4);
        queue.pushRight(5);
        queue.pushRight(6);
        System.out.println(queue.popLeft());
        System.out.println(queue.popLeft());
        System.out.println(queue.popLeft());
    }
}
