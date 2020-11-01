package chapter1;

import binary.BinarySearch;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;


public class Ex39 {
    public static void main(String[] args) {
        //命令行参数不能为空
        if (args.length < 1) {
            System.out.println("error!");
            return;
        }

        int T = Integer.parseInt(args[0]);
        //num[0]保存 N 为10^3时的实验结果，num[1]保存 N 为10^4 的实验结果。。。
        int[] num = new int[4];
        //每个 N 值实验 T 次
        for (int i = 0; i < T; i++) {
            //设置 N 为100，N^3 即为 N*10
            int N = 100;
            //循环四次，每次设置 N 的值
            for (int j = 0; j < 4; j++) {
                N *= 10;
                //两个等长数组
                int[] a = new int[N];
                int[] b = new int[N];
                //为数组填充随机数
                for (int k = 0; k < N; k++) {
                    a[k] = StdRandom.uniform(100000, 1000000);
                    b[k] = StdRandom.uniform(100000, 1000000);
                }
                Arrays.sort(a);
                //调用二分查找，遍历数组b，数组b的值为key，查找数组a，找到即 num[j]++,num[j] 对应一种 N 值
                for (int k = 0; k < N; k++) {
                    if (BinarySearch.rank(b[k], a) != -1) {
                        num[j]++;
                    }
                }
            }
        }
        System.out.println("   N\t Average");
        int N = 100;
        for (int i = 0; i < 4; i++) {
            N *= 10;
            //求每个 N 实验结果的平均值
            System.out.printf("%7d %9.2f\n", N, (double) num[i] / T);
        }
    }
}
