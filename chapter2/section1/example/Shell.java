package chapter2.section1.example;

/**
 * 算法2.3：希尔排序，增量递减的插入排序
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int n = a.length;
        //增量
        int h = 1;

        //为什么不是 h = n/3;?“无法证明某个递增序列是最好的，直接用即可”
        //该数组将被分为 n/h 个子数组，子数组越多说明有序程度越高
        while (h < (n / 3)) {
            h = h * 3 + 1;
        }

        //希尔排序实质上就是使插入排序的输入相对有序，插入排序的增量递减，数组的有序程度递增
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && Utils.less(a[j], a[j - h]); j -= h) {
                    Utils.exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
