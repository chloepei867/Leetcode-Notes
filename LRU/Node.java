package LRU;

public class Node {
    public int key;
    public int val;
    Node prev, next;

    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}