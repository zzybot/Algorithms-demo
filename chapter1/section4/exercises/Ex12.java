package chapter1.section4.exercises;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZZY
 *
 * 数组本身是有序的
 */
public class Ex12 {

    /**
     * 使用 hashmap，元素为键。
     * O(N)
     */
    public static void printCommon(int[] a, int[] b) {
        Map<Integer, Integer> valueMap = new HashMap<>(16);
        //先循环一个数组存入 hashmap，所有键对应的值都是 1
        for (Integer i : a
        ) {
            int count = 1;
            valueMap.put(i, count);
        }
        //再循环第二个数组，如果有重复的键，就直接打印
        for (Integer i : b
        ) {
                if (valueMap.containsKey(i)) {
                    StdOut.print(i + " ");
                    //已经打印过的就删除防止二次打印
                    valueMap.remove(i);
                }
        }
    }

    /**
     * 不使用 hashmap，当数组中元素有重复时，就可能重复打印公共元素,例如：
     *
     * 两个数组的元素：
     * 2 5 6 7 16 20 21 28 34 41 47 69 74 84 87 92 96 97 97 98
     * 8 12 19 24 25 26 28 30 33 33 54 58 63 82 84 89 93 93 97 97
     * 公共元素：
     * 28 84 97
     * 28 84 97 97
     *
     * 因为数组 a 中有相邻相等的元素 97，且数组 b 中也有相邻相等的元素 97，结果会打印两次 97
     *
     * O(N)
     */
    public static void printCommon2(int[] a, int[] b) {
        if (a == null || b == null) {
            throw new NullPointerException();
        }
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            //此时，b 中索引小于 j 的都不可能是公共元素
            if (a[i] > b[j]) {
                j++;
                //此时，a 中索引小于 i 的都不可能是公共元素
            } else if (a[i] < b[j]) {
                i++;
                //a[i] == b[j] 时，打印元素
            } else {
                StdOut.print(a[i] + " ");
                i++;
                j++;
            }
        }
    }

    public static int[] sourceArr(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = StdRandom.uniform(0, 100);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        int[] a = sourceArr(20);
        int[] b = sourceArr(20);
        System.out.println("两个数组的元素：");
        for (int i : a
        ) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : b
        ) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("公共元素：");
        printCommon(a, b);
        System.out.println();
        printCommon2(a, b);
    }
}
