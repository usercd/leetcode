package linkedlist;

/**
 * LeetCode 146. LRU缓存机制
 * 
 * 题目描述：
 * 设计并实现一个满足 LRU (最近最少使用) 缓存约束的数据结构。
 * 实现 LRUCache 类：
 * - LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * - int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * - void put(int key, int value) 如果关键字已经存在，则变更其数据值；
 *   如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，
 *   它应该在插入新项之前删除最久未使用的项。
 * 
 * 解题思路：
 * 使用双向链表和哈希表结合实现。双向链表用于维护使用顺序，哈希表用于快速访问节点。
 * 每次访问或插入节点时，将其移动到链表头部，表示最近使用。
 * 当容量超出限制时，移除链表尾部节点，即最久未使用的节点。
 * 
 * 时间复杂度：O(1) 对于 get 和 put 操作
 * 空间复杂度：O(capacity)
 */

public class Code_146_LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final java.util.Map<Integer, Node> map;
    private final Node head;
    private final Node tail;

    public Code_146_LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new java.util.HashMap<>();
        this.head = new Node(0, 0); // Dummy head
        this.tail = new Node(0, 0); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        insertAtHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertAtHead(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            insertAtHead(newNode);
            map.put(key, newNode);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    
}
