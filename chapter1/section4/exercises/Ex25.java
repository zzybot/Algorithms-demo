package chapter1.section4.exercises;

/**
 * 扔两个鸡蛋：
 * 条件：只有两个鸡蛋（不能使用二分法，使用循环），楼层 F 及之上扔鸡蛋会碎，总楼层数为 N,最坏情况下执行 2根号N 次
 * <p>
 * 思路：从 1 层开始扔鸡蛋，每次递增 sqrt(N) 倍，直到鸡蛋破碎，此时楼层为 k
 * 再从 k / sqrt(N) 处开始扔鸡蛋，每次楼层加一，直到鸡蛋碎，此时楼层 F 一定在上一次没碎的楼层和当前鸡蛋碎了的楼层之间
 * <p>
 * 最坏情况，上届直接命中楼层 F，此时程序执行 2根号N 次
 */
public class Ex25 {
    public static int throwTwoEggs(boolean[] buiding) {
        int n = buiding.length;
        int k = 1;
        int threshold = (int) Math.sqrt(n);
        int throwEggs = 0;
        int brokenEggs = 0;

        //从一层开始扔，以 sqrt(N) 递增
        while (k < n && !buiding[k]) {
            k *= threshold;
            throwEggs++;
        }
        //如果 k<n，那么鸡蛋一定碎了
        if (k < n) {
            brokenEggs++;
        }
        //从小范围再开始扔鸡蛋
        int first = (k / threshold);
        while (!buiding[first]) {
            throwEggs++;
            first++;
        }
        brokenEggs++;
        return first;
    }
}
