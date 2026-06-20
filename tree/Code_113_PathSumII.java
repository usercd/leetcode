package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. Path Sum II
 * 题目大意：
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum，
 * 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 * 
 * 解题思路：
 * 使用深度优先搜索（DFS）遍历二叉树。在遍历过程中，维护当前路径和当前路径上的节点值列表。
 * 当到达叶子节点时，检查当前路径和是否等于 targetSum。
 * 如果符合条件，将当前路径添加到结果列表中。
 * 使用回溯法在返回上层节点时移除当前节点值，确保路径列表正确。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树中的节点数。每个节点都被访问一次。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的最大深度为 O(h)，
 * 以及存储路径的列表在最坏情况下也需要 O(h) 的空间。
 */

public class Code_113_PathSumII {

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

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(TreeNode node, int remainingSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        currentPath.add(node.val);
        remainingSum -= node.val;

        if (node.left == null && node.right == null && remainingSum == 0) {
            result.add(new ArrayList<>(currentPath));
        } else {
            backtrack(node.left, remainingSum, currentPath, result);
            backtrack(node.right, remainingSum, currentPath, result);
        }

        currentPath.remove(currentPath.size() - 1);
    }
}
