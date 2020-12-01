package chapter2.section4.excerise.ex3;

import chapter2.tools.Utils;

import java.util.NoSuchElementException;

/**
 * 有序数组优先队列
 * 每次插入元素时都对新元素执行一次插入排序，从数组末尾删除元素
 */
public class OrderedArrayPQ<E extends Comparable<E>> {
    private E[] a = (E[]) new Comparable[2];
    private int high = 0;
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(E e) {
        if (size == a.length) {
            resize(a.length * 2);
        }
        a[high] = e;
        //新元素插入排序
        insertion(high);
        high++;
        size++;
    }

    public E deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("pq is empty");
        }
        if (size == a.length / 4) {
            resize(a.length / 2);
        }
        //删除最后一个元素
        high--;
        E e = a[high];
        a[high] = null;
        size--;
        System.out.println("删除：" + e);
        return e;
    }

    private void insertion(int index) {
        while (index > 0 && Utils.less(a[index], a[index - 1])) {
            Utils.exch(a, index, index - 1);
            index = index - 1;
        }
    }

    private void resize(int length) {
        E[] temp = (E[]) new Comparable[length];
        if (high >= 0) {
            System.arraycopy(a, 0, temp, 0, high);
        }
        a = temp;
    }

    public static void main(String[] args) {
        OrderedArrayPQ<Integer> pq = new OrderedArrayPQ<>();
        pq.insert(1);
        pq.insert(3);
        pq.insert(2);
        pq.insert(7);
        pq.insert(0);
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
    }
}
