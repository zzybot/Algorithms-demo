package chapter2.section1.exercise;

import chapter2.tools.Utils;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Ex16 {
    public static boolean check(Comparable[] a, String alg) {
        Comparable[] b = Arrays.copyOf(a, a.length);

        if ("Selection".equals(alg)) {
            Selection(a);
        }
        if ("Insertion".equals(alg)) {
            Insertion(a);
        }
        if ("Shell".equals(alg)) {
            Shell(a);
        }
        return Utils.isSorted(a) && eachCheck(b,a);
    }

    private static boolean eachCheck(Comparable[] a, Comparable[] b) {
        if (a == null || b == null || a.length != b.length) {
            return false;
        }

        for (int i = 0; i < a.length; i++) {
            int count = 0;
            for (int j = 0; j < b.length; j++) {
                if (a[i].equals(b[j])){
                    count++;
                }
            }
            if (count == 0){
                return false;
            }
        }
        return true;
    }

    private static void Selection(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (Utils.less(a[j], a[min])) {
                    min = j;
                }
            }
            Utils.exch(a, i, min);
        }
    }

    private static void Insertion(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0 && Utils.less(a[j], a[j - 1]); j--) {
                Utils.exch(a, j, j - 1);
            }
        }
    }

    private static void Shell(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && Utils.less(a[j], a[j - h]); j -= h) {
                    Utils.exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Double[] d = new Double[]{3.0, 2.0, 1.0, 4.0};
        StdOut.println(check(d, "Selection"));
    }
}
