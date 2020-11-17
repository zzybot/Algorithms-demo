package chapter1.section2;

import javax.naming.OperationNotSupportedException;
import java.util.Iterator;
/**
 * @author ZZY
 * 这是一个动态调整容量大小的下压栈（LIFO），可以使用 foreach 进行倒序遍历。
 * */
public class ResizingArrayStack<E> implements Iterable {
    /**
     * 栈空间，初始容量为 1
     */
    private E[] a = (E[]) new Object[1];
    /**
     * 栈长度
     */
    private int n;

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 入栈，栈满时扩容至原来的 2 倍
     */
    public void push(E e) {
        if (a.length == n) {
            resize(a.length * 2);
        }
        a[n++] = e;
    }

    /**
     * 出栈，使用率不超过四分之一时容量减半
     */
    public E pop() {
        E e = a[--n];
        //栈中不应该保存已出栈对象的引用
        a[n] = null;
        if (n > 0 && n == a.length/4) {
            resize((int) (a.length/2));
        }
        return e;
    }

    /**
     * 修改栈空间
     */
    public void resize(int max) {
        E[] temps = (E[]) new Object[max];
        for (int i = 0; i < a.length; i++) {
            temps[i] = a[i];
        }
        a = temps;
    }

    @Override
    public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * 倒序遍历的迭代器
     * */
    private class ReverseArrayIterator implements Iterator {

        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public E next() {
            return a[--i];
        }

        @Override
        public void remove() {
            try {
                throw new OperationNotSupportedException("不支持该方法！");
            } catch (OperationNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
}
