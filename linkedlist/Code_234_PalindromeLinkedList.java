package linkedlist;

/**
 * LeetCode 234. 回文链表
 * 
 * 题目描述：
 * 给定一个单链表的头节点 head，判断该链表是否为回文链表。
 * 
 * 解题思路：
 * 使用快慢指针找到链表的中点，同时反转前半部分链表。
 * 然后比较反转后的前半部分和后半部分是否相同。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_234_PalindromeLinkedList {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Find middle using slow/fast pointer
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        // If odd length, skip middle element
        ListNode second = fast == null ? slow : slow.next;
        ListNode first = prev;

        // Compare reversed first half with second half
        while (first != null && second != null) {
            if (first.val != second.val) {
                return false;
            }
            first = first.next;
            second = second.next;
        }
        return true;
    }
}
