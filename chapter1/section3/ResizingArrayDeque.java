package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Ex33：
 * 初始数组容量为2，pushright 插入0，pushleft 插入1.
 * head 为第一个元素索引-1，tail 为最后一个元素索引+1；
 * 扩容时判断是left 还是 right，如果是left 给左边预留一个空位，如果是 right 给右边预留一个空位
 * 减容时插入元素位置与left 还是 right 无关
 * 被删除元素被复制为 null
 */
public class ResizingArrayDeque<E> implements Iterable<E> {
    private E[] items = (E[]) new Object[2];
    private int head = 0;
    private int tail = 1;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void pushLeft(E e) {
        if (head < 0) {
            pushResize(items.length * 2, true);
        }
        items[head--] = e;
        size++;
    }

    public void pushRight(E e) {
        if (tail > items.length - 1) {
            //扩容
            pushResize(items.length * 2, false);
        }
        items[tail++] = e;
        size++;
    }

    public E popLeft() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E e = items[++head];
        items[head] = null;
        size--;
        if (size == 0) {
            head = 0;
            tail = 1;
        } else if (size > 0 && size == items.length / 4) {
            popResize(items.length / 2);
        }
        return e;
    }

    public E popRight() {
        E e = items[--tail];
        items[tail] = null;
        size--;
        if (size == 0) {
            head = 0;
            tail = 1;
        } else if (size > 0 && size == items.length / 4) {
            popResize(items.length / 2);
        }
        return e;
    }

    private void pushResize(int lenght, boolean flag) {
        E[] array = (E[]) new Object[lenght];
        int headNum = (lenght - size - 1) / 2;
        int current = flag ? headNum + 1 : headNum;
        for (int i = head + 1; i < tail; i++) {
            array[current++] = items[i];
        }
        //此时新元素还未插入
        head = flag ? headNum : headNum - 1;
        tail = flag ? headNum + size + 1 : headNum + size;
        items = array;
    }

    private void popResize(int lenght) {
        E[] array = (E[]) new Object[lenght];
        int headNum = Math.abs(lenght - size) / 2;
        int current = headNum;
        for (int i = head + 1; i < tail; i++) {
            array[current++] = items[i];
        }
        //head tail 重新赋值
        head = headNum - 1;
        tail = headNum + size;
        items = array;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[empty]";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = head + 1; i < tail; i++) {
                if (i == tail - 1) {
                    sb.append(items[i]);
                } else {
                    sb.append(items[i]).append("-");
                }
            }
            return sb.toString();
        }
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = head + 1;

            @Override
            public boolean hasNext() {
                return i < tail;
            }

            @Override
            public E next() {
                return items[i++];
            }
        };
    }

    public static void main(String[] args) {
        ResizingArrayDeque<Integer> integers = new ResizingArrayDeque<>();
        integers.pushRight(1);
        integers.pushRight(2);
        integers.pushRight(3);
        integers.pushRight(4);
        integers.pushRight(5);
        System.out.println(integers);
        integers.pushLeft(1);
        integers.pushLeft(2);
        integers.pushLeft(3);
        integers.pushLeft(4);
        integers.pushLeft(5);
        System.out.println(integers);
        integers.popLeft();
        System.out.println(integers);
        integers.popRight();
        System.out.println(integers);
    }
}
