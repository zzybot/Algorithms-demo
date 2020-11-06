package chapter1.section3;

/**
 * 通过栈打印二进制
 * */
public class Ex05 {
    public static void main(String[] args) {
        ListStack<Integer> stack = new ListStack<>();
        int n = 50;
        while (n > 0) {
            stack.push(n % 2);
            n /= 2;
        }

        for (Integer i : stack
        ) {
            System.out.print(i);
        }
    }
}
