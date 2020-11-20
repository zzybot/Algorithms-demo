package chapter1.section4.exercises;

import edu.princeton.cs.algs4.StdOut;

public class Ex18 {
    /**
     * 思路 :
     *
     * 二分查找，若 mid 处于一个单调递增三元组，就让 hi = mid - 1
     * 如果 mid 处于一个单调递减三元组，就让 lo = mid + 1
     * 如果 mid 是一个局部最大值，就随便选一边
     * 否则就返回 mid
     *
     * 数组元素各不相同，有两个特殊情况，数组元素单调递增，则a[0]为局部最小值
     * 数组元素单调递减，则a[a.length-1] 为局部最小值
     *
     * 如果找较大元素的一侧，有可能遇到单调递增的情况
     */
    public static int localMinimum(int[] a) {
        if (a == null || a.length < 2) {
            throw new IllegalArgumentException();
        }
        if (a[0] < a[1]) {
            return a[0];
        }
        if (a[a.length - 2] > a[a.length - 1]) {
            return a[a.length - 1];
        }
        int lo = 1, hi = a.length - 2, mid = 0;
        while (lo <= hi) {
            mid = (lo + hi) / 2;
            if       (a[mid] < a[mid - 1] && a[mid] < a[mid + 1]) {
                return a[mid];
            } else if  (a[mid - 1] < a[mid] && a[mid] < a[mid + 1]) {
                hi = mid - 1;
            } else if  (a[mid - 1] > a[mid] && a[mid] > a[mid + 1]) {
                lo = mid + 1;
            } else {
                lo = mid + 1;
            }
            StdOut.printf("mid = %d lo = %d hi = %d\n", mid, lo, hi);
        }
        return -1;
    }
}
