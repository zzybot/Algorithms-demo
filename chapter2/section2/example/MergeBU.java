package chapter2.section2.example;

import chapter2.tools.Utils;

/**
 * 算法2.4+，自底向上的归并排序
 * 直接从小数组开始，递推归并为一个大数组
 *
 * 执行次数：N*lgN/2 ~ N*lgN
 *
 * @author ZZY
 */
public class MergeBU {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {

        int n = a.length;
        //辅助数组
        aux = new Comparable[n];

        // size 子数组长度
        for (int size = 1; size < n; size += size) {
            //从左至右依次归并，若子数组数量不为偶数，则多出来的子数组放置暂时不归并
            for (int low = 0; low < n - size; low += (size + size)) {
                int mid = low + size - 1;
                int high = Math.min(low + size + size - 1, n - 1);
                //最后一步归并，两个子数组要么一样大，要么一大一小
                //如果是一大一小，high == n-1;
                merge(a, low, mid, high);
            }
        }
    }

    private static void merge(Comparable[] a, int low, int mid, int high) {

        int indexLeft = low;
        int indexRight = mid + 1;

        if (high - low + 1 >= 0) {
            //复制数组元素
            System.arraycopy(a, low, aux, low, high - low + 1);
        }

        for (int i = low; i <= high; i++) {
            if (indexLeft > mid) {
                a[i] = aux[indexRight++];
            } else if (indexRight > high) {
                a[i] = aux[indexLeft++];
            } else if (Utils.less(aux[indexRight], aux[indexLeft])) {
                a[i] = aux[indexRight++];
            } else {
                a[i] = aux[indexLeft++];
            }
        }
    }
}
