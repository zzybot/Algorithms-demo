package chapter2.section1.exercise;

import chapter2.tools.ArrayGenerator;
import edu.princeton.cs.algs4.StdOut;

/**
 * 昂贵的交换：
 * 条件：只有一个空位，单次交换的成本很高
 *
 * @author ZZY*/
public class Ex15 {
    /**
     * 应当尽量减少交换次数，那么应该使用选择排序，选择排序是移动数据最少的排序
     *
     * 根据题意交换时应当将 a[min] 先放到空位，再将 a[i] 放到 a[min]，最后将空位处的箱子搬到 a[i]
     * */

    private static long select = 0;
    public static void selection(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            select++;
            int t = a[min];
            a[min] = a[i];
            a[i] = t;
        }
    }
    private static long insert = 0;
    public static void insertion(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                insert++;
                int t = a[j];
                a[j] = a[j - 1];
                a[j - 1] = t;
            }
        }
    }
    private static long shell = 0;
    public static void shell(int[] a) {
        int n = a.length, h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
                    shell++;
                    int t = a[j];
                    a[j] = a[j - h];
                    a[j - h] = t;
                }
            }
            h /= 3;
        }
    }
    public static void test() {
        for (int i = 100; i < 10000; i += 100) {
            int[] arr = ArrayGenerator.ints(i);
            int[] copy1 = ArrayGenerator.copy(arr);
            int[] copy2 = ArrayGenerator.copy(arr);
            selection(arr);
            insertion(copy1);
            shell(copy2);
            long min = select < insert ? (Math.min(select, shell)) : (Math.min(insert, shell));
            StdOut.printf("规模 %d 的交换数比为 -->  选择排序 : 插入排序 : 希尔排序 = %d : %d : %d\n",
                    i, select / min, insert / min, shell / min);
            select = insert = shell = 0;
        }
    }
    public static void main(String[] args) {
        /*
         * 应该用选择排序，不论在任何情况下，都只需要 N 次交换
         * 当我们考虑实际问题时，交换的次数小于等于 N,
         * 毕竟如果查找的序列中没有比假定最小的小，那么不用自己和自己交换
         * 很显然，希尔排序和插入排序的交换次数都有可能大于 N
         */
        test();
    }
}
