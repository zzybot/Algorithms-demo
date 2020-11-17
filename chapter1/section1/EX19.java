package chapter1;

import edu.princeton.cs.algs4.StdOut;

public class EX19 {
    
    public static long f(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        /*for (int N = 0; N < 100; N++) {
            StdOut.println(N + "：" + f(N));
        }*/

        long[] fibonacci = fArray(99);
        for (int i = 0; i < fibonacci.length; i++) {
            StdOut.println(i + ":" + fibonacci[i]);
        }
    }

    /**
     * 将以上方法转为数组形式，用数组的存取方法实现递归
     * */
    public static long[] fArray(int N) {
        long[] fibonacci = new long[N + 1];
        if (N == 0) {
            return fibonacci;
        }
        fibonacci[1] = 1;
        if (N == 1) {
            return fibonacci;
        }
        for (int i = 2; i <= N; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }
}
