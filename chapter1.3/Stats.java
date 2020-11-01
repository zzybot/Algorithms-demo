package chapter2.example;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stats {
    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<>();
        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }

        int N = numbers.size();

        double sum = 0.0;
        for (double d : numbers
        ) {
            sum += d;
        }

        //Bag 中元素平均值
        double mean = sum / N;

        sum = 0.0;
        for (double d : numbers
        ) {
            sum += (d - mean) * (d - mean);
        }
        //样本标准差，每个值与平均值的差的平方之和除以 N-1
        double std = Math.sqrt(sum / (N - 1));

        StdOut.printf("Mean: %.2f\n",mean);
        StdOut.printf("Std dev: %.2f\n",std);
    }
}
