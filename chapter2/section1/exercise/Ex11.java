package chapter2.section1.exercise;

import java.util.ArrayList;

import static chapter2.tools.ArrayGenerator.IntegerToInt;

/**
 * 将希尔排序中使用的 增量 递增序列存入数组
 * @author ZZY
 */
public class Ex11 {
    public static final int BY_NUM = 3;

    public static void shell(int[] a) {
        int N = a.length, h = 1;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(h);

        while (h < N / BY_NUM) {
            list.add((h = 3 * h + 1));
        }

        int[] hs = IntegerToInt(list.toArray());
        //选取增量
        int cursor = hs.length - 1;

        while (cursor >= 0) {
            for (int i = hs[cursor]; i < N; i++) {
                for (int j = i; j >= hs[cursor] && a[j - 1] > a[j]; j--) {
                    int t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                }
            }
            cursor--;
        }
    }
}
