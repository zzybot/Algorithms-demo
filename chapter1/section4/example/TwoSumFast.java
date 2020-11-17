package chapter1.section4.example;

import binary.BinarySearch;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * 利用归并排序和二分查找实现更快的 TwoSum，前提是数组有序且数组元素均不相同
 *
 * 二分查找失败时返回 -1，成功时返回元素索引
 * 如果返回值大于 i，那么计数器 +1
 * 如果返回值小于 i，那么计数器不变（此时，该整数对已经被计数过了）
 *
 * 二分查找的时间复杂度是O(logN)，归并的时间复杂度是O(N*logN)，单层循环的时间复杂度是O(N)
 * 二分与循环的组合时间复杂度是O(N*logN)，该算法的时间复杂度是 2*N*logN->O(N*logN)
 *
 * 归并操作，也叫归并算法，指的是将两个顺序序列合并成一个顺序序列的方法。
 * 如　设有数列{6，202，100，301，38，8，1}
 * 初始状态：6,202,100,301,38,8,1
 * 第一次归并后：{6,202},{100,301},{8,38},{1}，比较次数：3；
 * 第二次归并后：{6,100,202,301}，{1,8,38}，比较次数：4；
 * 第三次归并后：{1,6,8,38,100,202,301},比较次数：4；
 * 总的比较次数为：3+4+4=11；
 * 逆序数为14；
 */
public class TwoSumFast {
    public static int count(int[] a) {
        //归并排序：N*logN
        Arrays.sort(a);
        int count = 0;
        //循环：N
        for (int i = 0; i < a.length; i++) {
            //N*logN
            if (BinarySearch.rank(-a[i], a) > i) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        System.out.println(count(a));
    }
}
