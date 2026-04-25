package linkedlist;

/**
 * LeetCode 148. 排序链表
 * 
 * 题目描述：
 * 给定链表的头节点 head ，对链表进行排序，并返回排序后的链表。
 * 
 * 解题思路：
 * 使用归并排序算法对链表进行排序。首先使用快慢指针找到链表的中点，
 * 然后递归地对左右两部分进行排序，最后合并两个已排序的链表。
 * 
 * 时间复杂度：O(n log n)
 * 空间复杂度：O(log n)（递归栈空间）
 */

public class Code_148_SortList {

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Split the list into two halves
        ListNode mid = getMid(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null; // Split the list into two halves

        // Step 2: Recursively sort both halves
        left = sortList(left);
        right = sortList(right);

        // Step 3: Merge the sorted halves
        return merge(left, right);
    }

    private ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
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

        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        return dummy.next;
    }

    // -------------------------------------------------------------------------
    // 解法二：自底向上归并排序（迭代）
    //
    // 思路：
    // 递归写法的额外空间来自调用栈，深度为 O(log n)。
    // 为了消除递归，改用迭代的自底向上归并：
    //   1. 先统计链表总长度 length。
    //   2. 用 step 表示每轮参与归并的子链表长度，初始为 1，每轮翻倍。
    //   3. 每一轮从头到尾扫描链表，每次截取两段长度为 step 的子链表进行合并，
    //      将合并结果接回主链表，然后继续处理后续节点。
    //   4. 重复直到 step >= length，排序完成。
    //
    // 时间复杂度：O(n log n)
    // 空间复杂度：O(1)（只使用常数个指针，无递归栈）
    // -------------------------------------------------------------------------

    public ListNode sortListO1(ListNode head) {
        if (head == null || head.next == null) return head;

        // 第一步：统计链表总长度
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 第二步：step 从 1 开始，每轮翻倍，共 O(log n) 轮
        for (int step = 1; step < length; step <<= 1) {
            ListNode prev = dummy;
            ListNode cur  = dummy.next;

            while (cur != null) {
                ListNode left  = cur;
                ListNode right = splitO1(left, step);
                cur = splitO1(right, step);
                prev = mergeO1(left, right, prev);
            }
        }

        return dummy.next;
    }

    /**
     * 从 head 开始保留前 n 个节点，断开后续链表，返回断开后剩余部分的头节点。
     * 例如：链表 1->2->3->4->5，n=2，操作后原链表变为 1->2->null，返回 3。
     */
    private ListNode splitO1(ListNode head, int n) {
        // 向后走 n-1 步，让 head 停在第 n 个节点
        while (--n > 0 && head != null) {
            head = head.next;
        }
        // 保存后续部分的头节点
        ListNode rest = (head != null) ? head.next : null;
        // 断开连接
        if (head != null) head.next = null;
        return rest;
    }

    /**
     * 将有序链表 l1 与 l2 合并，合并结果接在 prev 之后。
     * 返回合并后链表的尾节点，供下一次拼接使用。
     */
    private ListNode mergeO1(ListNode l1, ListNode l2, ListNode prev) {
        ListNode cur = prev;
        // 逐一比较，将较小节点接到 cur 后面
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        // 将剩余部分直接接上
        cur.next = (l1 != null) ? l1 : l2;
        // 移动到本段尾节点，为下一段拼接做准备
        while (cur.next != null) cur = cur.next;
        return cur;
    }

}
