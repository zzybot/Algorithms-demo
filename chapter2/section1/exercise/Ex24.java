package chapter2.section1.exercise;

import chapter2.tools.Utils;

/**
 * 插入排序的哨兵
 * 先找出最小元素放在最左边，省去 j<0 的边界判断
 */
public class Ex24 {
    public static void Insertion(Comparable[] a) {
        int n = a.length;
        int min = 0;
        for (int i = 0; i < n; i++) {
            if (Utils.less(a[i], a[min])) {
                min = i;
            }
        }
        //最小的元素放在数组头
        Utils.exch(a, 0, min);

        //插入排序
        for (int i = 1; i < n; i++) {
            for (int j = i; Utils.less(a[j], a[j - 1]); j--) {
                Utils.exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        /**
         * For 1000 random Doubles
         *    Insertion is 1.4 times faster than InsertionGurdian
         *
         *
         * */
    }
}
