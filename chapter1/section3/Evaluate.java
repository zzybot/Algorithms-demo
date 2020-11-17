package chapter2.example;

import edu.princeton.cs.algs4.StdIn;

import java.util.Stack;

/**
 * 双栈算术表达式求值算法
 */
public class Evaluate {
    public static void main(String[] args) {
        //运算符栈
        Stack<String> ops = new Stack<>();
        //操作数栈
        Stack<Double> vals = new Stack<>();

        /**
         * 依次读取表达式字符串，忽左括号'('，读到操作数就压入操作数栈，读到运算符就压入运算符栈
         * 读到')'就弹出一个运算符和两个操作数，计算结果再将结果压入栈
         * */
        while (!StdIn.isEmpty()) {
            //表达式字符串
            String str1 = StdIn.readString();

            switch (str1) {
                //忽略左括号
                case "(":
                    break;
                //运算符入栈
                case "+":
                case "-":
                case "*":
                case "/":
                    ops.push(str1);
                    break;
                //读到')'就弹出一个运算符和两个操作数，计算结果再将结果压入操作数栈
                case ")": {
                    //弹出最新压入栈的操作数
                    double temp = vals.pop();
                    //弹出最新压入栈的运算符
                    String str2 = ops.pop();
                    switch (str2) {
                        case "+":
                            temp += vals.pop();
                            break;
                        case "-":
                            temp -= vals.pop();
                            break;
                        case "*":
                            temp *= vals.pop();
                            break;
                        case "/":
                            temp /= vals.pop();
                            break;
                        default:
                    }
                    vals.push(temp);
                }
                break;
                default:
                    //非运算符和括号，转换为 Double 类型直接压入操作数栈
                    vals.push(Double.parseDouble(str1));
                    break;
            }
        }

        double d = vals.pop();
        System.out.println(d);
    }
}
