package LRU_v2;

import java.util.LinkedHashMap;

public class LRUCache {
    int cap;
    LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.cap = capacity;
        cache = new LinkedHashMap<>();
    }

    public int get(int k) {
        if (!cache.containsKey(k)) {
            return -1;
        }
        // 将key提升为最近使用
        makeRecently(k);
        return cache.get(k);
    }

    public void put(int k, int v) {
        if (cache.containsKey(k)) {
            // 修改key对应的val
            cache.put(k, v);
            // 将key变为最近使用
            makeRecently(k);
        }
        // 容量已满，需要先删除最久未使用的元素
        if (cache.size() == cap) {
            // 先找到最久未使用元素的key
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 然后再添加新元素
        cache.put(k, v);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        cache.remove(key);
        cache.put(key, val);
    }

}
