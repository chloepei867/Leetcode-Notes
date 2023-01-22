package LRU;

import java.util.HashMap;

public class LRUCache {
    // key -> Node(key, val)
    private HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private DLList cache;
    // 最大容量
    private int cap;

    // 初始化
    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DLList();
    }

    // 查询key对应的value
    public int get(int k) {
        // 当前查询的key不存在
        if (!map.containsKey(k)) {
            return -1;
        }
        // 将该元素提升为最近使用的
        makeRecently(k);
        return map.get(k).val;
    }

    public void put(int k, int v) {
        // 如果map中已经包含该key
        if (map.containsKey(k)) {
            // 则删除旧数据
            removeKey(k);
            // 添加新数据
            addRecently(k, v);
            return;
        }
        // 考虑容量
        if (cache.size() == cap) {
            // 删除最久未使用的
            removeLeastRecently();
        }
        // 添加新数据
        addRecently(k, v);
    }

    // 将某个key提升为最近使用的
    private void makeRecently(int k) {
        // 先从哈希表中查询到这个key对应的节点
        Node target = map.get(k);
        // 在链表中删除这个节点
        cache.remove(target);
        // 再重新插入到链表尾部
        cache.addLast(target);
    }

    // 添加最近使用的元素(key，val)(当前map中一定还没有这个key)
    // 这里先不考虑map中已存在key的问题
    private void addRecently(int k, int v) {
        Node x = new Node(k, v);
        // 在链表尾部插入节点x
        cache.addLast(x);
        // 在map中添加对应的映射
        map.put(k, x);
    }

    // 删除某一个key
    private void removeKey(int k) {
        // 先在map中查询对应的node
        Node x = map.get(k);
        // 在链表中删除节点x
        cache.remove(x);
        // 在map中删除对应的映射
        map.remove(k);
    }

    // 删除最久未使用的元素
    private void removeLeastRecently() {
        // 在链表中删除并返回第一个节点
        Node x = cache.removeFirst();
        // 查询该节点对应的key
        int keyRemoved = x.key;
        // 在map中删除对应的映射
        map.remove(keyRemoved);
    }
}
