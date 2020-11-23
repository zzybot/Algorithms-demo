package chapter1.section4.exercises.ex31;

import chapter1.section4.exercises.ex29.DoubleLinkedListStack;

import java.util.NoSuchElementException;


/**
 * 三个栈实现双向队列
 * stackLeft 实现 pushLeft、popLeft
 * stackMid 缓冲池，stackLeft 或 stackRight 为空时转移一半元素
 * stackRight 实现 pushRight、popRight
 * @author ZZY
 */
public class ThreeStackDoubleQueue<E> {
    private final DoubleLinkedListStack<E> stackLeft = new DoubleLinkedListStack<>();
    private final DoubleLinkedListStack<E> stackMid = new DoubleLinkedListStack<>();
    private final DoubleLinkedListStack<E> stackRight = new DoubleLinkedListStack<>();
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void pushLeft(E e) {
        stackLeft.push(e);
        size++;
    }

    public void pushRight(E e) {
        stackRight.push(e);
        size++;
    }

    public E popLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (stackLeft.isEmpty()) {
            leftToRight();
        }
        return stackLeft.pop();
    }

    public E popRight(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        if (stackRight.isEmpty()){
            rightToLeft();
        }
        return stackRight.pop();
    }

    public void leftToRight() {
        transfer(stackLeft, stackRight);
    }

    public void rightToLeft() {
        transfer(stackRight, stackLeft);
    }

    private void transfer(DoubleLinkedListStack<E> stackRight, DoubleLinkedListStack<E> stackLeft) {
        int index = stackRight.size() / 2;
        while (index-- > 0){
            stackMid.push(stackRight.pop());
        }
        while (!stackRight.isEmpty()){
            stackLeft.push(stackRight.pop());
        }
        while (!stackMid.isEmpty()){
            stackRight.push(stackMid.pop());
        }
    }

    public static void main(String[] args) {

    }
}
