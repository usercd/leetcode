package linkedlist;

/**
 * LeetCode 142. 环形链表 II
 * 
 * 题目描述：
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 
 * 解题思路：
 * 使用快慢指针（Floyd's Tortoise and Hare 算法）检测链表中的环。
 * 当快慢指针相遇时，使用另一个指针从头开始与慢指针同步前进，
 * 两者相遇的节点即为环的入口节点。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_142_LinkedListCycleII {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Detect cycle using Floyd's Tortoise and Hare algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // Cycle detected, find the entry point
                ListNode entry = head;
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry; // Entry point of the cycle
            }
        }
        return null; // No cycle
    }
}
