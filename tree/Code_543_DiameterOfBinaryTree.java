package tree;

/**
 * LeetCode 543. 二叉树的直径
 * 
 * 题目描述：
 * 给定一棵二叉树，你需要计算它的直径长度。二叉树的直径长度是指任意两个节点路径长度中的最大值。
 * 这条路径可能穿过根节点，也可能不穿过根节点。
 * 
 * 解题思路：
 * 使用深度优先搜索（DFS）递归计算每个节点的深度，同时更新最大直径。
 * 1. 定义一个辅助函数 calculateDepth 来计算节点的深度
 * 2. 对于每个节点，计算其左子树和右子树的深度
 * 3. 更新最大直径为左子树深度加右子树深度的最大值
 * 4. 返回当前节点的深度，即 max(左子树深度, 右子树深度) + 1
 * 
 * 时间复杂度：O(n)，其中 n 是二叉树中的节点数。每个节点被访问一次。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的空间取决于树的高度。
 */

public class Code_543_DiameterOfBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        calculateDepth(root);
        return maxDiameter;
    }

    private int calculateDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = calculateDepth(node.left);
        int rightDepth = calculateDepth(node.right);

        // 更新最大直径
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        // 返回当前节点的深度
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
