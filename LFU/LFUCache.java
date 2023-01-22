package LFU;

import java.util.HashMap;
import java.util.LinkedHashSet;

class LFUCache {
    int cap;
    HashMap<Integer, Integer> keyToVal;
    HashMap<Integer, Integer> keyToFreq;
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    int minFreq;

    // 初始化
    public LFUCache(int capacity) {
        this.cap = capacity;
        this.minFreq = 0;
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
    }

    // 在缓存中查询key
    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 重点！key对应的freq需要加1
        increaseFreq(key);
        return keyToVal.get(key);
    }

    // 将key和val放入缓存
    public void put(int key, int value) {
        // 根据题意：capacity的范围为[0,10^4]
        if (this.cap == 0) {
            return;
        }
        // 如果key已经存在，则修改对应的val即可
        // 这里，三个哈希表只需要更新哈希表KV。
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            // key对应的freq加一
            increaseFreq(key);
            return;
        }

        // 如果key不存在，则需要插入键值对(key,val)
        // 如果容量已满，需要删除一个freq最小(多个的话删最旧的)的键值对
        // 这里需要同时更新KV,KF,FK
        if (keyToVal.size() >= this.cap) {
            removeMinFreqKey();
        }

        // 插入key和val，对应的freq加1
        // 这里需要更新三个哈希表：KV,KF,FK
        keyToVal.put(key, value);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        // 插入新的键值对后，需要更新最小freq
        this.minFreq = 1;
    }

    // 需要更新哈希表KF，FK
    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        // 更新KF表
        keyToFreq.put(key, freq + 1);// 自动覆盖了key原来对应的val
        // 更新FK表

        // 先删除freq对应的key列表中的key
        freqToKeys.get(freq).remove(key);
        // 如果此时freq对应的key列表为空，则删除这个freq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            // 如果删除的freq刚好是minFreq，则更新minFreq！！！
            if (freq == this.minFreq) {
                this.minFreq += 1;
            }
        }
        // 再在freq+1对应的key列表中添加key
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
    }

    // 需要同时更新KV，
    private void removeMinFreqKey() {
        // 最小freq对应的key列表
        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
        // 先找到需要删除的key
        int keyRemoved = keyList.iterator().next();
        // 更新FK
        keyList.remove(keyRemoved);
        if (keyList.isEmpty()) {
            freqToKeys.remove(this.minFreq);// 不需要再更新minFreq
        }
        // 更新KV
        keyToVal.remove(keyRemoved);
        // 更新KF
        keyToFreq.remove(keyRemoved);
    }
}