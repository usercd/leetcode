package linkedlist;

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