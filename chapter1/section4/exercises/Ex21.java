package chapter1.section4.exercises;

import java.util.Arrays;

/**
 * 无重复值的二分查找
 * 思路：注意要求运行时间为 ~lgR，其中 R 为为参数数组中不同整数的数量，
 * 也就是说，重复的数不应该被重复查找，对比普通二分查找的最坏情况，如果 key 是最左或最右，
 * 每次调整范围是 mid - 1，或 mid + 1。
 * 如果 mid 处元素向左或向右均是相等元素，那么我们应该直接跳过这些元素
 * <p>
 * 最坏情况下，所有元素都不重复，那么时间复杂度为 O(lgR)
 */
public class Ex21 {
    private final int[] a;

    public Ex21(int[] keys) {
        a = new int[keys.length];
        System.arraycopy(keys, 0, a, 0, keys.length);
        Arrays.sort(a);
    }

    private int rank(int key) {
        int first = 0;
        int last = a.length - 1;

        while (first <= last) {

            int mid = first + (last - first) / 2;

            if (key < a[mid]) {
                /*
                  if (a[mid - 1] == a[mid]) {
                                      int temp = a[mid];
                                      while (temp == a[mid]) {
                                          mid--;
                                      }
                                      last = mid;
                                  } else {
                                      last = mid - 1;
                                  }
                  这样写多余了
                  */
                //跳过所有与 a[mid] 相同的元素
                int temp = a[mid];
                while (temp == a[mid]) {
                    mid--;
                }
                last = mid;
            } else if (key > a[mid]) {
                //跳过所有与 a[mid] 相同的元素
                int temp = a[mid];
                while (temp == a[mid]) {
                    mid++;
                }
                first = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public boolean contains(int key) {
        return rank(key) > 0;
    }
}
