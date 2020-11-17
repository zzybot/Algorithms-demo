package chapter1.section3.Ex33;

public interface Deque<E> extends Iterable<E> {

    //判断队列是否为空
    boolean isEmpty();

    //返回队列长度
    int size();

    //左端添加一个新元素
    void pushLeft(E item);

    //右端添加一个新元素
    void pushRight(E item);

    //左端删除一个元素
    E popLeft();

    //右端删除一个元素
    E popRight();
}
