package tree;

/**
 * 112. Path Sum
 * 题目大意：
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum，
 * 判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。
 * 叶子节点 是指没有子节点的节点。
 * 
 * 解题思路：
 * 使用深度优先搜索（DFS）遍历二叉树。在遍历过程中，维护当前路径的和。
 * 当到达叶子节点时，检查当前路径和是否等于 targetSum。
 * 如果找到符合条件的路径，返回 true；否则继续搜索。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树中的节点数。每个节点都被访问一次。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的最大深度为 O(h)。
 */

public class Code_112_PathSum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        // If it's a leaf node, check if the remaining targetSum equals the node's value
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        // Recursively check the left and right subtrees with the updated targetSum
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
