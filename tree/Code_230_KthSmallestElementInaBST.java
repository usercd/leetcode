package tree;

/**
 * 230. Kth Smallest Element in a BST
 * 题目大意：
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * 
 * 解题思路：
 * 利用二叉搜索树的性质，左子树的节点值都小于根节点值，右子树的节点值都大于根节点值。
 * 通过递归计算左子树的节点数，判断第 k 个最小元素是在左子树、右子树还是当前节点。
 * 
 * 复杂度分析：
 * 时间复杂度：O(h)，其中 h 是树的高度。在最坏情况下，我们可能需要遍历树的高度。
 * 空间复杂度：O(h)，递归调用栈的最大深度为 O(h)。
 */

public class Code_230_KthSmallestElementInaBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k - count - 1);
        } else {
            return root.val;
        }
    }

    private int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}
