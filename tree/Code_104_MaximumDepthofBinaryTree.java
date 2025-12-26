package tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 104. Maximum Depth of Binary Tree
 * 题目大意：
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 解题思路：
 * 方法一：递归，后序遍历。对于每个节点，计算其左子树和右子树的最大深度，取较大值加一即为该节点的最大深度。
 * 方法二：迭代，层序遍历。使用队列进行广度优先搜索（BFS），每遍历完一层，深度加一。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树的节点数。每个节点都被访问一次。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的最大深度为 h；对于迭代方法，队列中存储的节点数不会超过最大宽度。
 */

public class Code_104_MaximumDepthofBinaryTree {

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

    // 递归 后序遍历
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 迭代 层序遍历
    public int maxDepth1(TreeNode root) {
        int result = 0;
        if (root == null) return result;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.pollFirst();
                if (node.left != null) queue.offerLast(node.left);
                if (node.right != null) queue.offerLast(node.right);
            }
            result++;
        }
        return result;
     }
}
