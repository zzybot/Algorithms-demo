package chapter1.section3;

import com.sun.media.sound.SF2InstrumentRegion;
import edu.princeton.cs.algs4.StdOut;

/**
 * Ex40：双向链表实现 LRU 队列，最近最少使用（刚访问过的元素被移动到队头，队列中没有的元素直接插入队头，从队尾删除元素）
 * 该队列头插尾删，插入新元素前判断该元素在队列中是否已经存在，如果存在就删除该元素并将新元素插入队头。
 * 相当于将最新插入的元素移动到队头
 * <p>
 * 编写一个 serach 方法，用来检索元素是否已存在队列中，如果存在返回该结点，不存在则返回 null；
 */
public class MoveToFront<E> {
    private class Node {
        private Node next;
        private Node prev;
        private E item;

        public Node() {
        }

        public Node(Node next, Node prev, E item) {
            this.next = next;
            this.prev = prev;
            this.item = item;
        }

        //当前结点前插入新结点，由 header 调用
        public void insertAfter(E e) {
            Node newNode = new Node(next, this, e);
            if (next != null) {
                next.prev = newNode;
            }
            next = newNode;
        }

        //删除当前结点
        public E delete() {
            E e = item;
            item = null;
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
            return e;
        }
    }

    private int size;
    private Node header = new Node();
    private Node tailer = new Node();
    /*
    * public MoveToFront(){
    *     header = new Node(tailer,null,null);
    *     tailer = new Node(null,header,null);
    * }
    *
    * 低级错误，搞混了引用和对象，在初始化 header 时，tailer 指向 null，此时 header.next 就是 null
    * 即使之后对 tailer 初始化，header.next 与 tailer 关联的也不是同一个对象，因为 null 本身不是对象只是一个表示该引用没有关联任何对象的符号
    * */
    public MoveToFront() {
        header.next = tailer;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) {
        Node node = search(e);
        //先把队列中重复的元素删除
        if (node != null) {
            node.delete();
        }
        header.insertAfter(e);
        size++;
        StdOut.println(this);
    }

    //删除队尾元素
    public E dequeue() {
        E e = tailer.prev.delete();
        size--;
        StdOut.println(this + "       delete element = " + e);
        return e;
    }

    public Node search(E e) {
        Node current;
        for (current = header.next; current != tailer; current = current.next) {
            if (e.equals(current.item)) {
                return current;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[empty]";
        }
        StringBuilder sb = new StringBuilder();
        Node current;
        for (current = header.next; current.next != tailer; current = current.next) {
            sb.append(current.item + "->");
        }
        sb.append(current.item);

        return sb.toString();
    }

    public static void main(String[] args) {
        MoveToFront<Integer> queue = new MoveToFront<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(1);
    }
}
