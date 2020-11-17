package chapter1.section4.exercises;

import edu.princeton.cs.algs4.*;

public class Ex03 {
    public static double timeTrial(int N) {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }
    public static void drawStd(int N) {
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, 1);
        StdDraw.setPenRadius(.001);
        for (int i = 1; i < N; i++) {
            StdDraw.point(i, timeTrial(i));
        }
    }
    public static void drawLgN(int N) {
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-10, 10);
        StdDraw.setPenRadius(.001);
        for (int i = 1; i < N; i++) {
            StdDraw.point(i, Math.log(timeTrial(i)));
        }
    }

    public static void main(String[] args) {
        drawStd(3000);
        drawLgN(3000);
    }
}
