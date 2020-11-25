package chapter2.section1.exercise;

import chapter2.tools.Utils;

/**
 * 出列排序
 * 条件：只能看最上面的两张牌，可以交换最上面的两张牌，或是将最上面的牌放到最下面
 * @author ZZY
 */
public class Ex14 {
    /**
     * 其实就是选择排序，设牌存于长度为 n 的数组 a 中
     * 设 k = n-1
     * 从[0,k]中找出最小的放在数组末尾，然后 k--，
     * 再从[0,k]中找出最小的放在数组末尾，k--，
     * 直到 k == 0；
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        int k = n - 1;
        while (k >= 0) {
            //循环一次，a[0]，为所有元素的最小值
            for (int i = 0; i < k; i++) {
                if (Utils.less(a[0], a[1])) {
                    Utils.exch(a, 0, 1);
                }
                //移动数组元素
                Comparable temp = a[0];
                System.arraycopy(a, 1, a, 0, k);
                a[k] = temp;
            }
            //最后，把 a[0] 移动到数组末尾。
            Comparable temp = a[0];
            System.arraycopy(a, 1, a, 0, n - 1);
            a[n - 1] = temp;
            k--;
        }
    }
}
