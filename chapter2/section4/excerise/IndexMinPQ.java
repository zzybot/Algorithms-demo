package chapter2.section4.excerise;

import java.util.NoSuchElementException;

/**
 * 索引优先队列
 *
 * @author ZZY
 */
public class IndexMinPQ<T extends Comparable<T>> {
    private final T[] keys;
    private final int[] pq;
    private final int[] qp;
    private int size;

    public IndexMinPQ(int length) {
        keys = (T[]) new Comparable[length + 1];
        pq = new int[length + 1];
        qp = new int[length + 1];

        //填充 -1，用于判断索引是否存在
        for (int i = 0; i <= length; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 插入
     */
    public void insert(int k, T key) {
        if (size == pq.length - 1) {
            throw new IllegalArgumentException("priority queue overflow");
        }
        if (qp[k] != -1) {
            throw new IllegalArgumentException("index k already exists");
        }
        size++;
        keys[k] = key;
        pq[size] = k;
        swim(size);
    }

    /**
     * 删除最小元素并返回索引
     */
    public int delMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("pq is empty");
        }
        int minIndex = pq[1];
        exch(pq, 1, size);
        size--;
        System.out.println(keys[pq[size + 1]]);
        keys[pq[size + 1]] = null;
        qp[pq[size + 1]] = -1;
        sink(1);
        return minIndex;
    }

    /**
     * 返回最小元素
     */
    public T min() {
        System.out.println(keys[pq[1]]);
        return keys[pq[1]];
    }

    /**
     * 返回最小元素索引
     */
    public int minIndex() {
        return pq[1];
    }

    /**
     * 修改索引处元素
     */
    public void change(int k, T key) {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    /**
     * 删除索引处元素
     */
    public T delete(int k) {
        T key = keys[k];
        exch(pq, qp[k], size);
        size--;
        sink(qp[k]);
        keys[k] = null;
        qp[k] = -1;
        return key;
    }

    /**
     * 验证索引是否存在
     */
    public boolean contains(int k) {
        return qp[k] != -1;
    }

    /**
     * k 小则上浮
     */
    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(pq, k, k / 2);
            k = k / 2;
        }
        qp[pq[k]] = k;
    }

    /**
     * k 大则下沉
     */
    private void sink(int k) {

        while (k * 2 <= size) {
            int son = k * 2;

            if (son < size && less(son + 1, son)) {
                son = son + 1;
            }
            if (less(k, son)) {
                break;
            }
            exch(pq, son, k);

            k = son;
        }
        qp[pq[k]] = k;
    }

    private boolean less(int a, int b) {
        return keys[pq[a]].compareTo(keys[pq[b]]) < 0;
    }

    /**
     * 交换数组 pq 中的索引
     */
    private void exch(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        IndexMinPQ<Integer> pq = new IndexMinPQ<>(16);
        pq.insert(0, 1);
        pq.insert(1, 6);
        pq.insert(2, 2);
        pq.insert(3, 0);
        pq.delMin();
        pq.min();
        pq.change(1, 0);
        pq.min();
        pq.delete(1);
        pq.min();
    }
}
