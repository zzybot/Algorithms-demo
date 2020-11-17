package chapter1.section4.exercises;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 求整数对个数，要求该整数对中的两个整数相等
 *
 * @author ZZY
 */
public class Ex08 {
    /**
     * 暴力法解 O(N^2)
     */
    public static int count(int[] a) {
        Stopwatch timer = new Stopwatch();
        int count = 0;
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (a[i] == a[j]) {
                    count++;
                }
            }
        }
        StdOut.println(String.format("slow way result: %d\ntotal time : %f\n=============", count, timer.elapsedTime()));
        return count;
    }

    /*
     * O(N*logN) 循环 + 二分查找
     * 错误，二分查找找到一个数就停止，我们需要找到所有符合条件的整数对
     */
   /* public static int countFast(int[] a) {
        Stopwatch timer = new Stopwatch();
        Arrays.sort(a);
        int count = 0;
        int N = a.length;
        for (int i = 0; i < N; i++) {
            if (BinarySearch.rank(a[i], a) > i) {
                count++;
            }
        }
        StdOut.println(String.format("slow way result: %d\ntotal time : %f\n=============", count, timer.elapsedTime()));

        return count;
    }*/

    /**
     * O(N*logN)，常量 LAST 用于判断，避免代码中出现魔法值
     * 我并不知道这个整数 2 代表什么意思，只有把这个整数带入后面的代码逻辑才能判断该值的真实意义，
     * 而且如果多次出现，这就大大的降低了代码的可阅读性。所以我们在代码中应该极力避免产生魔法值。
     */
    private static final int LAST = 2;

    public static long countFast(int[] a) {
        //数组至少有两个元素
        if (a == null || a.length < LAST) {
            return 0;
        }
        //开启计时器
        Stopwatch timer = new Stopwatch();

        //归并排序 O(N*logN)
        Arrays.sort(a);
        long count = 0;
        //两个指针
        int previous;
        int current = 1;
        //比较相邻的两个元素，如果相等，就继续找出后续所有相等的元素。O(N)
        while (current < a.length) {
            previous = current - 1;
            if (a[previous] != a[current]) {
                current++;
            } else {
                while (current < a.length && a[previous] == a[current]) {
                    current++;
                }
                //equalCount 所有相等的元素的个数，包括 previous
                int equalCount = current - previous;
                //整数对个数为：(N-1)+(N-2)+(N-3)+...+0 = N/2 * (N-1)
                count += equalCount * (equalCount - 1) / 2;
            }
        }
        StdOut.println(String.format("Fast way result : %d\ntotal time : %f\n================",
                count, timer.elapsedTime()));
        return count;
    }

    /**
     * O(N)，使用 hashmap
     */
    public static long countExtreme(int[] a) {
        //元素为键，该元素的个数为值
        Map<Integer, Integer> valuesMap = new HashMap<>(16);

        //开启计时
        Stopwatch timer = new Stopwatch();
        //O(N)
        for (int j : a) {
            //某整数的个数
            int count = 0;
            //如果 hashmap 中有该整数，就将整数个数 +1，如果没有直接存 1。
            if (valuesMap.containsKey(j)) {
                count = valuesMap.get(j);
            }
            count++;
            valuesMap.put(j, count);
        }
        //整数对个数
        long equalNumberCount = 0;
        //O(N)
        for (Integer i : valuesMap.keySet()
        ) {
            if (valuesMap.get(i) > 1) {
                int n = valuesMap.get(i);
                equalNumberCount += n * (n - 1) / 2;
            }
        }
        StdOut.println(String.format("Extreme way result : %d\ntotal time : %f\n================",
                equalNumberCount, timer.elapsedTime()));
        return equalNumberCount;
    }

    public static int[] sourceArr(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = StdRandom.uniform(0, 10);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] a = sourceArr(160000);
        count(a);
        countFast(a);
        countExtreme(a);
    }
}
