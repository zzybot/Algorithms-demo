package chapter2.section2.example;

import chapter2.tools.Utils;

/**
 * 算法2.4，自顶向下的归并排序
 * 通过递归，将大数组分解成小数组，再将小数组归并为一个大数组
 *
 * 执行次数：N*lgN/2 ~ N*lgN
 *
 * 依赖树的层数为 lgN，
 *
 * @author ZZY
 */
public class Merge {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int low, int high) {
        int mid = low + (high - low) / 2;

        if (low >= high) {
            return;
        }

        //排序左子数组和右子数组
        sort(a, low, mid);
        sort(a, mid + 1, high);

        //归并
        merge(a, low, mid, high);
    }

    private static void merge(Comparable[] a, int low, int mid, int high) {
        int indexLeft = low;
        int indexRight = mid + 1;

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
