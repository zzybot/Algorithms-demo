package chapter2.section1.example;

public class Test {
    /**
     * 运行时间与输入无关，移动数据最少
     * 索引左侧是最终有序的，索引 i 移动到数组末端时完成排序，索引 j 不会访问左侧元素
     */
    public void selection(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            //(N-1)+(N-2)+(N-3)+...+1 = ~N^2/2，一定会执行 ~N^2/2 次比较
            for (int j = i + 1; j < a.length; j++) {
                if (Utils.less(a[i], a[min])) {
                    min = i;
                }
            }
            //一定会执行 N 次交换，但不一定会交换位置，比如 min == i；
            Utils.exch(a, i, min);
        }
    }

    /**
     * 运行时间与输入相关，平均移动数据次数为 N^2/4，平均比较次数 N^2/4
     * 索引 i 左侧是有序的（新元素会在左侧找到合适的位置插入），索引 i 移动到数组末端时完成排序，索引 j 不会访问右侧元素
     */
    public void insertion(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            //1+2+3+4+...+(N-1) = N*(N-1)/2 = ~N^2/2，最好情况下执行 N-1 次
            for (int j = i; j > 0 && Utils.less(a[j], a[j - 1]); j--) {
                //最坏情况下执行 N^2/2 次，最好情况下执行 0 次
                Utils.exch(a, j, j - 1);
            }
        }
    }
}
