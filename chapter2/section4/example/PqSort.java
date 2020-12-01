package chapter2.section4.example;

import chapter2.tools.Utils;

/**
 * 堆排序，注意堆排序用例不能直接使用
 * 因为 a[0] 不为空
 *
 * Ex19 接收一个数组作为参数，自底向上构造一个二叉堆
 */
public class PqSort {

    public static void sort(Comparable[] a) {
        Comparable[] pq = new Comparable[a.length + 1];
        //N
        System.arraycopy(a, 0, pq, 1, a.length);
        int length = a.length;
        //构造堆
        for (int i = length / 2; i > 0; i--) {
            sink(pq, i, length);
        }
        //排序
        while (length > 1) {
            Utils.exch(pq, 1, length);
            length--;
            sink(pq, 1, length);
        }
        //复制 N
        System.arraycopy(pq, 1, a, 0, a.length);
    }

    /**
     * 下沉
     */
    private static void sink(Comparable[] a, int k, int length) {
        while (k * 2 <= length) {
            int son = k * 2;

            //判断有没有右结点
            if (son < length && Utils.less(a[son], a[son + 1])) {
                son = son + 1;
            }
            if (Utils.less(a[k], a[son])) {
                Utils.exch(a, k, son);
            } else {
                break;
            }

            k = son;
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[5];
        a[0] = 9;
        a[1] = 5;
        a[2] = 2;
        a[3] = 8;
        a[4] = 1;

        sort(a);
        for (Integer i:a
             ) {
            System.out.println(i);
        }
    }
}
