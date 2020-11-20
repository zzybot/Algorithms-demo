package chapter1.section4.exercises;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 快速 3-sum，要求：
 * 线性级别，和为 0 的整数对的个数
 * 前提：数组有序
 * <p>
 * 先实现线性级别的 twosum，再实现平方级别的 3-sum
 */
public class Ex15 {

    /**
     * 思路：和为零说明两个数一正一负或者都为零，数组有序，负数在左侧正数在右侧，
     * 两个索引一个从零开始，一个从数组尾开始向中间遍历
     */
    public static int twoSumCount(int[] a) {
        if (a == null || a[0] > 0) {
            return 0;
        }
        int first = 0;
        int last = a.length - 1;
        int count = 0;
        while (last > first) {
            if (a[last] + a[first] < 0) {
                first++;
            } else if (a[last] + a[first] > 0) {
                last--;
            } else {
                //相等时，last 向左推进找到所有与 a[last] 相等的正数，计数为 positiveCount
                //first 向右推进找到所有与 a[first] 相等的负数，计数为 negativeCount
                //整数对的个数为 positiveCount * negativeCount
                int preFirst = first;
                int preLast = last;
                int positiveCount;
                int negativeCount;

                //如果该数组中有 0，则当两指针相等且均为 0 时，计算 0 的个数并得到整数对的个数，最后返回 count
                if (a[first] == 0) {
                    int zeroNum = last - first + 1;
                    count += zeroNum * (zeroNum - 1) / 2;
                    return count;
                }
                while (a[first] == a[preFirst]) {
                    first++;
                }
                while (a[last] == a[preLast]) {
                    last--;
                }
                positiveCount = preLast - last;
                negativeCount = first - preFirst;
                count += positiveCount * negativeCount;
            }
        }
        return count;
    }

    /**
     * 三个整数和为 0
     */
    public static int threeSumCount(int[] a) {
        if (a == null || a[0] > 0) {
            return 0;
        }

        int count = 0;
        out:
        for (int i = 0; i < a.length; i++) {
            //保证 i < first < last，避免重复记录
            int first = i + 1;
            int last = a.length - 1;

            while (last > first) {
                if (a[last] + a[first] + a[i] < 0) {
                    first++;
                } else if (a[last] + a[first] + a[i] > 0) {
                    last--;
                } else {
                    //相等时，last 向左推进找到所有与 a[last] 相等的正数，计数为 positiveCount
                    //first 向右推进找到所有与 a[first] 相等的负数，计数为 negativeCount
                    //整数对的个数为 positiveCount * negativeCount
                    int preFirst = first;
                    int preLast = last;
                    int positiveCount;
                    int negativeCount;

                    //当 a[i] a[first] a[last] 均为 0 时，计算 0 的个数并得到三整数组的个数，最后返回 count
                    if (a[i] == 0) {
                        int zeroNum = last - i + 1;
                        count += count(zeroNum);
                        //直接停止外部 for 循环
                        break out;
                    }
                    while (a[first] == a[preFirst]) {
                        first++;
                    }
                    while (a[last] == a[preLast]) {
                        last--;
                    }
                    positiveCount = preLast - last;
                    negativeCount = first - preFirst;
                    count += positiveCount * negativeCount;
                }
            }
        }
        return count;
    }

    //用于计算 N 个 0 中有几个整数组
    private static int count(int n) {
        if (n < 3){
            return 0;
        }
        if (n == 3) {
            return 1;
        }
        int i = n - 1;
        return count(i) + (i * (i - 1) / 2);
    }

    public static int[] sourceArr(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = StdRandom.uniform(-10, 10);
        }
        Arrays.sort(arr);
        return arr;
    }

    /**
     * -9 -9 -8 -8 -7 -6 -5 -5 -3 -3 -1 0 0 1 3 3 3 4 7 7
     * 10
     * 5
     */
    public static void main(String[] args) {
        int[] a = sourceArr(10);
        for (int i : a
        ) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(twoSumCount(a));
        System.out.println(threeSumCount(a));
    }
}
