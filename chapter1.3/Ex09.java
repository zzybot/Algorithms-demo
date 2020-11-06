package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;


/**
 * @author ZZY
 * chapter1.3 第九题答案
 */
public class Ex09 {
    /**
     * 该方法创建两个栈，一个保存操作数，另一个保存运算符。
     * 可以补全缺少左括号的算术表达式，利用了双栈算术表达式求值的思路
     * 输入：1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
     * 输出：( ( ( 1 + 2 ) * ( 3 - 4 ) * ( 5 - 6) ) )
     */
    public static String getInfixExpression(String input) {
        //保存操作数
        ListStack<String> operands = new ListStack<>();
        //保存运算符
        ListStack<String> operators = new ListStack<>();
        String[] inputValues = input.split("\\s");
        for (String value : inputValues
        ) {
            if ("(".equals(value)) {
                //do nothing
            } else if ("+".equals(value) || "-".equals(value) || "*".equals(value) || "/".equals(value)) {
                operators.push(value);
            } else if (")".equals(value)) {
                String value2 = operands.pop();
                String value1 = operands.pop();
                String ops = operators.pop();
                String subExpression = "(" + value1 + ops + value2 + ")";
                operands.push(subExpression);
            } else {
                operands.push(value);
            }
        }

        return operands.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入字符串：");
        String input = scanner.nextLine();
        StdOut.println(getInfixExpression(input));
    }
}
