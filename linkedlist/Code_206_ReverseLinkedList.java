package linkedlist;

/**
 * LeetCode 206. 反转链表
 * 
 * 题目描述：
 * 给定单链表的头节点 head ，反转链表，并返回反转后的链表。
 * 
 * 解题思路：
 * 使用双指针法迭代反转链表，或者使用递归方法反转链表。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)（迭代法），O(n)（递归法）
 */

public class Code_206_ReverseLinkedList {
    
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // two pointers
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    // recursive
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
