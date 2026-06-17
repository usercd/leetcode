package linkedlist;

public class Code_92_ReverseLinkedListII {

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1, head);
        
        // left 的前驱节点
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        
        // 反转区间的第一个节点
        ListNode curr = pre.next;
        
        // 局部反转
        ListNode temp = null;
        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = curr.next;
            curr.next = temp;
            temp = curr;
            curr = next;
        }
        // 反转后 curr 指向反转区间的下一个节点
        // temp指向反转区间右边界点
        pre.next.next = curr;
        pre.next = temp;        
        return dummyNode.next;
    }
}
