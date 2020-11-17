package chapter1.section3;

import java.util.Scanner;
/**
 * 中序表达式转后序表达式
 * */
public class Ex10 {
    public static String InfixToPostfix(String input) {
        String[] inputValues = input.split("\\s");
        ListStack<String> operands = new ListStack<>();
        ListStack<String> operators = new ListStack<>();

        for (String value : inputValues
        ) {
            if ("(".equals(value)){
                //do nothing
            }else if ("+".equals(value) || "-".equals(value) || "*".equals(value) || "/".equals(value)){
                operators.push(value);
                //遇到“)”就弹出两个操作数和一个运算符，调整它们的顺序并转换成字符串放回操作数栈
            }else if (")".equals(value)){
                String value2 = operands.pop();
                String value1 = operands.pop();
                String ops = operators.pop();
                String subExpression = value1 + " " + value2 + " " + ops;
                operands.push(subExpression);
            }else {
                operands.push(value);
            }
        }
        return operands.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入字符串：");
        String input = scanner.nextLine();
        System.out.println(InfixToPostfix(input));
    }
}
