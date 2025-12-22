package basic;

/**
 * 题目大意：
 * 给你一个 m 行 n 列的链表矩阵 matrix 和一个链表的头节点 head 。
 * 请你将链表中的值 按顺时针螺旋顺序 填充到矩阵中，未被填充的格子全部用 -1 替代。
 * 返回生成的矩阵。
 * 
 * 解题思路：
 * 模拟过程，定义四个边界，依次遍历四个边界，同时从链表中取值填充矩阵，然后更新边界，直到填充完成或者链表遍历完。
 * 
 * 复杂度分析：
 * 时间复杂度：O(mn)，其中 m 和 n 分别为矩阵的行数和列数。需要填充整个矩阵一次。
 * 空间复杂度：O(1)，不需要额外的空间存储数据，返回结果不计入空间复杂度
 */
public class Code_2326_SpiralMatrixIV {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {};
        ListNode(int x) { val = x; }
        ListNode(int x, ListNode next) { val = x; this.next = next; }
    }
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = -1;
            }
        }
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;
        ListNode current = head;
        while (top <= bottom && left <= right && current != null) {
            for (int i = left; i <= right && current != null; i++) {
                matrix[top][i] = current.val;
                current = current.next;
            }
            top++;
            for (int i = top; i <= bottom && current != null; i++) {
                matrix[i][right] = current.val;
                current = current.next;
            }
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left && current != null; i--) {
                    matrix[bottom][i] = current.val;
                    current = current.next;
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top && current != null; i--) {
                    matrix[i][left] = current.val;
                    current = current.next;
                }
                left++;
            }
        }
        return matrix;
    }
}
