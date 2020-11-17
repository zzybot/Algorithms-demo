package 欧几里得算法;

/**
 * 对两个不全为 0 的非负整数不断应用此式：gcd(m,n)=gcd(n,m mod n);直到m mod n为0时。m就是最大公约数
 * <p>
 * 两个非负整数 m，n。若 n 为 0，则两数的最大公约数为m
 * 若 n 不为 0，则递归调用该算法直到被除数为 0
 *
 * @author ZZY
 */
public class Gcd {
    public static void main(String[] args) {
        System.out.println(gcd(0, 3));
    }

    public static int gcd(int p, int q) {
        if (q == 0) {
            return p;
        }

        int r = p % q;
        return gcd(q, r);
    }
}

