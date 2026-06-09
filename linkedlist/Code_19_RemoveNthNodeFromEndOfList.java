package linkedlist;

/**
 * LeetCode 19. 删除链表的倒数第 N 个结点
 * 
 * 题目描述：
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 
 * 解题思路：
 * 使用双指针法，先让第一个指针移动 n+1 步，然后同时移动两个指针，
 * 当第一个指针到达链表末尾时，第二个指针正好指向倒数第 n 个节点的前一个节点。
 * 然后调整指针跳过该节点即可。
 * 
 * 时间复杂度：O(L)，其中 L 是链表的长度。我们只遍历了链表一次。
 * 空间复杂度：O(1)，只使用了常数级别的额外空间。
 */

public class Code_19_RemoveNthNodeFromEndOfList {
    
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    private static ListNode createList(int[] values) {
        if (values == null || values.length == 0) return null;

        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        return head;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;

        // Move first n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            if (first == null) {
                return head; // n is larger than the length of the list
            }
            first = first.next;
        }

        // Move both pointers until first reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // Remove the nth node from the end
        if (second.next != null) {
            second.next = second.next.next;
        }

        return dummy.next; // Return the modified list, skipping the dummy node
    }

    public static void main(String[] args) {
        int[] values = {1, 2, 3, 4, 5};
        ListNode head = createList(values);

        ListNode res = removeNthFromEnd(head, 2);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }

    }
}
