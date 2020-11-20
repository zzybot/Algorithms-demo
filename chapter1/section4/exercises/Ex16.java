package chapter1.section4.exercises;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 在 double 数组中找出两者之差（绝对值）最小的两个数
 * 思路：先排序，再循环找绝对值之差最小的两个数
 */
public class Ex16 {
    public static double[] getAbsMin(double[] a) {
        double[] temp = Arrays.copyOfRange(a, 0, a.length);
        double[] result = new double[2];
        // O(N*logN)
        Arrays.sort(temp);

        double absMin = temp[1] - temp[0];
        //O(N)
        for (int i = 1; i < temp.length - 1; i++) {
            double abs = temp[i + 1] - temp[i];
            if (abs < absMin){
                result[0] = temp[i];
                result[1] = temp[i + 1];
                absMin = abs;
            }
        }
        StdOut.printf("closest pair is %8.3f %8.3f", result[0], result[1]);
        return result;
    }
    /**
     * 产生已排序随机数组
     */
    public static double[] sourceArr(int N) {
        double[] arr = new double[N];
        for (int i = 0; i < N; i++) {
            arr[i] = StdRandom.uniform(-100.0, 100.0);
        }
        return arr;
    }

    /**
     * 打印数组
     */
    public static void printArray(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if ((i + 1) % 10 == 0) {
                StdOut.printf("%8.3f\n", arr[i]);
            } else {
                StdOut.printf("%8.3f", arr[i]);
            }
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        double[] arr = sourceArr(10);
        printArray(arr);
        getAbsMin(arr);
    }
}
