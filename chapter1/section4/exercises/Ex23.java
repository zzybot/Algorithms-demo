package chapter1.section4.exercises;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 分数的二分查找：
 * 要求：logN、有理数
 * 目标：p/q
 * <p>
 * 有理数是整数（正整数、0、负整数）和分数的统称，是整数和分数的集合。
 * 有理数的小数部分是有限或循环的，小数部分是无限不循环的数是无理数
 * <p>
 * 思路：与二分法相同，区别在于该题目只要求命中元素与目标元素 p/q 的距离在一定范围内就算命中
 * 也就是 Math.abs(a[mid] - key) <= 1.0/N^2 即可，（我没理解这里为什么是这样写条件，原话是两个分母小于 N 的有理数之差不小于 1/N^2）
 *
 * @author ZZY
 */
public class Ex23 {
    public static int binarySearch(double[] a, double key) {
        int first = 0;
        int last = a.length - 1;
        while (first <= last) {
            int mid = first + (last - first) / 2;
            double diff = Math.abs(a[mid] - key);
            double threshold = 1.0 / (a.length * a.length);
            //包括了 a[mid] == key 的情况
            if (diff <= threshold) {
                return mid;
            } else if (a[mid] > key) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 产生已排序随机数组
     */
    public static double[] sourceArr(int n) {
        double[] arr = new double[n];
        for (int i = 0; i < n; i++) {
            int p = StdRandom.uniform(1, 10);
            int q = StdRandom.uniform(1, 20);
            while (q <= p) {
                q = StdRandom.uniform(1, 20);
            }
            arr[i] = p * 1.0 / q;
        }

        Arrays.sort(arr);
        return arr;
    }

    /**
     * 打印数组
     */
    public static void printArray(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if ((i + 1) % 10 == 0) {
                StdOut.printf("%2.3f\n", arr[i]);
            } else {
                StdOut.printf("%2.3f ", arr[i]);
            }
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        double[] arr = sourceArr(10);
        printArray(arr);
        StdOut.println("result is " + binarySearch(arr, 4.0 / 7));
    }
}
