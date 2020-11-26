package chapter2.section2.exercise;

import chapter2.tools.Utils;

/**
 * 线程安全的 aux
 *
 * 思路：将 aux 作为参数传递给 sort 方法
 *
 * @author ZZY*/
public class Ex9 {

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int low, int high) {
        int mid = low + (high - low) / 2;

        if (low >= high) {
            return;
        }

        //排序左子数组和右子数组
        sort(a, aux, low, mid);
        sort(a, aux, mid + 1, high);

        //归并
        merge(a, aux, low, mid, high);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
        int indexLeft = low;
        int indexRight = high;

        if (high + 1 - low >= 0) {
            System.arraycopy(a, low, aux, low, high + 1 - low);
        }

        //将两个子数组以正确的顺序合并
        for (int i = low; i <= high; i++) {
            //左子数组归并结束后直接归并右子数组
            if (indexLeft > mid) {
                a[i] = aux[indexRight++];
                //右子数组归并结束后直接归并左子数组
            } else if (indexRight > high) {
                a[i] = aux[indexLeft++];
                //aux[indexRight]较小时归并至 a[i]
            } else if (Utils.less(aux[indexRight], aux[indexLeft])) {
                a[i] = aux[indexRight++];
                //aux[indexLeft]较小时归并至 a[i]
            } else {
                a[i] = aux[indexLeft++];
            }
        }
    }
}
