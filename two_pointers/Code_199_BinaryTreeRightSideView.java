package two_pointers;

import java.util.List;
import java.util.ArrayList;

/**
 * 199. Binary Tree Right Side View
 * 题目大意：
 * 给定一个二叉树的根节点 root，想象自己站在它的右侧，返回从顶部到底部所能看到的节点值。
 * 
 * 解题思路：
 * 使用深度优先搜索（DFS）遍历二叉树，优先访问右子节点。维护当前深度和结果列表，如果当前深度等于结果列表的大小，说明是该深度的第一个节点，将其值添加到结果列表中。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树的节点数。需要遍历所有节点。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归栈的空间复杂度取决于树的高度。
 */

public class Code_199_BinaryTreeRightSideView {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode node, int depth, List<Integer> res) {
        if (node == null) {
            return;
        }
        // 如果当前深度等于结果列表的大小，说明是该深度的第一个节点
        if (depth == res.size()) {
            res.add(node.val);
        }
        // 先遍历右子树，再遍历左子树
        dfs(node.right, depth + 1, res);
        dfs(node.left, depth + 1, res);
    }
}
