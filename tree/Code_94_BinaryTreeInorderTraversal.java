package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 94. Binary Tree Inorder Traversal
 * 题目大意：
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * 
 * 解题思路：
 * 递归和迭代两种方法实现中序遍历。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树的节点数。每个节点都被访问一次。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的空间复杂度为 O(h)，迭代方法中栈的空间复杂度也为 O(h)。
 */

public class Code_94_BinaryTreeInorderTraversal {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Recursive inorder traversal
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }

    // Iterative inorder traversal
    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            res.add(current.val);
            current = current.right;
        }
        return res;
    }
}