package chapter1.section3.Ex38;

/**
 * Ex38：使用数组或链表实现队列
 */
public interface GeneralizedQueue<E> {
    boolean isEmpty();

    void insert(E e);

    E delete(int k);
}
