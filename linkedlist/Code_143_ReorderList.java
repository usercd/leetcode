package linkedlist;

public class Code_143_ReorderList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public void reorderList(ListNode head) {
        // 获取中点
        ListNode mid = middleNode(head);
        // 记录后半段链表
        ListNode second = mid.next;
        // 断开
        mid.next = null;

        // 反转后半段
        second = reverse(second);
        while (second != null) {
            ListNode headNext = head.next;
            ListNode secondNext = second.next;
            head.next = second;
            second.next = headNext;
            head = headNext;
            second = secondNext;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    private ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
