package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 208. Implement Trie (Prefix Tree)
 * 题目大意：
 * 实现一个前缀树（Trie），包含 insert, search, 和 startsWith 这三个操作。
 * 
 * 解题思路：
 * 使用一个嵌套的 TrieNode 类来表示前缀树的节点。每个节点包含一个子节点数组和一个标志位表示是否为单词的结尾。
 * insert 方法用于插入单词，search 方法用于搜索完整单词，startsWith 方法用于检查是否存在以给定前缀开头的单词。
 * 
 * 复杂度分析：
 * 时间复杂度：O(m)，其中 m 是单词的长度。每个操作都需要遍历单词的每个字符一次。
 * 空间复杂度：O(m)，在最坏情况下，前缀树需要存储所有插入的单词，每个字符都需要一个节点。
 */

public class Code_208_PrefixTree {
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    private final TrieNode root;

    public Code_208_PrefixTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return true;
    }
}

class PrefixTreeWithMap {
    private TrieNode root;

    public PrefixTreeWithMap() {
        root = new  TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.map.putIfAbsent(ch, new TrieNode());
            node = node.map.get(ch);
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    // 查询前缀树是否包含prefix
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.map.containsKey(ch)) {
                return null;
            }
            node = node.map.get(ch);
        }

        return node;
    }

    static class TrieNode {
        Map<Character, TrieNode> map = new HashMap<>();
        boolean isEnd = false;
    }
}
