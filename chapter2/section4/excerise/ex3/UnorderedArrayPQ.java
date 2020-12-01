package chapter2.section4.excerise.ex3;

import chapter2.tools.Utils;

import java.util.NoSuchElementException;


/**
 * 无序动态数组实现优先队列
 *
 * 写麻烦了，直接从 high 删除元素比较简单
 * @author ZZY
 */
public class UnorderedArrayPQ<Key extends Comparable<Key>> {
    private Key[] a = (Key[]) new Comparable[2];
    private int size;
    private int low = 0;
    private int high = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Key key) {
        if (size == a.length) {
            resize(a.length * 2);
        }
        a[high] = key;
        high = (++high) % a.length;
        size++;
    }

    public Key deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("pq is empty");
        }
        if (size < a.length / 4) {
            resize(a.length / 2);
        }
        int max = low;
        int index = low + 1;

        //如果只有一个元素，不执行
        while (index != high) {
            if (Utils.less(a[max], a[index])) {
                max = index;
            }
            index = (++index) % a.length;
        }

        Utils.exch(a, low, max);
        Key key = a[low];
        a[low] = null;
        size--;
        low++;
        System.out.println(key);
        return key;
    }

    private void resize(int length) {
        Key[] temp = (Key[]) new Comparable[length];
        int i = 0;

        do {
            temp[i] = a[low];
            i++;
            low = (++low) % a.length;
        } while (low != high);

        a = temp;
        low = 0;
        high = size;
    }

    public static void main(String[] args) {
        UnorderedArrayPQ<Integer> pq = new UnorderedArrayPQ<>();
        pq.insert(1);
        pq.insert(3);
        pq.insert(2);
        pq.insert(7);
        pq.insert(0);
        pq.insert(6);
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
    }
}
