package linkedlist;

/**
 * LeetCode 160. 相交链表
 * 
 * 题目描述：
 * 给定两个单链表的头节点 headA 和 headB，找出并返回两个单链表相交的起始节点。
 * 如果两个链表没有交点，返回 null。
 * 
 * 解题思路：
 * 使用双指针法，分别遍历两个链表，当一个指针到达链表末尾时，跳转到另一个链表的头节点。
 * 这样两个指针最终会在相交节点处相遇，或者都为 null（无交点）。
 * 
 * 时间复杂度：O(m + n)，其中 m 和 n 分别是两个链表的长度
 * 空间复杂度：O(1)
 */

public class Code_160_IntersectionOfTwoLinkedLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }
}
