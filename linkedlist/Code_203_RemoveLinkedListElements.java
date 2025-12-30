package linkedlist;

/**
 * 203. Remove Linked List Elements
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
 * and return the new head.
 *
 * 解题思路:
 * 使用一个哑节点(dummy node)来简化边界情况的处理。遍历链表，检查每个节点的值是否等于给定值val。
 * 如果相等，则跳过该节点；否则，将其连接到结果链表中。最终返回哑节点的下一个节点作为新的头节点。
 * 时间复杂度: O(n)，其中n是链表的长度。
 * 空间复杂度: O(1)，只使用了常数级别的额外空间。
 */

public class Code_203_RemoveLinkedListElements {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode current = dummy;

        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return dummy.next;
    }
}
