package chapter2.section4.excerise;

import java.util.NoSuchElementException;

/**
 * 链表实现二叉堆优先级队列
 * @author ZZY
 */
public class LinkedListPriortyQueue<E extends Comparable<E>> {

    private class Node {
        private E item;
        private Node prev;
        private Node leftNext;
        private Node rightNext;
        private int height;

        public Node(E item) {
            this.item = item;
        }
    }

    /**
     * 根节点
     */
    private Node root;

    public LinkedListPriortyQueue() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(E key) {
        root = insert(root, key);
    }

    private Node insert(Node node, E key) {
        if (node == null) {
            return new Node(key);
        }

        //堆有序
        if (key.compareTo(node.item) > 0) {
            E temp = node.item;
            node.item = key;
            key = temp;
        }

        //检索权重更小的分支
        int leftHeight = getHeight(node.leftNext);
        int rightHeight = getHeight(node.rightNext);

        if (rightHeight < leftHeight) {
            node.rightNext = insert(node.rightNext, key);
            node.rightNext.prev = node;
        } else {
            node.leftNext = insert(node.leftNext, key);
            node.leftNext.prev = node;
        }

        //修改权重，自底向上，节点每高一层，权重加一
        node.height = 1 + getMaxHeight(node.leftNext, node.rightNext);
        return node;
    }

    public E deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("priorty queue is empty");
        }

        //如果队列中只有根节点
        if (root.height == 0) {
            E max = root.item;
            root = null;
            System.out.println(max);
            return max;
        }

        //获取尾节点及尾节点的父节点
        Node last = getLast();
        Node parent = last.prev;

        //交换根节点与尾节点的数据域
        E max = root.item;
        root.item = last.item;

        //删除尾节点
        if (isLeft(last)) {
            last.prev.leftNext = null;
            //删除左分支时会影响权重，自底向上更新权重
            while (parent != null) {
                parent.height = 1 + getMaxHeight(parent.leftNext, parent.rightNext);
                parent = parent.prev;
            }
        } else {
            last.prev.rightNext = null;
        }

        //下沉
        Node current = root;
        while (hasChild(current)) {
            Node maxNode = maxNode(current.leftNext, current.rightNext);

            if (current.item.compareTo(maxNode.item) > 0) {
                break;
            }
            //交换数据域
            E temp = maxNode.item;
            maxNode.item = current.item;
            current.item = temp;

            //步进
            current = maxNode;
        }
        //测试用
        System.out.println(max);
        return max;
    }

    private Node maxNode(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        return node2.item.compareTo(node1.item) > 0 ? node2 : node1;
    }

    private boolean isLeft(Node last) {
        return last.prev.leftNext == last;
    }

    /**
     * 返回尾节点
     * 左分支权重大于右分支时，检索左分支；相等时检索右分支
     */
    private Node getLast() {
        if (isEmpty()) {
            return null;
        }

        Node current = root;

        while (hasChild(current)) {
            if (getHeight(current.leftNext) > getHeight(current.rightNext)) {
                current = current.leftNext;
            } else {
                current = current.rightNext;
            }
        }
        return current;
    }

    private int getMaxHeight(Node node1, Node node2) {
        return Math.max(getHeight(node1), getHeight(node2));
    }

    private int getHeight(Node node) {
        return node == null ? -1 : node.height;
    }

    private boolean hasChild(Node node) {
        return node.leftNext != null || node.rightNext != null;
    }

    public static void main(String[] args) {
        LinkedListPriortyQueue<Integer> pq = new LinkedListPriortyQueue<>();
        pq.insert(1);
        pq.insert(2);
        pq.insert(3);
        // 0 存不进去？
        pq.insert(0);
        pq.insert(9);
        pq.insert(-10);
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        pq.deleteMax();
        /**
         * 9
         * 3
         * 2
         * 1
         * 0
         * -10
         * Exception in thread "main" java.util.NoSuchElementException: priorty queue is empty
         * */
    }
}
