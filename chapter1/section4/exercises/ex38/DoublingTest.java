package chapter1.section4.exercises.ex38;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 对比两种 3-sum 算法，编写测试用例，得出两种算法运行时间的比例
 * */
public class DoublingTest {
    static int[] souceArr(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = StdRandom.uniform(-1000, 1000);
        }
        return arr;
    }
    static class NaiveThreeSum {
        static int count(int[] a) {
            int N = a.length;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        if (i < j && j < k) {
                            if (a[i] + a[j] + a[k] == 0) {
                                cnt++;
                            }
                        }
                    }
                }
            }
            return cnt;
        }
    }
    static class BruteForceThreeSum {
        static int count(int[] a) {
            int N = a.length;
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    for (int k = j + 1; k < N; k++) {
                        if (a[i] + a[j] + a[k] == 0) {
                            cnt++;
                        }
                    }
                }
            }
            return cnt;
        }
    }
    static class DoublingRatioTest {
        static double timeTrial_Naive(int[] arr) {
            Stopwatch timer = new Stopwatch();
            int cnt = NaiveThreeSum.count(arr);
            return timer.elapsedTime();
        }
        static double timeTrial_BruteForce(int[] arr) {
            Stopwatch timer = new Stopwatch();
            int cnt = NaiveThreeSum.count(arr);
            return timer.elapsedTime();
        }
    }
    public static void main(String[] args) {
        double naivePrev = 1.0, bruteForcePrev = 1.0;
        for (int i = 200, j = 0; j < 7; j++, i += i) {
            int[] arr = souceArr(i);
            double naiveTime = DoublingRatioTest.timeTrial_Naive(arr);
            double bruteForceTime = DoublingRatioTest.timeTrial_Naive(arr);
            double naiveRatio = naiveTime / naivePrev;
            double bruteForceRatio = bruteForceTime / bruteForcePrev;
            naivePrev = naiveTime;
            bruteForcePrev = bruteForceTime;
            StdOut.printf("Naive --> N = %d  Ratio: %f TotalTime : %f秒\n",
                    i, naiveRatio, naiveTime);
            StdOut.printf("Brute --> N = %d  Ratio : %f TotalTime : %f秒\n",
                    i, bruteForceRatio, bruteForceTime);
            StdOut.println();
        }

        /**
         * Naive --> N = 200  Ratio: 0.032000 TotalTime : 0.032000秒
         * Brute --> N = 200  Ratio : 0.031000 TotalTime : 0.031000秒
         *
         * Naive --> N = 400  Ratio: 1.437500 TotalTime : 0.046000秒
         * Brute --> N = 400  Ratio : 1.516129 TotalTime : 0.047000秒
         *
         * Naive --> N = 800  Ratio: 8.173913 TotalTime : 0.376000秒
         * Brute --> N = 800  Ratio : 6.978723 TotalTime : 0.328000秒
         *
         * Naive --> N = 1600  Ratio: 5.989362 TotalTime : 2.252000秒
         * Brute --> N = 1600  Ratio : 4.335366 TotalTime : 1.422000秒
         *
         * Naive --> N = 3200  Ratio: 4.971581 TotalTime : 11.196000秒
         * Brute --> N = 3200  Ratio : 8.099156 TotalTime : 11.517000秒
         * */
    }
}
