package chapter2.section1.example;

import edu.princeton.cs.algs4.StdOut;

public class Utils {
    /**
     * 比较元素大小
     */
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换数组元素位置
     */
    public static void exch(Comparable[] a, int i, int j) {
        Comparable t;
        t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 打印数组
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    /**
     * 测试数组是否有序
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
