package chapter1.section3;

import edu.princeton.cs.algs4.StdIn;
/**
 * @author ZZY
 * 打印队列中倒数第 k 个字符串
 * */
public class Ex15 {
    public static void main(String[] args) {
        ListQueue<String> queue = new ListQueue<>();
        while (!StdIn.isEmpty()) {
            queue.enqueue(StdIn.readString());
        }
        print(queue, 3);
    }

    public static void print(ListQueue<String> queue, int k) {
        String result = null;
        for (int i = 0; i < queue.size() - k + 1; i++) {
            result = queue.dequeue();
        }
        System.out.println(result);
    }
}
