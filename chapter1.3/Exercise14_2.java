package chapter1.section3;

import edu.princeton.cs.algs4.StdOut;
/**
 * 数组实现队列，更合理的利用数组空间AS
 * */
public class Exercise14_2 {
    private String[] items;
    private int n;
    private int first;
    private int last;

    public Exercise14_2(int capacity) {
        items = new String[capacity];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    /**
     * 修改容量，修改后将 first 和 last 重置
     * */
    public void resize(int capacity) {
        String[] temp = new String[capacity];

        for (int i = 0; i < n; i++) {
            temp[i] = items[(first + i) % 2];
        }

        items = temp;
        first = 0;
        last = n;
    }

    /**
     * 入队，队满时扩容，如果该队列出队过，回滚 last，重复利用数组空间
     * */
    public void enqueue(String item) {

        if (n == items.length) {
            resize(items.length * 2);
        }

        if (last == items.length) {
            last = 0; // Wrap around
        }

        items[last++] = item;
        n++;
    }

    //减容时不需要考虑实际用了多少数组容量，只要栈中数据够少就可以减容
    public String dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue underflow");
        } else {
            String item = items[first];
            items[first] = null; // To avoid loitering
            first++;

            if (first == items.length) {
                first = 0; // Wrap around
            }
            n--;

            if (n > 0 && n == items.length / 4) {
                resize(items.length / 2);
            }

            return item;
        }
    }

    public static void main(String[] args) {
        Exercise14_2 resizingArrayQueueOfStrings = new Exercise14_2(1);

        resizingArrayQueueOfStrings.enqueue("1");
        resizingArrayQueueOfStrings.enqueue("2");
        resizingArrayQueueOfStrings.dequeue();
        resizingArrayQueueOfStrings.enqueue("3");
        resizingArrayQueueOfStrings.dequeue();
        resizingArrayQueueOfStrings.enqueue("4");
        resizingArrayQueueOfStrings.enqueue("5");
        resizingArrayQueueOfStrings.enqueue("6");

    }
}
