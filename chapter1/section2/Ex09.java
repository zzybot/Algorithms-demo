package chapter1.section2;

import edu.princeton.cs.algs4.Counter;


/**
 * @author ZZY
 * 1.2 章的第九题
 */
public class Ex09 {
    public static void main(String[] args) {

    }

    public static int rank(int key, int[] a, Counter counter) {
        int first = 0;
        int last = a.length - 1;

        while (first <= last) {
            counter.increment();

            int mid = first + (last - first) / 2;

            if (key < a[mid]) {
                last = mid - 1;
            } else if (key > a[mid]) {
                first = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
