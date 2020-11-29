package chapter2.section4.example;

import chapter2.tools.Utils;

/**
 * 基于堆的优先队列
 *
 * @author ZZY
 */
public class MaxPq<Key extends Comparable<Key>> {
    /**
     * 基于堆的完全二叉树
     */
    private final Key[] pq;
    /**
     * 优先队列长度
     */
    private int size = 0;

    public MaxPq(int length) {
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
        //不使用 pq[0]，元素从 pq[1] 开始插入
        size++;
        pq[size] = value;
        swim(size);
    }

    /**
     * 删除
     */
    public Key deleteMax() {
        Key max = pq[1];
        //交换堆头和堆尾
        Utils.exch(pq, 1, size);
        //删除
        pq[size] = null;
        size--;
        //下沉
        sink(1);
        return max;
    }

    /**
     * 上浮恢复堆有序
     */
    private void swim(int k) {

        while (k > 0 && Utils.less(pq[k / 2], pq[k])) {
            Utils.exch(pq, k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 下沉恢复堆有序
     */
    public void sink(int k) {
        //判断有没有下一层
        while (k * 2 <= size) {
            int sun = k * 2;
            if (Utils.less(pq[sun], pq[sun + 1])) {
                sun = sun + 1;
            }
            if (Utils.less(pq[sun], pq[k])) {
                break;
            }
            Utils.exch(pq, k, sun);
            k = sun;
        }
    }
}
