package tree;

/**
 * 124. Binary Tree Maximum Path Sum
 * 题目大意：
 * 给定一个非空二叉树，返回其最大路径和。
 * 路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * 
 * 解题思路：
 * 使用递归遍历每个节点，计算以该节点为终点的最大路径和，同时更新全局最大路径和。
 * 对于每个节点，计算其左子树和右子树的最大贡献值（如果贡献值为负则取0），然后计算通过该节点的路径和，并更新全局最大值。
 * 最后返回该节点的最大贡献值给其父节点。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树的节点数。每个节点被访问一次。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的最大深度为 O(h)。
 */

public class Code_124_BinaryTreeMaximumPathSum {
    private int maxSum;

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int leftMax = Math.max(0, dfs(node.left));
        int rightMax = Math.max(0, dfs(node.right));
        maxSum = Math.max(maxSum, node.val + leftMax + rightMax);

        return node.val + Math.max(leftMax, rightMax);
    }
}
