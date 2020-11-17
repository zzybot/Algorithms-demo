package chapter1;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * 删除白名单中重复的数字
 * 实现思路是找出不重复的元素放入 Arraylist，再将 Arraylist 中的元素复制到新数组中
 *
 * */
public class Ex28 {
    public static void main(String[] args) {
        int[] whitelist = {1, 2, 6, 6, 7, 5, 9, 8, 3, 3, 3, 5, 4,};
        Arrays.sort(whitelist);
        ArrayList<Integer> list = new ArrayList<>();

        // 遍历白名单数组，相邻的两个数不相等即为不重复，注意索引范围是[0,n-1]，最后一个元素不检查
        for (int i = 0; i < whitelist.length - 1; i++) {
            if (whitelist[i] != whitelist[i + 1]){
                list.add(whitelist[i]);
            }
        }
        //添加最后一个元素进 list，
        list.add(whitelist[whitelist.length -1]);

        whitelist = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            whitelist[i] = list.get(i);
            System.out.print(whitelist[i] + "");
        }
    }
}
