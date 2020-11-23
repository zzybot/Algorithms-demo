package chapter1.section4.exercises.ex27;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 两个栈实现的队列：
 * 队列：先进先出
 * 栈：先进后出
 * <p>
 * 思路：在队列内部创建两个栈，入队时，将元素存入栈1，出队时，将栈1的元素全部弹出并存入栈2，再从栈2弹出元素
 */
public class TwoStackQueue<E> implements Iterable<E> {
    LinkedListStack<E> stack1 = new LinkedListStack<>();
    LinkedListStack<E> stack2 = new LinkedListStack<>();

    /** 队长度 */
    int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队
     */
    public void enqueue(E e) {
        stack1.push(e);
        size++;
    }

    /**
     * 出队
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            //stack2 为空时，将 stack1 中元素存入 stack2，保证出队顺序
            if (stack2.isEmpty()){
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            size--;
            return stack2.pop();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new TwoStackQueueIterator();
    }

    private class TwoStackQueueIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return stack1.iterator().hasNext();
        }

        @Override
        public E next() {
            return stack1.iterator().next();
        }
    }
}
