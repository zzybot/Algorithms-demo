package chapter2.section1.exercise;

import chapter2.tools.Utils;

import java.util.ArrayList;

import static chapter2.tools.ArrayGenerator.Doubles;
import static chapter2.tools.ArrayGenerator.IntegerToInt;

/**
 * 打印希尔排序中每个增量对应的比较次数和数组长度的比值
 * */
public class Ex12 {
    public static final int BY_NUM = 3;

    public static void shell(Double[] a) {
        int N = a.length, h = 1;

        System.out.println("=============="+"数组规模：" + N + "============");

        ArrayList<Integer> list = new ArrayList<>();
        list.add(h);

        while (h < N / BY_NUM) {
            list.add((h = 3 * h + 1));
        }

        int[] hs = IntegerToInt(list.toArray());
        //选取增量
        int cursor = hs.length - 1;

        while (cursor >= 0) {
            int count = 0;
            for (int i = hs[cursor]; i < N; i++) {
                for (int j = i; j >= hs[cursor] && a[j - 1] > a[j]; j--) {
                    double t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                    count++;
                }
                count++;
            }
            System.out.println("增量为：" + hs[cursor] + "，比较次数与数组长度的比值为：" + count/N);
            cursor--;
        }
    }

    public static void main(String[] args) {
        for (int i = 2; i < 8; i++) {
            Double[] d = Doubles((int)Math.pow(10, i));
            shell(d);
            assert Utils.isSorted(d);
        }
    }

    /**
     ==============数组规模：100============
     增量为：40，比较次数与数组长度的比值为：8
     增量为：13，比较次数与数组长度的比值为：11
     增量为：4，比较次数与数组长度的比值为：4
     增量为：1，比较次数与数组长度的比值为：2
     ==============数组规模：1000============
     增量为：364，比较次数与数组长度的比值为：100
     增量为：121，比较次数与数组长度的比值为：89
     增量为：40，比较次数与数组长度的比值为：36
     增量为：13，比较次数与数组长度的比值为：13
     增量为：4，比较次数与数组长度的比值为：5
     增量为：1，比较次数与数组长度的比值为：2
     ==============数组规模：10000============
     增量为：9841，比较次数与数组长度的比值为：0
     增量为：3280，比较次数与数组长度的比值为：1137
     增量为：1093，比较次数与数组长度的比值为：845
     增量为：364，比较次数与数组长度的比值为：345
     增量为：121，比较次数与数组长度的比值为：119
     增量为：40，比较次数与数组长度的比值为：36
     增量为：13，比较次数与数组长度的比值为：14
     增量为：4，比较次数与数组长度的比值为：7
     增量为：1，比较次数与数组长度的比值为：2
     ==============数组规模：100000============
     增量为：88573，比较次数与数组长度的比值为：326
     增量为：29524，比较次数与数组长度的比值为：12105
     增量为：9841，比较次数与数组长度的比值为：7872
     增量为：3280，比较次数与数组长度的比值为：3121
     增量为：1093，比较次数与数组长度的比值为：1059
     增量为：364，比较次数与数组长度的比值为：346
     增量为：121，比较次数与数组长度的比值为：121
     增量为：40，比较次数与数组长度的比值为：41
     增量为：13，比较次数与数组长度的比值为：14
     增量为：4，比较次数与数组长度的比值为：5
     增量为：1，比较次数与数组长度的比值为：1
     ==============数组规模：1000000============

     Process finished with exit code -1

     * */
}
