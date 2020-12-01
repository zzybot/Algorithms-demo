package chapter2.section4.excerise;

import chapter2.tools.Utils;

import java.util.NoSuchElementException;

/**
 * 动态数组实现二叉堆
 */
public class ResizingMaxPq<Key extends Comparable<Key>> {
    /**
     * 基于堆的完全二叉树
     */
    private Key[] pq;
    /**
     * 优先队列长度
     */
    private int size = 0;

    public ResizingMaxPq(int length) {
        //pq[0] 不使用，所以长度加一
        pq = (Key[]) new Comparable[length + 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 插入
     */
    public void insert(Key value) {
        if (size == pq.length - 1) {
            resize(pq.length * 2);
        }
        //不使用 pq[0]，元素从 pq[1] 开始插入
        size++;
        pq[size] = value;
        swim(size);
    }

    /**
     * 删除
     */
    public Key deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("pq is empty");
        }
        if (size == (pq.length - 1) / 4) {
            resize(pq.length / 2);
        }
        Key max = pq[1];
        //交换堆头和堆尾
        Utils.exch(pq, 1, size);
        //删除
        pq[size] = null;
        size--;
        //下沉
        sink(1);
        System.out.println(max);
        return max;
    }

    /**
     * 上浮恢复堆有序
     */
    private void swim(int k) {

        while (k > 1 && Utils.less(pq[k / 2], pq[k])) {
            Utils.exch(pq, k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 下沉恢复堆有序
     */
    private void sink(int k) {
        //判断有没有下一层
        while (k * 2 <= size) {
            int sun = k * 2;

            if (sun < size && Utils.less(pq[sun], pq[sun + 1])) {
                sun = sun + 1;
            }
            if (Utils.less(pq[sun], pq[k])) {
                break;
            }
            Utils.exch(pq, k, sun);
            k = sun;
        }
    }

    private void resize(int length) {
        Key[] temp = (Key[]) new Comparable[length];
        System.arraycopy(pq, 1, temp, 1, size);
        pq = temp;
    }

    public static void main(String[] args) {
        ResizingMaxPq<Integer> pq = new ResizingMaxPq<>(2);
        pq.insert(1);
        pq.insert(6);
        pq.insert(5);
        pq.insert(2);
        pq.insert(0);
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
    }
}
