package chapter1.section4.exercises;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * 仅用加减实现的二分查找，
 * 条件：数组升序，数组元素不重复，只能使用加法和减法以及常数的额外内存
 * 时间复杂度O(logN)
 * <p>
 * 斐波那契数列：
 * 斐波那契数列（Fibonacci sequence），又称黄金分割数列、
 * 因数学家莱昂纳多·斐波那契（Leonardoda Fibonacci）以兔子繁殖为例子而引入，故又称为“兔子数列”，
 * 指的是这样一个数列：0、1、1、2、3、5、8、13、21、34、……
 * 在数学上，斐波那契数列以如下被以递推的方法定义：F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
 * <p>
 * lgN logN lnN：
 * 算法中log级别的时间复杂度都是由于使用了分治思想,这个底数直接由分治的复杂度决定。
 * 如果采用二分法,那么就会以2为底数,三分法就会以3为底数
 * <p>
 * 思路：就是用斐波那契数列作为每次调整查找范围的索引，例如下一次查找时 mid == Fk-2
 */
public class Ex22 {
    static class FibonacciSearch {
        /*
         * 判断 int 加法操作是否溢出
         */
        public static boolean isOverflow(int x, int y) {
            long r = x + y;
            return ((x ^ r) & (y ^ r)) < 0;
            /*long r = x + y;
            if (x > 0 && y > 0 && x > Integer.MAX_VALUE - y){
                return false;
            }
            if (x < 0 && y < 0 && x < Integer.MIN_VALUE - y){
                return false;
            }
            return true;*/
        }

        /*
         * 计算斐波纳切数列第 k 项
         */
        public static int fibo(int k) {
            int prev = 1, next = 1, kk = k;
            while (--k > 0) {
                if (isOverflow(next, prev)) {
                    StdOut.printf("k = %d next = %d prev = %d 开始溢出\n",kk, next, prev);
                }
                next = next + prev;
                prev = next - prev;
            }
            return next;
            /*
            斐波那契递归实现：用在循环中巨巨巨慢

            if (k == 1) {
                return 0;
            }
            if (k == 2) {
                return 1;
            }
            return fibo(k - 1) + fibo(k - 2);*/
        }

        /*
         * 首次加载该 Class 文件时就把斐波纳切前 47 项全部准备好
         */
        public static final int[] fibo;

        static {
            /*
             * 斐波纳切第 46 项 int 溢出
             *        第 93 项 long 溢出
             */
            fibo = new int[46];
            for (int i = 0; i < fibo.length; i++) {
                fibo[i] = fibo(i);
            }
        }

        /*
         * 在 lo 和 hi 间寻找斐波纳切分割点
         */
        public static int splitBetween(int first,int last) {
            /*
            int fIndex = 0;
            while (fibo[fIndex] <= (last - first)) {
                fIndex++;
            }
            return fibo[--fIndex] + first - 1;
            */
            //根据书上的提示为以下代码
            int fIndex = 0;
            //F(k)
            while (fibo[fIndex] + first <= last) {
                fIndex++;
            }
            //F(k-2)
            return fibo[fIndex - 3] + first;
        }

        /*
         * 斐波纳切查找
         */
        public static int rank(int key, int[] arr) {
            int first = 0, last = arr.length - 1;
            while (first < last) {
                int split = splitBetween(first, last);
                if (arr[split] > key) {
                    last = split - 1;
                } else if (arr[split] < key) {
                    first = split + 1;
                } else {
                    return split;
                }
            }
            return -1;
        }
    }

    /*
     * 产生已排序随机数组
     */
    public static int[] sourceArr(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = StdRandom.uniform(0, 100);
        }
        Arrays.sort(arr);
        return arr;
    }

    /*
     * 打印数组
     */
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if ((i + 1) % 10 == 0) {
                StdOut.printf("%2d\n", arr[i]);
            } else {
                StdOut.printf("%2d ", arr[i]);
            }
        }
        StdOut.println();
    }
    public static void main(String[] args) {

        int[] arr = sourceArr(20);
        printArray(arr);
        StdOut.println("斐波纳切查找结果 : " + FibonacciSearch.rank(4, arr));
    }
}
