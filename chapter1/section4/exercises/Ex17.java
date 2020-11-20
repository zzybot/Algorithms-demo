package chapter1.section4.exercises;
/**
 * double 数组中找到绝对值之差最大的两个数，要求时间复杂度 O(N)
 * 思路：只能遍历一次，不能排序
 * 两次循环，一次找到最大的数，一次找到最小的数
 * */
public class Ex17 {
    public static double[] getAbsMax(double[] a){
        double[] result = new double[2];

        double min = a[0];
        //O(N)
        for (int i = 1; i < a.length; i++) {
            if (a[i] < min){
                min = a[i];
                result[0] = a[i];
            }
        }

        //O(N)
        double max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max){
                max = a[i];
                result[1] = a[i];
            }
        }

        return result;
    }
}
