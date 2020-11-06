package chapter1.section3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ZZY
 */
public class LinkedList<E> implements Iterable<E> {
    private Node first;
    private int size;

    @Override
    public Iterator<E> iterator() {
        return new NodeIterator();
    }

    private class NodeIterator implements Iterator<E> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E e = current.item;
            current = current.next;
            return e;
        }
    }

    private class Node {
        private Node next;
        private E item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private Node createNode(E e) {
        Node node = new Node();
        node.item = e;
        return node;
    }

    /**
     * 在链表尾添加一个结点
     */
    public void push(E e) {
        if (size == 0) {
            first = new Node();
            first.item = e;
        } else {
            Node current;
            for (current = first; current.next != null; current = current.next) {
            }
            Node newNode = new Node();
            newNode.item = e;
            current.next = newNode;
        }
        size++;
    }

    /**
     * Ex19：删除最后一个结点
     */
    public void deleteLastNode() {
        if (!isEmpty()) {
            if (size == 1) {
                first = null;
            } else {
                Node current = first;
                for (int i = 0; i < size - 2; i++) {
                    current = current.next;
                }
                current.next = null;
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Ex20：接收一个 int 参数 k，删除链表的第 k 个元素（如果存在的话）
     * <p>
     * 删除第 k 个结点，包括三种情况：
     * 1. 结点不足 k 个或链表为空（链表为空的情况一开始忘记了），同时避免 k 小于零的情况
     * 2. 结点等于 k 个
     * 3. 结点大于 k 个
     * <p>
     * 等于 k 和大于 k分别执行不同的逻辑有点啰嗦，没有必要了。
     */
    public void delete(int k) {

        // k 不能大于 size，链表不能为空，k 小于或等于零都没有意义
        if (size < k || isEmpty() || k <= 0) {
            throw new NoSuchElementException();
            //k = 1 时要特殊对待，因为此时不存在 preNode
        } else if (k == 1) {
            first = first.next;
        } else {
            //啰嗦，没必要获取第 k 个 Node 的后一个 Node
            /*
            Node current = first;
            Node preNode = null;
            int count = 0;
            //循环 k 次，current 为第 k 个 Node 的后一个 Node
            for (int i = 0; i < k; i++) {
                //循环一次计数器加一
                count++;
                current = current.next;
                //循环 k-2 次时，获得第 k 个 Node 的前一个 Node
                if (count == k - 2) {
                    preNode = current;
                }
            }
            assert preNode != null;
            E e = preNode.next.item;
            preNode.next = current;
            */
            Node current;
            int count = 0;
            for (current = first; current != null; current = current.next) {
                //通过计数器找到第 k 个 Node 的前一个 Node,k <= size，所以第 k 个 Node 不可能为空
                if (count == k - 2) {
                    current.next = current.next.next;
                }
                count++;
            }
            size--;
        }
    }

    /**
     * Ex24：接收参数 Node，删除该结点的后续结点，如果参数结点或参数结点的后续结点为空，
     * 则什么也不做。
     */
    public void removeAfter(Node node) {
        if (node == null || isEmpty()) {
            //do nothing
        } else {
            Node current;
            for (current = first; current != null; current = current.next) {
                if (current.item.equals(node.item)) {
                    if (current.next != null) {
                        current.next = current.next.next;
                        size--;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Ex25：编写方法 insertAfter(); 接收两个 Node 参数，将第二个 Node 插入第一个 Node之后，如果两个参数为空则什么也不做
     * <p>
     * 排除不合理情况，找到 preNode 的位置，插入postNode
     * <p>
     * 比较条件，两个 Node 的数据域内容相同（item）
     */
    public void insertAfter(Node preNode, Node postNode) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (preNode == null || postNode == null) {
            //do nothing
        } else {
            Node current;
            for (current = first; current != null; current = current.next) {
                if (current.item.equals(preNode.item)) {
                    postNode.next = current.next.next;
                    current.next = postNode;
                    size++;
                    return;
                }
            }
        }
    }

    /**
     * Ex26：编写方法 remove();链表和一个字符串 key 作参数，删除链表中所有 item 域为 key 的结点
     * 分目标 Node 是 first，目标 Node 不是 first 两种情况
     * 目标 Node 是 first 在本题中有特殊情况要注意，所有 first.item == key 的情况都要囊括。
     */
    public void remove(String key) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //删除所有目标 first，first != null 为 true 时链表为空，【放在左侧避免右侧判断报空指针异常】
        while (first != null && key.equals(first.item)) {
            first = first.next;
            size--;
        }

        //与 first 的情况相同，这里也有“坑”要注意
        //用 if 判断会导致进行删除操作后补位的 Node 不会被检查 item
        //以非目标 Node 为起点，循环判断补位 Node 是否是目标 Node，如果不是，起点步进
        Node current;
        for (current = first; current != null; current = current.next) {
            /*
            if (key.equals(current.next.item)) {
                current.next = current.next.next;
                size--;
            }
            */
            while (current.next != null && key.equals(current.next.item)) {
                current.next = current.next.next;
                size--;
            }
        }

    }

    /**
     * Ex27：接收链表首结点作为参数，返回链表中数据域最大的结点的值，所有数据域均为正整数，链表尾空返回 0；
     * 1.链表为空返回 0；
     * 2.设置一个 maxValue 并赋值 first.item
     * 3.从第二个 Node 开始循环对比 node.item，大的赋值 maxValue
     * 4.返回 maxValue
     */
    public int max() {
        if (isEmpty()) {
            return 0;
        }

        int maxValue = (Integer) first.item;
        Node current;
        for (current = first.next; current != null; current = current.next) {
            int currentValue = (Integer) current.item;
            if (currentValue > maxValue) {
                maxValue = currentValue;
            }
        }
        return maxValue;
    }

    /**
     * Ex28：递归解决Ex27
     * 1.终点：链表遍历完毕，node == null
     * 2.收敛：node.next
     * 3.操作：currentMaxValue = (int) node.item;
     * 产生变化的变量都应该作为9参数
     */
    public int max(Node node,int currentMaxValue) {

        if (node == null) {
            return currentMaxValue;
        }
        if ((Integer) node.item > currentMaxValue) {
            currentMaxValue = (Integer) node.item;
        }
        //递：直到 node == null 为止，最后一次调用 max 方法获得返回值。归：将 currentMaxValue 依次返回
        return max(node.next,currentMaxValue);
    }

    /**
     * Ex30：
     * */
    public Node reverse(Node node){
        first = node;
        //逆链表为空时，reverse 是 null，正链表删除的结点排在 reverse 前。保证 reverse 始终指向你链表的首结点
        //first 始终指向正链表的首结点
        Node reverse = null;
        while (first != null){
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        //返回逆链表的首结点
        return reverse;
    }

    /**
     * Ex31:递归实现 reverse
     * */
    public Node reverseRecursion(Node first){
        if (first == null){
            return null;
        }
        if (first.next == null){
            return first;
        }
        Node second = first.next;
        //递：找到最后一个结点并返回
        Node reverse = reverseRecursion(second);
        //归：从倒数第二个结点开始向前执行，将其放置在最后一个结点之后即可
        first.next = null;
        second.next = first;

        return reverse;

    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);

        list.max(list.first, list.first.item);


//        list.reverseRecursion(list.first);
    }
}
