package chapter2.section1.exercise;

import chapter2.section1.example.SortCompare;
import edu.princeton.cs.algs4.StdOut;

/**
 * 比较希尔排序与插入排序，数组长度以 2 的幂次递增
 * */
public class Ex27 {
    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int t = Integer.parseInt(args[2]);

        double t1 = SortCompare.timeRandomInputBy2(alg1, t);
        double t2 = SortCompare.timeRandomInputBy2(alg2, t);

        StdOut.printf("For random Doubles\n  %s is", alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
        /**
         * For random Doubles
         *   Shell is 123.7 times faster than Insertion
         * */
    }
}
