package chapter2.section1.example;

import chapter2.section1.exercise.Ex24;
import chapter2.section1.exercise.Ex26;
import chapter2.tools.ArrayGenerator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 比较两种排序算法
 */
public class SortCompare {
    /**
     * 记录算法运行时间
     */
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if ("Insertion".equals(alg)) {
            Insertion.sort(a);
        }
        if ("Selection".equals(alg)) {
            Selection.sort(a);
        }
        if ("Shell".equals(alg)) {
            Shell.sort(a);
        }
        if ("InsertionGurdian".equals(alg)) {
            Ex24.Insertion(a);
        }
        return timer.elapsedTime();
    }

    /**
     * 返回算法排序 t 个长度为 n 的数组的总用时
     */
    public static double timeRandomInput(String alg, int n, int t) {
        double total = 0.0;
        Double[] a = new Double[n];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                a[j] = StdRandom.uniform();
            }
            //测试一个数组
            total += time(alg, a);
        }
        return total;
    }

    /**
     * Ex27
     */
    public static double timeRandomInputBy2(String alg, int t) {
        int n = 128;
        double total = 0.0;
        for (int i = 0; i < t; i++) {
            Double[] a = new Double[n];
            for (int j = 0; j < n; j++) {
                a[j] = StdRandom.uniform();
            }
            n *= 2;
            total += time(alg, a);
        }
        return total;
    }

    /**
     * Ex28，数组只能取两种值
     */
    public static double timeRandomInputTwoChoice(String alg, int n, int t) {
        double total = 0.0;
        for (int i = 0; i < t; i++) {
            int[] arr = ArrayGenerator.intsVrg(n, 1, 2);
            Integer[] a = new Integer[arr.length];

            for (int j = 0; j < arr.length; j++) {
                a[j] = arr[j];
            }
            //测试一个数组
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int n = Integer.parseInt(args[2]);
        int t = Integer.parseInt(args[3]);

        //算法 1 运行总时间
        double t1 = timeRandomInput(alg1, n, t);
        //算法 2 运行总时间
        double t2 = timeRandomInput(alg2, n, t);

        StdOut.printf("For %d random Doubles\n   %s is", n, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }
}
