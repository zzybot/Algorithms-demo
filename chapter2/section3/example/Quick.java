package chapter2.section3.example;

import chapter2.tools.Utils;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 算法 2.5：快速排序
 */
public class Quick {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int low, int high) {
        //停止递归
        if (low >= high) {
            return;
        }
        int split = partiton(a, low, high);
        sort(a, low, split);
        sort(a, split + 1, high);
    }

    private static int partiton(Comparable[] a, int low, int high) {
        Comparable v = a[low];
        int left = low + 1;
        int right = high;
        while (true) {
            while (Utils.less(a[left], v)) {
                left++;
                if (left == high) {
                    break;
                }
            }
            while (Utils.less(v, a[right])) {
                right--;
            }
            if (left >= right) {
                break;
            }
            Utils.exch(a, left, right);
        }
        //a[right] 一定小于等于 v
        Utils.exch(a, low, right);
        return right;
    }
}
