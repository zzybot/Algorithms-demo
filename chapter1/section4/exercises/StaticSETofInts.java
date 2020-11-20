package chapter1.section4.exercises;

import java.util.Arrays;

/**
 * Ex11:
 * <p>
 * 思路：两个二分查找，分别找到最小索引和最大索引，返回二者之差 + 1
 */
public class StaticSETofInts {
    private int[] a;

    public StaticSETofInts(int[] keys) {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++) {
            a[i] = keys[i];
        }
        Arrays.sort(a);
    }

    public boolean contains(int key) {
        return rank(key) > 0;
    }

    //普通二分
    private int rank(int key) {
        int first = 0;
        int last = a.length - 1;

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

    public int howMany(int key){
        int miniIndex = miniRank(key);
        int maxIndex = maxRank(key);
        if (miniIndex == -1){
            return 0;
        }
        return maxIndex - miniIndex + 1;
    }

    //二分找最左索引
    private int miniRank(int key) {
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

    //二分找最右索引
    private int maxRank(int key) {
        int first = 0;
        int last = a.length - 1;
        while (first <= last) {

            int mid = first + (last - first) / 2;

            if (key < a[mid]) {
                last = mid - 1;
            } else if (key > a[mid] || mid < a.length - 1 && a[mid + 1] == key) {
                first = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


}
