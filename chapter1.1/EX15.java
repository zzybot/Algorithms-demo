package chapter1;

/**
 * 返回一个长度为 M 的数组 b
 * 数组 b 的第 i 个值为 i 在数组 a 中出现的次数
 * 当 0 <= a[i] < M 时，数组 b 的元素之和等于 a.length
 */
public class EX15 {
    /**
     * 开始只想到这么写
     * */
    public static int[] histogram(int[] a, int M) {
        int[] b = new int[M];
        for (int i = 0; i < b.length; i++) {
            int count = 0;
            for (int k : a) {
                if (i == k) {
                    count++;
                }
            }
            b[i] = count;
        }
        return b;

    }

    public static int[] histogramBetter(int[] a, int M) {
        //此时，数组 result 中每个元素都是 0
        int[] result = new int[M];
        for (int i = 0; i < a.length; i++) {
            if (0 <= a[i] && a[i] < M) {
                //数组 b 的第a[i]个元素每出现一次该元素的值就加一，循环一次 result 中就有一个元素的值加一，循环结束，result 元素之和
                //就等于 a.length
                result[a[i]]++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 2, 3, 1, 7, 5, 3, 2, 2, 2};
        int[] result = histogram(a, 8);
        for (int j : result) {
            System.out.printf("%3d", j);
        }
        System.out.println("");
        result = histogramBetter(a, 8);
        for (int i : result
        ) {
            System.out.printf("%3d", i);
        }
    }
}
