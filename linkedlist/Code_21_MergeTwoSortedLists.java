package linkedlist;

/**
 * LeetCode 21. 合并两个有序链表
 * 
 * 题目描述：
 * 将两个升序链表合并为一个新的 升序 链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 * 
 * 解题思路：
 * 使用双指针方法遍历两个链表，比较当前节点的值，将较小的节点连接到新链表中。
 * 最后将剩余节点连接到新链表末尾。
 * 
 * 时间复杂度：O(n + m)，其中 n 和 m 分别是两个链表的长度。
 * 空间复杂度：O(1)
 */

public class Code_21_MergeTwoSortedLists {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Append the remaining nodes of l1 or l2
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        return dummy.next; // Return the merged list, skipping the dummy node
    }
}
