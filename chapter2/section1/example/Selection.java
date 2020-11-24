package chapter2.section1.example;


import edu.princeton.cs.algs4.StdOut;



/**
 * 算法2.1：选择排序，循环选择剩余元素的最小（大）值
 *
 * 特点：
 * 1.运行时间与输入无关：无论数组本身是否已经有序，或数组元素全部相等，都要执行 N^2/2 次的元素比较。
 * 2.数据移动次数最少：交换元素的次数与数组长度是线性关系，会执行 N-1 次交换
 *
 * @author ZZY
 */
public class Selection {

    /**
     * 选择排序，递增
     */
    public static void sort(Comparable[] a) {
        // 一定会进行 N 次选位
        for (int i = 0; i < a.length; i++) {
            int min = i;
            // 每次选位都要执行 N - i - 1 次比较
            for (int j = i + 1; j < a.length; j++) {
                if (Utils.less(a[j], a[min])) {
                    min = j;
                }
            }
            //一定会执行 N 次交换
            Utils.exch(a, i, min);
        }
    }
}
