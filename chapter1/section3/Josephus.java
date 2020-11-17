package chapter1.section3;

import edu.princeton.cs.algs4.StdIn;

/**
 * Ex37：
 * 思路：编写一个方法，该方法接收 int 参数 N 和  M，
 * 创建长度为 N 的环形队列，设置一个计数器初始值为 1 ，每循环到计数器值为 M 时就把当前 Node 删除，删除后刷新循环次数，直到队列剩最后一个 Node
 * */
public class Josephus {
    public static void main(String[] args) {
        josephus(7,2);
    }

    public static void josephus(int N,int M){
        Ex29<Integer> queue = new Ex29<>();
        //插入元素，从 0 开始
        for (int i = 0; i < N; i++) {
            queue.enqueue(i);
        }

        queue.josephus(M);
    }
}
