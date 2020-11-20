package chapter1.section4.exercises;


import binary.BinarySearch;

import java.util.Arrays;

/**
 * 4-sum，为4-sum实现一个算法
 */
public class Ex14 {

    /**
     * O(N^3 * logN)，优化了暴力解法的最后一个循环
     * */
    public int count(int[] a) {

        //保护原数组顺序
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }

        //归并排序 O(N*logN)
        Arrays.sort(b);

        //三层循环 + 二分
        int n = b.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (BinarySearch.rank(-b[i] - b[j] - b[k], b) > k){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
