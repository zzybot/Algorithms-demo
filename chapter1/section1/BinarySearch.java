package binary;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BinarySearch {

    public static int rank(int key, int[] a) {
        int first = 0;
        int last = a.length - 1;

        while (first <= last) {

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

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if (rank(key,whitelist)<0){
                StdOut.println(key);
            }
        }
        long endTime = System.nanoTime();
        System.out.println(endTime-startTime + "ns");

    }
}
