package chapter1;

/**
 * 推算该递归方法打印的结果：33,50
 * 什么是递归？
 *
 * 递：
 * f(6)
 * => 6 * f(5)
 * => 6 * (5 * f(4))
 * => 6 * (5 * (4 * f(3)))
 * => 6 * (5 * (4 * (3 * f(2))))
 * => 6 * (5 * (4 * (3 * (2 * f(1)))))归：
 * => 6 * (5 * (4 * (3 * (2 * 1))))
 * => 6 * (5 * (4 * (3 * 2)))
 * => 6 * (5 * (4 * 6))
 * => 6 * (5 * 24)
 * => 6 * 120
 * => 720
 */
public class EX18 {
    public static int mystery(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return mystery(a + a, b / 2);
        }
        return mystery(a + a, b / 2) + a;
    }

    public static void main(String[] args) {
        int a = 3;
        int b = 11;
        System.out.println(mystery(a, b));
        int c = 2;
        int d = 25;
        System.out.println(mystery(c, d));
        System.out.println(mystery(a, b) == a * b);
        System.out.println(mystery2(a,b));
        System.out.println(mystery2(a,b) == Math.pow(a,b));
    }

    /**
     * 如果将‘+’变为‘*’呢？
     */
    public static int mystery2(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b % 2 == 0) {
            return mystery2(a * a, b / 2);
        }
        return mystery2(a * a, b / 2) * a;
    }
}
