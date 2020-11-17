package chapter1.section4.example;

import binary.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ThreeSumFast {
    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }

    //思路：将前两个整数分为一组，找与它们和为 0 的第三个整数
    public static int count(int[] a) {
        //N*logN
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                //N^2*logN，二分查找第三个整数
                if (BinarySearch.rank(-a[i] - a[j], a) > j){
                    count++;
                }
            }
        }
        return count;
    }
}
