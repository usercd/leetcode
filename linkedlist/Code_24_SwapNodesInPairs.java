package linkedlist;

/**
 * LeetCode 24. 两两交换链表中的节点
 * 
 * 题目描述：
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * 解题思路：
 * 使用迭代或递归的方法交换每对相邻节点。
 * 迭代方法中，使用一个虚拟头节点简化边界条件处理。
 * 递归方法中，交换当前对节点后递归处理剩余节点。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)（迭代法），O(n)（递归法）
 */

public class Code_24_SwapNodesInPairs {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // Swapping
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Move prev to the next pair
            prev = first;
        }

        return dummy.next;
    }

    // recursive approach
    public ListNode swapPairsRecursive(ListNode head) {
        if (head == null || head.next == null) {return head;}

        ListNode newHead = head.next;
        head.next = swapPairsRecursive(newHead.next);
        newHead.next = head;

        return newHead;
    }
}