package chapter3.section1.example;

import java.util.NoSuchElementException;

/**
 * 基于有序数组和二分查找的符号表
 *
 * @author ZZY
 */
public class BinarySearchSt<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int size;

    @SuppressWarnings("unchecked")
    public BinarySearchSt(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Value get(Key key) {
        check(key);
        int index = rank(key);
        //命中
        if (index < size && keys[index].compareTo(key) == 0) {
            return values[index];
        }
        //未命中
        return null;
    }

    public Value delete(Key key) {
        check(key);
        if (size == keys.length / 4){
            //减容
        }
            int index = rank(key);
        //命中
        if (index < size && keys[index].compareTo(key) == 0) {
            Value value = values[index];
            for (int i = index + 1; i < size; i++) {
                keys[i - 1] = keys[i];
                values[i - 1] = values[i];
            }
            return value;
        }
        //未命中
        return null;
    }

    public void put(Key key, Value value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("argument can not be null");
        }

        if (size == keys.length) {
            //扩容
        }

        int index = rank(key);
        //命中
        if (index < size && keys[index].compareTo(key) == 0) {
            values[index] = value;
            //未命中
        } else {
            for (int i = size; i > index; i--) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
            }
            keys[index] = key;
            values[index] = value;
            size++;
        }
    }

    private int rank(Key key) {
        int low = 0;
        int high = size - 1;
        return rank(key, low, high);
    }

    /**
     * rank 方法未命中时也会返回一个索引，范围是[0,size]
     * 在该索引处插入元素可以保证数组有序
     */
    private int rank(Key key, int low, int high) {
        if (low > high) {
            // 新 key 最小时，应该插入 keys[0]
            // 新 key 最大时，应该插入 keys[size]
            return low;
        }
        //避免整数溢出
        int mid = low + (high - low) / 2;
        int cmp = key.compareTo(keys[mid]);

        if (cmp > 0) {
            return rank(key, mid + 1, high);
        } else if (cmp < 0) {
            return rank(key, low, mid - 1);
        } else {
            return mid;
        }
    }

    private void check(Key key) {
        if (isEmpty()) {
            throw new NoSuchElementException("table is empty");
        }

        if (key == null) {
            throw new IllegalArgumentException("key can not be null");
        }
    }
}
