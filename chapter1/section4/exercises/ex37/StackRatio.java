package chapter1.section4.exercises.ex37;

import chapter1.section2.FixedCapacityStack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 测试用例，测试需要自动装箱拆箱的栈和不需要自动装箱拆箱的栈，
 * 在执行多次 push、pop 操作时的性能差距。
 *
 * @author ZZY
 * */
public class StackRatio {
    public static double timeTrial_int(int n){
        FixedCapacityStackOfInts stackOfInts = new FixedCapacityStackOfInts(n);
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < n; i++) {
            stackOfInts.push(i);
        }
        while (!stackOfInts.isEmpty()){
            stackOfInts.pop();
        }
        return timer.elapsedTime();
    }

    public static double timerTiral_Generic(int n){
        FixedCapacityStack<Integer> stack = new FixedCapacityStack<>(n);
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty()){
            stack.pop();
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        StdOut.printf("基本类型操作耗时 : %f\n",
                timeTrial_int(10000000));
        StdOut.printf("自动装箱拆箱操作耗时 : %f\n",
                timerTiral_Generic(10000000));
    }
}
