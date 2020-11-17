package chapter1.section3.Ex38;

import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * @author ZZY
 * Ex38：动态数组实现
 */
public class ResizingArrayGeneralizedQueue<E> implements GeneralizedQueue<E> {
    @SuppressWarnings("unchecked")
    E[] items = (E[]) new Object[2];
    private int size;
    /**
     * head 是队列头元素的索引
     */
    private int head;
    /**
     * tail 是队列尾元素的【下一位】的索引
     */
    private int tail;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(E e) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[tail] = e;
        //tail == items.length 时重置为 0
        tail = (tail + 1) % items.length;
        StdOut.println(this);
        size++;
    }

    @Override
    public E delete(int k) {
        if (k > size || k <= 0) {
            throw new NoSuchElementException("index of " + k + " out of bounds");
        }
        //第 k 个元素的索引
        int index = (head + k - 1) % items.length;
        E e = items[index];
        //循环 k - 1 次，将第 k 个元素前的所有所有元素向后移动一位
        for (int i = 0; i < k - 1; i++) {
            //不使用取余而是取模，因为当 index = 0 时，我们需要让它的前一位是数组的最后一位
            //比如，数组长度为4， --index 为 -1 时，此时索引应该是 3，而不是 -1，取余时余数符号与被除数相同
            int current = Math.floorMod(--index, items.length);
            items[(current + 1) % items.length] = items[current];
        }

        //移动结束，将 head 处元素置为 null
        items[head] = null;
        head = (head + 1) % items.length;
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        StdOut.println(this + " delete : k = " + k + " value = " + e);
        return e;
    }

    private void resize(int newsize) {
        @SuppressWarnings("unchecked")
        E[] temp = (E[]) new Object[newsize];
        int current = head;
        int k = 0;
        /*while (current != tail) {
            temp[k++] = items[current];
            current = (current + 1) % items.length;
        }*/
        //使用 do while，因为队满时 head 等于 tail
        do {
            temp[k++] = items[current];
            current = (current + 1) % items.length;
        } while (current != tail);
        //重置
        head = 0;
        tail = size;
        items = temp;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[empty]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (E item : items) {
            sb.append(String.format("  %-3s|", item == null ? " " : item));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        GeneralizedQueue<Integer> queue = new ResizingArrayGeneralizedQueue<>();
        for(int i = 0; i < 8; i++) {
            queue.insert(i);
        }
        for(int i = 0; i < 4; i++) {
            queue.delete(1);
        }
        for(int i = 8; i < 12; i++) {
            queue.insert(i);
        }
        queue.delete(4);
        queue.delete(2);
        queue.delete(3);
        queue.delete(1);
        queue.delete(2);
        queue.delete(1);
    }
}
