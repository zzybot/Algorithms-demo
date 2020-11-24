package chapter2.section1.example;

/**
 * 算法2.2：插入排序
 * <p>
 * 最坏情况下：~N^2/2 次比较，~N^2/2 次交换
 * 最好情况下：N-1 次比较，0 次交换
 * 平均：~N^2/4 次比较，~N^2/4 次交换
 *
 * @author ZZY
 */
public class Insertion {
    /**
     * 插入排序：保证索引左边的元素始终有序
     */
    public static void sort(Comparable[] a) {
        // N-1
        for (int i = 1; i < a.length; i++) {
            //1+2+3+4+...+(N-1) = (N-1)/2 * N = N*(N-1)/2 = ~N^2
            for (int j = i; j > 0 && Utils.less(a[j], a[j - 1]); j--) {
                Utils.exch(a, j, j - 1);
            }
        }
    }
}
