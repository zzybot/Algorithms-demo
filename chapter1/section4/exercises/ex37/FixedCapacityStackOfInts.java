package chapter1.section4.exercises.ex37;

/**
 *
 * @author ZZY
 */
public class FixedCapacityStackOfInts {
    /**
     * 栈空间
     */
    private final int[] a;
    /**
     * 栈长度
     */
    private int n;

    public FixedCapacityStackOfInts(int cap) {
        this.a = new int[cap];
    }

    /**
     * 入栈：最大索引处添加一个元素并使栈长度加一
     */
    public void push(int item) {
        a[n++] = item;
    }

    /**
     * 出栈：返回最大索引处元素并使长度减一
     */
    public int pop() {
        return a[--n];
    }

    /**
     * 栈是否为空
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 判断栈满
     */
    public boolean isFull() {
        return n == a.length;
    }

    /**
     * 返回栈长度
     */
    public int size() {
        return n;
    }
}
