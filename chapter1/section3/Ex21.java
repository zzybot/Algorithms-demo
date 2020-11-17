package chapter1.section3;
/**
 * 编写方法 find，查找链表中是否有对应字符串，有就输出 true，没有就输出 false
 * @author ZZY
 * */
public class Ex21 {
    public static boolean find(String key, Ex19<String> list) {
        if (list.isEmpty()){
            return false;
        }

        boolean flag = false;
        for (String str : list
        ) {
            if (key.equals(str)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        Ex19<String> list = new Ex19<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        find("7",list);
        find("3",list);
    }
}
