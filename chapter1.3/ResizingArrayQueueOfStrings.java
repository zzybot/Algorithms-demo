package chapter1.section3;


import java.util.NoSuchElementException;

/**
 * 使用数组实现队列，要求该队列可动态调整大小
 *
 * @author ZZY
 */
public class ResizingArrayQueueOfStrings {
    private static final int INIT_CAPACITY = 1;
    private String[] a;
    private int n;
    private int first;
    private int last;

    public ResizingArrayQueueOfStrings() {
        n = 0;
        first = 0;
        last = 0;
        a = new String[INIT_CAPACITY];
    }

    public String dequeue() {
        if (isEmpty()){
            throw new NoSuchElementException("Queue underflow");
        }
        String item = a[first];
        a[first] = null;
        first++;
        n--;
        //必要时修改数组容量
        if (last > 0 && last == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public void enqueue(String item) {
        //必要时修改数组容量
        if (last == a.length) {
            resize(a.length * 2);
        }
        a[last++] = item;
        n++;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        String[] temp = new String[capacity];
        for (int i = first; i < n; i++) {
            temp[i - first] = a[i];
        }
        a = temp;
        first = 0;
        last = n;
    }

    public static void main(String[] args) {
        ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
    }
}
