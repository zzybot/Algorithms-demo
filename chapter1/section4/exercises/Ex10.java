package chapter1.section4.exercises;

import java.util.Arrays;

public class Ex10 {

    /**
     * 暴力解，但最坏的情况下该算法会执行 N/2 次，也就是O(N)
     */
    public static int rank(int key, int[] a) {
        int first = 0;
        int last = a.length - 1;

        while (first <= last) {

            int mid = first + (last - first) / 2;

            if (key < a[mid]) {
                last = mid - 1;
            } else if (key > a[mid]) {
                first = mid + 1;
            } else {
                while (key == a[mid] && mid >= 0) {
                    mid--;
                }
                return mid + 1;
            }
        }
        return -1;
    }

    /**
     * if( key <= a[mid])，最后返回 last + 1；
     * <p>
     * 当数组中有 key 存在时，mid 命中 key，继续向左查找
     * 只会有两种情况：
     * 1.a[mid] 命中 key，此时 mid 是第二小的索引，有 a[last] == key，循环结束时， last + 1 为最小索引
     * 2.a[mid] 命中 key，此时 mid 是最小的索引，则循环结束，last + 1 为最小索引。
     * <p>
     * 循环总会得到 first == last == mid 的情况，此时，若 a[mid] == key，则 last = mid - 1，循环结束后，a[last + 1] == key
     * 若 a[mid] < key，此时 a[last + 1] == key，则 first = mid + 1，随后循环结束
     */
    public static int rank2(int key, int[] a) {
        int first = 0;
        int last = a.length - 1;
        while (first <= last) {
            int mid = first + (last - first) / 2;

            if (key <= a[mid]) {
                last = mid - 1;
            } else if (key > a[mid]) {
                first = mid + 1;
            }
        }
        return a[last + 1] == key ? last + 1 : -1;
    }

    /**
     * 更简单直观的解法
     */
    public static int rank3(int key, int[] a) {
        int first = 0;
        int last = a.length - 1;
        while (first <= last) {
            int mid = first + (last - first) / 2;

            if (key > a[mid]) {
                first = mid + 1;
            } else if (key < a[mid] || mid > 0 && a[mid - 1] == key) {
                last = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = Ex08.sourceArr(10);
        Arrays.sort(a);
        int index = rank(6, a);
        System.out.println(index);
    }
}
