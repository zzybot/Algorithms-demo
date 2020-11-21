package chapter1.section4.exercises;

/**
 * 扔鸡蛋：
 * 目标：确定 F 的值
 * 思路：二分查找
 */
public class Ex24 {
    //true 表示鸡蛋碎了，false 表示没碎
    public static int throwEggsLgN(boolean[] a) {
        //碎鸡蛋数
        int brokenEggs = 0;
        //扔鸡蛋次数
        int throwEggs = 0;
        //楼层高度 N
        int n = a.length;
        //如果顶层没有碎，就不用找了
        if (!a[n - 1]) {
            return -1;
        }

        int first = 0;
        int last = n - 1;
        //~lgN
        while (first <= last) {
            throwEggs++;
            int mid = first + (last - first) / 2;
            //如果 mid - 1 命中，之后每次扔鸡蛋都不会碎，直到 first == last == mid
            if (a[mid] && a[mid - 1]) {
                last = mid - 1;
                brokenEggs += 2;
                //mid + 1 命中，之后每次扔鸡蛋都会碎
            } else if (!a[mid]) {
                first = mid + 1;
                //此时，a[mid] 碎，但 a[mid - 1]不碎
            } else {
                brokenEggs++;
                return mid;
            }
        }
        return -1;
    }

    /**
     * 减小扔鸡蛋的成本至 ~2lgF
     * <p>
     * 从一层开始，循环乘 2 ，目的是找到一个较小的范围，F 在这个范围里。
     * 再以该范围为二分查找的起始范围进行二分查找
     */
    public static int throwEggs2lgF(boolean[] a) {
        //k 为楼层
        int k = 1;
        //楼层总数
        int n = a.length;
        //扔鸡蛋次数与碎鸡蛋个数
        int throwEggs = 0;
        int brokenEggs = 0;
        //顶层没碎就不用找了
        if (!a[n - 1]) {
            return -1;
        }
        //试探离 F 最近的楼层
        if (k < n && !a[k]) {
            k *= 2;
            throwEggs++;
        }
        //如果 k < n，那鸡蛋肯定是碎了
        if (k < n) {
            brokenEggs++;
        }
        //根据 k 设置二分查找范围
        int first = k / 2;
        //k > n 时，我们不知道在 k/2 到 n-1 的范围内鸡蛋会不会碎
        int last = k < n ? k : n - 1;
        while (first <= last) {
            int mid = first + (last - first) / 2;
            if (a[mid] && a[mid - 1]) {
                last = mid - 1;
                brokenEggs += 2;
            } else if (!a[mid]) {
                first = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
