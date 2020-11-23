package chapter1.section4.exercises.ex34;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 热还是冷：
 * <p>
 * 思路：
 * 设 索引 first 最小，last 最大
 * 先猜 first，再猜 last，如果返回 “COLD”，说明目标靠近 first，令 last = mid;如果返回 “HOT”，说明目标靠近 last，令 first = mid；
 * 重复上述操作直到猜中（mid == first == last 或 mid == key）
 *
 * @author ZZY
 */
public class GuessGame {
    public enum Result {
        /** 猜中时返回 */
        CORRECT,
        /** 比上次猜测的距离更近 */
        HOT,
        /** 比上次猜测的距离更远 */
        COLD
    }

    /**
     * 随机生成的目标整数
     */
    private int secretNumber;
    /**
     * 上界
     */
    private int prevDistance;
    /** */
    public int maximum;

    public GuessGame(int limit) {
        maximum = limit;
    }

    /** 开始游戏 */
    public void play() {
        prevDistance = Integer.MAX_VALUE;
        secretNumber = StdRandom.uniform(0, maximum + 1);
        StdOut.printf("范围是 %d ~ %d, 要猜测的数字是 %d\n", 0, maximum, secretNumber);
    }

    /**
     * 判断距离
     */
    public Result guess(int guessNumber) {
        int curDistance = Math.abs(guessNumber - secretNumber);
        if (curDistance == 0) {
            return Result.CORRECT;
        }
        Result r = curDistance < prevDistance ? Result.HOT : Result.COLD;
        prevDistance = curDistance;
        return r;
    }
}
