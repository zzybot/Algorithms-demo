package chapter1.section4.exercises;

/**
 * 矩阵局部最小值：
 * 思路：直接从中间元素开始，判断该元素是不是局部最小，如果是就返回，如果不是就找周围四个元素中最小的元素作为起点进行下一次比较
 *
 * 无法确定能找到结果，时间复杂度一般情况下不会超过 O(N)，但当数据规模大时不确定
 *
 * 改进：从某一行或某一列的最小值开始搜索，在矩阵边缘时不停止搜索，而是继续寻找更小的元素，元素会越来越小，总能搜索到局部最小值
 */
public class Ex19 {
    public static void localMinOfMartix(int[][] a) {

        if (a == null){
            throw new RuntimeException("matrix cannot be null!");
        }

        if (a.length != a[0].length){
            throw new RuntimeException("matrix must be NxN!");
        }

        //行数
        int i = a.length / 2;
        //列数
        int j = i;

        //计数器，保证在有限次数内未找到的情况下停止循环
        int count = 0;

        //找到局部最小值钱持续循环
        while (true) {
            if (count > 2 * a.length){
                System.out.println("can't find the element!");
                return;
            }
            int tempi = i;
            int tempj = j;
            int min = a[i][j];
            if (i + 1 != a.length - 1 && a[i + 1][j] < min) {
                min = a[i + 1][j];
                tempi = i + 1;
                tempj = j;
            }
            if (j + 1 != a.length - 1 && a[i][j + 1] < min) {
                min = a[i][j + 1];
                tempi = i;
                tempj = j + 1;
            }
            if (i - 1 != 0 && a[i - 1][j] < min) {
                min = a[i - 1][j];
                tempi = i - 1;
                tempj = j;
            }
            if (j -1 != 0 && a[i][j - 1] < min) {
                min = a[i][j - 1];
                tempi = i;
                tempj = j - 1;
            }
            if (min == a[i][j]) {
                System.out.println("局部最小值：" + i + " " + j);
                return;
                //如果a[i][j] 不是局部最小，比较中最小值的索引赋值给 i 和 j
            } else {
                i = tempi;
                j = tempj;
            }

            count++;
        }
    }
}
