package linkedlist;

/**
 * LeetCode 707. 设计链表
 * 
 * 题目描述：
 * 设计一个链表，支持以下操作：
 * - get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回 -1。
 * - addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。
 * - addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * - addAtIndex(index, val)：在链表中的第 index 个节点之前添加值为 val 的节点。
 *   如果 index 等于链表的长度，则该节点将附加到链表的末尾。
 *   如果 index 大于链表长度，则不会插入节点。
 * - deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 * 
 * 解题思路：
 * 使用单向链表实现上述功能，维护一个虚拟头节点以简化边界条件处理。
 * 每个操作根据索引遍历链表进行相应的增删改查操作。
 * 
 * 时间复杂度：
 * - get：O(n)
 * - addAtHead：O(1)
 * - addAtTail：O(n)
 * - addAtIndex：O(n)
 * - deleteAtIndex：O(n)
 * 
 * 空间复杂度：O(1)
 */

public class Code_707_DesignLinkedList {
    class MyLinkedList {

        class Node {
            int val;
            Node next;

            Node(int val) {
                this.val = val;
            }
        }

        private Node head;
        private int size;

        public MyLinkedList() {
            head = new Node(0); // Dummy head
            size = 0;
        }

        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            Node curr = head.next;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.val;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0) {
                index = 0;
            }
            Node prev = head;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            Node newNode = new Node(val);
            newNode.next = prev.next;
            prev.next = newNode;
            size++;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            Node prev = head;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            prev.next = prev.next.next;
            size--;
        }
    }
}
