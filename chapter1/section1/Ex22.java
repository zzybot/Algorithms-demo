package chapter1;
/**
 * 递归实现二分查找
 * */
public class Ex22 {
    public static int rank(int key, int[] a, int first, int last) {
        if (first > last) {
            return -1;
        }
        int mid = first + (last - first) / 2;

        if (key < a[mid]) {
            return rank(key, a, first, mid - 1);
        } else if (key > a[mid]) {
            return rank(key, a, mid + 1, last);
        }else {
            return mid;
        }
    }
}
