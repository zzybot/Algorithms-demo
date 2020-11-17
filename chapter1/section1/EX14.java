package chapter1;

/**
 * 2^a <= N，求 a 的最大整数:
 * 如果a的x次方等于N（a>0，且a≠1），那么数x叫做以a为底N的对数（logarithm），记作x=loga N。其中，a叫做对数的底数，N叫做真数。
 * 因为计算机算 n/2 会截断小数，所以 x^a-1 = N /2 ,a -1 就是最大整数。
 */
public class EX14 {
    public static int lg(int n) {
        int shiftRightCount = 0;
        do {
            //右移一位相当于除以二，区别在与，右移的商向负无穷取整，而除二的商向零取整,对于被除数是正整数的情况，二者没有区别，
            //被除数是负整数且是奇数时，二者有区别
            n >>= 1;
            shiftRightCount++;
            //除 2 的次数就等于 a 的值，n 为 1 时，a 为 0，此时 count 就是 a 的最大值
        } while (n != 1);
        return shiftRightCount;
    }

    public static void main(String[] args) {
        int value = 989896765;
        System.out.println(lg(value));
    }
}
