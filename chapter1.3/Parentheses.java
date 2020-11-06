package chapter1.section3;

import edu.princeton.cs.algs4.StdIn;
/**
 * 检查括号是否正确配对
 * */
public class Parentheses {
    public static void main(String[] args) {
        ListStack<String> stack = new ListStack<>();
        while (!StdIn.isEmpty()){
            String str = StdIn.readString();
            if ("(".equals(str) || "[".equals(str) || "{".equals(str)){
                stack.push(str);
                //出栈前保证栈不为空
            }else if (!stack.isEmpty()){
                //读到一个右括号就执行一次出栈，对比两符号是否成对，不成对输出 false 并中断循环
                if (")".equals(str)){
                    if (!"(".equals(stack.pop())){
                        System.out.println(false);
                        return;
                    }
                }else if ("]".equals(str)){
                    if (!"[".equals(stack.pop())){
                        System.out.println(false);
                        return;
                    }
                }else {
                    if (!"{".equals(stack.pop())){
                        System.out.println(false);
                        return;
                    }
                }
            }
        }
        //程序运行结束后，栈应该为空，如果不为空输出 false
        System.out.println(stack.isEmpty());
    }
}
