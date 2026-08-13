package tree;

/**
 * 129. Sum Root to Leaf Numbers
 * 题目大意：
 * 给定一个二叉树，返回从根节点到叶子节点的所有路径所表示的数字之和。
 * 例如，根节点到叶子节点路径 1->2 代表数字 12。
 * 
 * 解题思路：
 * 使用深度优先搜索（DFS）遍历二叉树，在遍历过程中构建当前路径表示的数字。当到达叶子节点时，将当前路径表示的数字加入总和。
 * 1. 定义一个辅助函数 dfs 来进行深度优先搜索
 * 2. 在 dfs 函数中，更新当前路径表示的数字
 * 3. 当到达叶子节点时，将当前路径表示的数字加入总和
 * 4. 最后返回总和
 * 
 * 时间复杂度：O(n)，其中 n 是二叉树中的节点数。每个节点被访问一次。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的空间取决于树的高度。
 */

public class Code_129_SumRootToLeafNumbers {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentSum) {
        if (node == null) return 0;
        currentSum = currentSum * 10 + node.val;
        if (node.left == null && node.right == null) return currentSum;
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}
