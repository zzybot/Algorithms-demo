package chapter2.section1.exercise;

import chapter2.section1.example.SortCompare;
import edu.princeton.cs.algs4.StdOut;

/**
 * 数组元素只能取两种值，这两种值出现的概率相等
 * */
public class Ex28 {
    public static void main(String[] args) {
        double t1 = SortCompare.timeRandomInputTwoChoice("Insertion",10000,100);
        double t2 = SortCompare.timeRandomInputTwoChoice("Selection",10000,100);

        StdOut.printf("For random Integers\n  %s is", "Insertion");
        StdOut.printf(" %.1f times faster than %s\n", t2 / t1, "Selection");
        /**
         * For random Integers
         *   Insertion is 1.4 times faster than Selectioned
         * */
    }
}
