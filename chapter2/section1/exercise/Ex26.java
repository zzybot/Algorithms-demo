package chapter2.section1.exercise;


import chapter2.tools.Utils;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import static chapter2.tools.ArrayGenerator.IntegerToInt;
import static chapter2.tools.ArrayGenerator.Integers;

/**
 * int 版本的插入排序，测试与原版的性能差距
 */
public class Ex26 {
    public static double InsertionInts(int[] a) {
        Stopwatch timer = new Stopwatch();
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                int temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
        return timer.elapsedTime();
    }

    public static double Insertion(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        // N-1
        for (int i = 1; i < a.length; i++) {
            //1+2+3+4+...+(N-1) = (N-1)/2 * N = N*(N-1)/2 = ~N^2
            for (int j = i; j > 0 && Utils.less(a[j], a[j - 1]); j--) {
                Utils.exch(a, j, j - 1);
            }
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        Integer[] arr = Integers(0, 40000);
        int[] arr1 = IntegerToInt(arr);
        StdOut.printf("基本数据类型 : %f\n", InsertionInts(arr1));
        StdOut.printf("包装类 : %f\n", Insertion(arr));
    }
}
