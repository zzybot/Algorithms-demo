package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Ex35,Ex36
 *
 * 动态数组实现随机队列，出队时替换[1,N-1) 与 N-1,然后取出 N-1。
 * 因为要求随机取出一个元素，所以元素的存入顺序也不再重要了
 * @author ZZY
 */
public class RandomQueue<E> implements Iterable<E> {
    private E[] items = (E[]) new Object[1];
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[size++] = e;
    }

    public void resize(int newsize) {
        E[] newItems = (E[]) new Object[newsize];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    private void random() {
        if (size > 1) {
            Random random = new Random();
            int index = random.nextInt(size);
            E temp = items[size - 1];
            items[size - 1] = items[index];
            items[index] = temp;
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        random();
        E e = items[--size];
        items[size] = null;
        if (size > 0 && size == items.length / 4) {

        }
        return e;
    }

    public E sample() {
        return items[size - 1];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) return "[empty]";
        sb.append("|");
        for (int i = 0; i < items.length; i++)
            sb.append(String.format(" %3s |", items[i] == null ? " " : items[i]));
        return sb.toString();
    }

    private E[] shuffle() {
        E[] newItems = Arrays.copyOfRange(items, 0, size);
        Random random = new Random();
        for (int i = 0; i < newItems.length; i++) {
            int index = i + random.nextInt(newItems.length - i);
            E temp = newItems[i];
            newItems[i] = newItems[index];
            newItems[index] = temp;
        }
        return newItems;
    }

    public static void main(String[] args) {
        RandomQueue<Integer> queue = new RandomQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 3; i++) {
            for (Integer a : queue
            ) {
                System.out.print(" " + a);
            }
            System.out.println("");
        }
        Object obj = new Object();
        obj.hashCode();
    }

    //乱序遍历，在遍历前先将数组元素乱序
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private E[] newItems = shuffle();
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < newItems.length;
            }

            @Override
            public E next() {
                return newItems[index++];
            }
        };
    }


}
