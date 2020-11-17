package chapter1.section3;

import java.util.Scanner;

/**
 * 计算后序表达式
 *
 * @author ZZY
 */
public class Ex11 {
    public static int evaluatePostfix(String input) {
        String[] inputValues = input.split("\\s");
        ListStack<Integer> stack = new ListStack<>();
        for (String str : inputValues
        ) {
            if ("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str)){
                int value2 = stack.pop();
                int value1 = stack.pop();
                int result;
                if ("+".equals(str)){
                    result = value1 + value2;
                }else if ("-".equals(str)){
                    result = value1 - value2;
                }else if ("*".equals(str)){
                    result = value1 * value2;
                }else {
                    result = value1 / value2;
                }
                stack.push(result);
            }else {
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(evaluatePostfix(input));
    }
}
