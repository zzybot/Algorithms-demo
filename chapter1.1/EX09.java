package chapter1;

/**
 * 手动实现十进制转二进制并将二进制转换为字符串
 */
public class EX09 {
    public static void main(String[] args) {
        System.out.println(transform(9));
    }

    /**
     * 除 p 倒取余
     */
    public static String transform(int n) {
        String s = "";
        for (int i = n; i > 0; i /= 2) {
            s = (i % 2) + s;
        }
        return s;
    }
}
