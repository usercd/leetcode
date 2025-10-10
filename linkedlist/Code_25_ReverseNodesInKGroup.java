package linkedlist;

public class Code_25_ReverseNodesInKGroup {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
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
