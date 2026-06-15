package linkedlist;

/**
 * LeetCode 25. K 个一组翻转链表
 * <p>
 * 题目描述：
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 解题思路：
 * 使用迭代的方法，每次处理 k 个节点进行翻转。
 * 维护前一组的尾节点和当前组的头节点，完成翻转后重新连接链表。
 * 如果剩余节点不足 k 个，则不进行翻转。
 * <p>
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_25_ReverseNodesInKGroup {

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

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode kthNode = prevGroupEnd;
            for (int i = 0; i < k && kthNode != null; i++) {
                kthNode = kthNode.next;
            }
            if (kthNode == null) {
                break; // Not enough nodes to reverse
            }

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kthNode.next;

            // Reverse the group
            ListNode prev = nextGroupStart;
            ListNode curr = groupStart;
            while (curr != nextGroupStart) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Connect with the previous part
            prevGroupEnd.next = kthNode;
            prevGroupEnd = groupStart; // Move to the end of the reversed group
        }

        return dummy.next;
    }
}
