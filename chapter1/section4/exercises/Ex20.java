package chapter1.section4.exercises;

import java.util.HashSet;

/**
 * 双调查找：
 * 要求：最坏情况下比较次数为 ~3lgN
 * 思路：先找出最大值索引，再分别查找递增区间和递减区间，三次二分查找
 */
public class Ex20 {

    /**
     * 找到双调数组的最大元素的索引
     */
    public static int searchMax(int[] a) {
        int first = 0;
        int last = a.length - 1;
        while (first <= last) {
            int mid = first + (last - first) / 2;
            if (a[mid] < a[mid + 1]) {
                first = mid + 1;
            } else if (a[mid] < a[mid - 1]) {
                last = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     */
    private static int binarySearch(int[] a, int key, int first, int last) {
        while (first <= last) {
            int mid = first + (last - first) / 2;
            if (key < a[mid]) {
                last = mid - 1;
            } else if (key > a[mid]) {
                first = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 双调查找
     */
    public static int bitonicSearch(int[] a, int key) {

        //最大元素索引
        int splitIndex = searchMax(a);
        //数组中位索引
        double midIndex = a.length / 2.0;

        //先找范围较小的部分，如果找到会减少搜索时间
        if (splitIndex < midIndex) {
            int index = binarySearch(a, key, 0, splitIndex);
            if (index > 0) {
                return index;
            }
            //如果小范围没找到，再去大范围里找
            return binarySearch(a, key, splitIndex + 1, a.length - 1);
        } else {
            int index = binarySearch(a, key, splitIndex + 1, a.length - 1);
            if (index > 0) {
                return index;
            }

            return binarySearch(a, key, 0, splitIndex);
        }
    }

    //如何生成随机双调数组？生成随机数作为数组长度，生成随机数填充数组，Arrays.sort 排序，生成随机数作为索引
    //以该索引为首，数组末尾为尾，向中间遍历，交换首尾元素位置

}
