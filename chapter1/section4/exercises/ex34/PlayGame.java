package chapter1.section4.exercises.ex34;

import edu.princeton.cs.algs4.StdOut;

/**
 * 编写一个方法，让该方法去猜数字
 *
 * 我们每次多需要猜一次下界和上界，才能得知目标离上界近还是离下界近父
 */
public class PlayGame {
    public static void playGame(GuessGame game) {
        game.play();

        int first = 0;
        int last = game.maximum;
        int guessTimes = 0;

        GuessGame.Result result;

        while (first <= last) {
            //猜下界
            result = game.guess(first);
            guessTimes++;
            if (result == GuessGame.Result.CORRECT) {
                break;
            }

            //猜上界
            result = game.guess(last);
            guessTimes++;
            if (result == GuessGame.Result.CORRECT) {
                break;
            }

            //开始二分
            int mid = (first + last) / 2;
            switch (result) {
                case HOT:
                    first = mid;
                    break;
                case COLD:
                    last = mid;
                    break;
                default:
                    break;
            }
        }
        StdOut.printf("一共猜了 %d 次\n", guessTimes);
    }

    public static void main(String[] args) {
        GuessGame game = new GuessGame(5);
        playGame(game);
    }
}
