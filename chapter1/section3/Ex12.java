package chapter1.section3;
/**
 * @author ZZY
 * 该方法返回一个字符串栈的副本
 * */
public class Ex12 {
    public static ListStack<String> copy(ListStack<String> stringListStack) {
        ListStack<String> temp = new ListStack<>();
        ListStack<String> copy = new ListStack<>();

        //遍历参数栈，元素倒序入 temp 栈
        for (String s : stringListStack
        ) {
            temp.push(s);
        }

        //遍历 temp 栈，元素正序入 copy 栈
        for (String s : temp
        ) {
            copy.push(s);
        }

        return copy;
    }
}
