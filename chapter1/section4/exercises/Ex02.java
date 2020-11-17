package chapter1.section4.exercises;
/**
 * 修改 ThreeSum，处理两个 int 相加可能溢出的情况
 *
 * 只要将 操作数之一提升为 long 类型即可
 * */
public class Ex02 {
    public static int count(int[] a){
        //统计和为 0 的元组的数量
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    //目的只是让该计算不会溢出
                    if ((long)a[i] + a[j] + a[k] == 0){
                        count++;
                        System.out.println(count);
                    }
                }
            }
        }
        return count;
    }
}
