package linkedlist;

/**
 * LeetCode 2. 两数相加
 * 
 * 题目描述：
 * 给出两个 非空 的链表用来表示两个非负的整数。
 * 数字最高位位于链表开始位置。它们的每个节点只存储一位数字。
 * 将这两数相加会返回一个新的链表来表示它们的和。
 * 
 * 解题思路：
 * 使用一个虚拟头节点来简化结果链表的构建过程。
 * 遍历两个输入链表，同时处理进位，直到两个链表都遍历完且没有进位。
 * 
 * 时间复杂度：O(max(m, n))，其中 m 和 n 分别是两个链表的长度。
 * 空间复杂度：O(max(m, n))，用于存储结果链表。
 */

public class Code_2_AddTwoNumbers {
    
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = l1, q = l2, current = dummy;
        int carry = 0;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummy.next;
    }
}
