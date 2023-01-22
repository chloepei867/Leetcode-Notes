package Trie;

import java.util.LinkedList;
import java.util.List;

class TrieMap<V> {
    // ASCII码的个数
    private static final int R = 256;
    // 当前存储在map中的键值对个数
    private int size = 0;

    private static class TrieNode<V> {
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];
    }

    // Trie树根节点
    TrieNode<V> root = null;

    // 在map中搜索给定的key对应的val，不存在则返回null
    public V get(String key) {
        // 先判断map中是否存在这个key
        if (!containsKey(key)) {
            return null;
        }
        return getNode(root, key).val;
    }

    // 判断map中是否存在key【键】，是则返回true，否则返回false
    public boolean containsKey(String key) {
        // 先找到这个key对应的node
        TrieNode<V> keyNode = getNode(root, key);
        // 如果keyNode非空，只能说明map中存在【前缀】key
        // 而当keyNode.val非空时，才能判断map中存在【键】key
        return keyNode != null && keyNode.val != null;
    }

    // 判断是否存在前缀为prefix的【键】
    public boolean hasKeyWithPrefix(String prefix) {
        return getNode(root, prefix) != null;
    }

    // 在map的所有【键】中寻找query的最短前缀
    // shortestPrefixOf("themxyz") -> "the"
    public String shortestPrefixOf(String query) {
        for (int i = 0; i < query.length(); i += 1) {
            String sub = query.substring(0, i);
            if (containsKey(sub)) {
                return sub;
            }
        }
        return "";
    }

    // 在map的所有【键】中寻找query的最长前缀
    // longestPrefixOf("themxyz") -> "them"
    public String longestPrefixOf(String query) {
        for (int i = query.length() - 1; i >= 0; i -= 1) {
            String sub = query.substring(0, i);
            if (containsKey(sub)) {
                return sub;
            }
        }
        return "";

    }

    // 搜索并返回【前缀】为prefix的所有【键】
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new LinkedList<>();
        // 先找到prefix对应的节点x
        TrieNode<V> x = getNode(root, prefix);
        // 如果不存在这个prefix，直接返回empty list
        if (x == null) {
            return res;
        }
        // dfs遍历以x为根节点的trie树
        dfs(x, new StringBuilder(prefix), res);
        return res;
    }

    // 通配符，匹配任意字符
    // keysWithPattern("t.a.") -> ["team", "that"]
    public List<String> keysWithPattern(String pattern) {
        List<String> res = new LinkedList<>();
        traverse(root, new StringBuilder(), pattern, 0, res);
        return res;

    }

    // 通配符，匹配任意字符，判断是否存在匹配的键
    public boolean hasKeyWithPattern(String pattern) {
        return hasKeyWithPattern(root, pattern, 0);
    }

    // 在map中添加或修改键值对
    public void put(String key, V val) {
        if (!containsKey(key)) {
            size += 1;
        }
        root = put(root, key, val, 0);
    }

    // 在 Map 中删除 key
    public void remove(String key) {
        if (!containsKey(key)) {
            return;
        }
        // 递归修改数据结构要接收函数的返回值
        root = remove(root, key, 0);
        size--;
    }

    // 定义：在以 node 为根的 Trie 树中删除 key[i..]，返回删除后的根节点
    private TrieNode<V> remove(TrieNode<V> node, String key, int i) {
        if (node == null) {
            return null;
        }
        if (i == key.length()) {
            // 找到了 key 对应的 TrieNode，删除 val
            node.val = null;
        } else {
            char c = key.charAt(i);
            // 递归去子树进行删除
            node.children[c] = remove(node.children[c], key, i + 1);
        }
        // 后序位置，递归路径上的节点可能需要被清理
        if (node.val != null) {
            // 如果该 TireNode 存储着 val，不需要被清理
            return node;
        }
        // 检查该 TrieNode 是否还有后缀
        for (int c = 0; c < R; c++) {
            if (node.children[c] != null) {
                // 只要存在一个子节点（后缀树枝），就不需要被清理
                return node;
            }
        }
        // 既没有存储 val，也没有后缀树枝，则该节点需要被清理
        return null;
    }

    // 在以node为根节点的trie树中插入key[i:]，并返回插入完成后的根节点
    private TrieNode<V> put(TrieNode<V> node, String key, V val, int i) {
        if (node == null) {
            // 如果树枝不存在，则新建
            node = new TrieNode<>();
        }
        //
        if (i == key.length()) {
            // key的路径已插入完成，将值val存入节点
            node.val = val;
            return node;
        }
        char c = key.charAt(i);
        // 递归插入子节点，并接收返回值
        node = put(node.children[c], key, val, i + 1);
        return node;
    }

    // 函数定义：从node节点开始匹配pattern[i]，一旦匹配成功就返回
    private boolean hasKeyWithPattern(TrieNode<V> node, String pattern, int i) {
        if (node == null) {
            // 树枝不存在，则匹配失败
            return false;
        }
        if (i == pattern.length()) {
            return node.val != null;
        }
        char c = pattern.charAt(i);
        // 没有遇到通配符
        if (c != '.') {
            // 从node.children[c]节点开始匹配pattern[i+1:]
            return hasKeyWithPattern(node.children[c], pattern, i + 1);
        }
        // 遇到通配符
        for (int j = 0; j < R; j += 1) {
            if (hasKeyWithPattern(node.children[j], pattern, i + 1)) {
                return true;
            }
        }
        return false;
    }

    // 遍历函数，尝试在【以node为根节点的Trie树中】匹配pattern[i:]
    private void traverse(TrieNode<V> node, StringBuilder path, String pattern, int i, List<String> res) {
        if (node == null) {
            // 树枝不存在，则匹配失败
            return;
        }
        if (i == pattern.length()) {
            // pattern匹配完成
            if (node.val != null) {
                res.add(path.toString());
            }
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.') {
            // pattern[i]是通配符，则可以替代任意字符
            // 多叉树(回溯算法)遍历框架
            for (char j = 0; j < R; j += 1) {
                path.append(j);
                traverse(node.children[j], path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1);
            }
        } else {
            // pattern[i]是普通字符c
            path.append(c);
            traverse(node.children[c], path, pattern, i + 1, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    // 遍历以node为根节点的trie树，找到所有的【键】
    private void dfs(TrieNode<V> node, StringBuilder path, List<String> res) {
        if (node == null) {// 到达trie树的叶子节点
            return;
        }
        if (node.val != null) {
            // 收集结果
            res.add(path.toString());
        }
        // 回溯算法遍历框架
        for (char c = 0; c < R; c += 1) {
            // 做选择
            path.append(c);
            if (node.children[c] != null) {
                dfs(node.children[c], path, res);
            }
            // 撤销选择
            path.deleteCharAt(path.length() - 1);
        }
    }

    // 工具函数：从节点node开始搜索是否存在key这个【前缀】
    // 存在则返回该节点，否则返回null
    private TrieNode<V> getNode(TrieNode<V> node, String prefix) {
        // 指针p
        TrieNode<V> p = node;
        // 从节点node开始搜索
        for (int i = 0; i < prefix.length(); i += 1) {
            if (p == null) {
                return null;
            }
            char ch = prefix.charAt(i);
            // 指针移向p的某个子树
            p = p.children[ch];
        }
        return p;
    }

    public int size() {
        return size;
    }
}
