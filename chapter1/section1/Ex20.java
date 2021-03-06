package chapter1;

/**
 * 实现 ln(n!)
 * <p>
 * 如果 ，即a的x次方等于N（a>0，且a≠1），那么数x叫做以a为底N的对数（logarithm），记作。其中，a叫做对数的底数，N叫做真数，x叫做“以a为底N的对数”。
 * 特别地，我们称以10为底的对数叫做常用对数（common logarithm），并记为lgN。
 * 称以无理数e（e=2.71828…）为底的对数称为自然对数（natural logarithm），并记为lnN。
 * 零没有对数。 [2]
 * 在实数范围内，负数无对数。 [3]  在虚数范围内，负数是有对数的。
 */
public class Ex20 {
    public static double ln(int n) {
        if (n <= 0) {
            return 0;
        }
        //Math.log(); 返回自然对数
        return Math.log(n) + ln(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(ln(10));
    }
}
