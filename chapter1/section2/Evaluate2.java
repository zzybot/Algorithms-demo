package chapter1.section2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

/**
 * 双栈算术表达式求值算法 if else 版
 * @author ZZY
 */
public class Evaluate2 {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();

            if ("(".equals(str)) {
            } else if ("+".equals(str)) {
                ops.push(str);
            } else if ("-".equals(str)) {
                ops.push(str);
            } else if ("*".equals(str)) {
                ops.push(str);
            } else if ("/".equals(str)) {
                ops.push(str);
            } else if ("sqrt".equals(str)) {
                ops.push(str);
            } else if (")".equals(str)) {
                String op = ops.pop();
                Double temp = vals.pop();

                if ("+".equals(op)) {
                    temp += vals.pop();
                } else if ("-".equals(op)) {
                    temp -= vals.pop();
                } else if ("*".equals(op)) {
                    temp *= vals.pop();
                } else if ("/".equals(op)) {
                    temp /= vals.pop();
                } else if ("sqrt".equals(op)) {
                    temp = Math.sqrt(temp);
                }

                vals.push(temp);
            } else {
                vals.push(Double.parseDouble(str));
            }
        }
        StdOut.println(vals.pop());
    }
}
