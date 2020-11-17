package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/**
 * 使用数组实现一个背包，要求该背包遍历时无序
 * <p>
 * 思路：每次循环前打乱数组元素的顺序
 */
public class RandomBag<E> implements Iterable<E> {
    private E[] items = (E[]) new Object[1];
    private int size;

    public RandomBag() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
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

    //创建数组副本并使其中元素乱序
    private E[] randomCopy() {
        E[] randomArray = Arrays.copyOfRange(items, 0, size);
        Random random = new Random();
        for (int i = 0; i < randomArray.length; i++) {
            //让 索引 i 处的元素与某个索引 不小于 i 的元素交换位置
            int index = i + StdRandom.uniform(randomArray.length - i);
            E temp = randomArray[i];
            randomArray[i] = randomArray[index];
            randomArray[index] = temp;
        }
        return randomArray;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private E[] randomArray = randomCopy();
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < randomArray.length;
            }

            @Override
            public E next() {
                return randomArray[index++];
            }
        };
    }

    public static void test() {
        RandomBag<Integer> bag = new RandomBag<Integer>();
        for (int i = 0; i < 20; i++)
            bag.add(i);

        for (int i = 0; i < 10; i++) {
            for (Integer elem : bag)
                StdOut.print(elem + " ");
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        test();
    }
}
