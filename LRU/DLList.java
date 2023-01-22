package LRU;

public class DLList {
    private Node head, tail;
    private int size;

    // 无参数初始化
    public DLList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    // 在链表尾部添加节点x，时间为O(1)
    public void addLast(Node x) {
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        x.prev = tail.prev;
        size++;
    }

    // 删除链表中的节点x(x一定存在)
    // 由于是双链表，且给的是目标节点，时间为O(1)
    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    // 删除链表中第一个节点并返回该节点，时间为O(1)
    public Node removeFirst() {
        // 当链表为空时，返回null
        if (head.next == tail) {
            return null;
        }
        Node firstNode = head.next;
        remove(firstNode);
        return firstNode;
    }

    // 返回链表长度，时间为O(1)
    public int size() {
        return size;
    }
}
