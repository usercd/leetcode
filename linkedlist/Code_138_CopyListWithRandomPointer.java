package linkedlist;

/**
 * LeetCode 138. 复制带随机指针的链表
 * 
 * 题目描述：
 * 给定一个链表，每个节点包含一个额外增加的随机指针，
 * 该指针可以指向链表中的任何节点或空节点。
 * 请实现一个函数来复制这个链表。
 * 
 * 解题思路：
 * 使用三步法复制带随机指针的链表：
 * 1. 在每个原节点后面插入对应的新节点。
 * 2. 设置新节点的随机指针。
 * 3. 将新旧链表分离。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_138_CopyListWithRandomPointer {
    
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Step 1: Create new nodes and insert them next to original nodes
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.val);
            newNode.next = current.next;
            current.next = newNode;
            current = newNode.next;
        }

        // Step 2: Assign random pointers for the new nodes
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        // Step 3: Separate the two lists
        Node dummyHead = new Node(0);
        Node copyCurrent = dummyHead;
        current = head;

        while (current != null) {
            copyCurrent.next = current.next;
            copyCurrent = copyCurrent.next;

            current.next = current.next.next;
            current = current.next;
        }

        return dummyHead.next;
    }
}
