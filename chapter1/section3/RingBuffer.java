package chapter1.section3;
/**
 * Ex39：数组实现环形队列
 *      first 是第一个元素的索引，last 是最后一个元素的下一位的索引，
 *
 * 思路：delete 时 while(isEmpty())，队列为空时循环等待其他线程唤醒
 *      insert 时 while(isFull())，队满时循环等待其他线程唤醒
 *      创建消费者线程和生产者线程
 * */
public class RingBuffer<E> {

}
