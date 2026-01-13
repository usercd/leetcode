package linkedlist;

/**
 * LeetCode 141. 环形链表
 * 
 * 题目描述：
 * 给定一个链表，判断链表中是否有环。
 * 
 * 解题思路：
 * 使用快慢指针（Floyd's Tortoise and Hare 算法）检测链表中的环。
 * 慢指针每次移动一步，快指针每次移动两步。
 * 如果链表中存在环，快慢指针最终会相遇；否则，快指针会到达链表末尾。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_141_LinkedListCycle {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
