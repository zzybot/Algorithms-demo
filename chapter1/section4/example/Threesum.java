package chapter1.section4.example;

import edu.princeton.cs.algs4.In;

public class Threesum {

    public static int count(int[] a){
        //统计和为 0 的元组的数量
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0){
                        count++;
                        System.out.println(count);
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        System.out.println(count(a));
    }
}
