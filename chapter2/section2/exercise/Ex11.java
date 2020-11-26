package chapter2.section2.exercise;

import chapter2.tools.ArrayGenerator;
import chapter2.tools.Utils;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 实现三项改进：
 * 1.加快小数组的排序速度：子数组长度小于 15 时使用插入排序
 * 2.检测数组是否已经有序：ex8
 * 3.省去每次归并复制数组的过程：将输入数组中的元素归并到辅助数组，再把辅助数组赋值给输入数组（并没有省，没写）
 */
public class Ex11 {

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

        //改进 2：减少有序字数组之间的比较次数
        if (Utils.less(a[mid], a[mid + 1])) {
            return;
        }

        //改进 1：使用插入排序
        if (high - low + 1 < 15) {
            insertion(a, low, high);
        }

        //归并
        merge(a, aux, low, mid, high);
    }

    private static void insertion(Comparable[] a, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            for (int j = i; j > low && Utils.less(a[j], a[j - 1]); j--) {
                Utils.exch(a, j, j - 1);
            }
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
        int indexLeft = low;
        int indexRight = mid + 1;

        if (high + 1 - low >= 0) {
            System.arraycopy(a, low, aux, low, high + 1 - low);
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

    public static void main(String[] args) {
        Integer[] a = ArrayGenerator.Integers(100000);

        for (Integer d : a
        ) {
            System.out.print(d + " ");
        }
        System.out.println();

        Stopwatch timer = new Stopwatch();
        sort(a);
        double t = timer.elapsedTime();

        for (Integer d : a
        ) {
            System.out.print(d + " ");
        }
        System.out.println();
        System.out.println("用时：" + t);
    }
}
