package chapter1;

import edu.princeton.cs.algs4.StdIn;
/**
 * 添加一个参数，该参数可以选择输出在白名单的数字和输出不在白名单中的数字
 * */
public class Ex23 {
    public static int rank(int key, int[] a) {
        int first = 0;
        int last = a.length - 1;

        while (first <= last) {

            int mid = first + (last - first) / 2;

            if (key < a[mid]) {
                last = mid - 1;
            } else if (key > a[mid]) {
                first = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 6, 7, 8, 9, 12 };
        System.out.printf("+ or - ? ");
        char arg = StdIn.readChar();
        StdIn.readLine();
        System.out.printf("Input keys: ");
        String[] keys = StdIn.readLine().split(" ");

        for (int i = 0; i < keys.length; i++) {
            // != -1，输入的数字在白名单中
            if (rank(Integer.parseInt(keys[i]), a) != -1 && arg == '-') {
                System.out.printf("%s ", keys[i]);
                // == -1,输入的数字不在白名单中
            } else if (rank(Integer.parseInt(keys[i]), a) == -1 && arg == '+') {
                System.out.printf("%s ", keys[i]);
            }
        }

        System.out.println();
    }
}
