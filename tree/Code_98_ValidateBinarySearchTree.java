package tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 98. Validate Binary Search Tree
 * 题目大意：
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 
 * 解题思路：
 * 递归和迭代两种方法实现验证二叉搜索树。
 * 递归方法通过设定节点值的上下界来验证每个节点是否满足二叉搜索树的性质。
 * 迭代方法通过中序遍历来验证节点值的顺序是否严格递增。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树的节点数。每个节点都被访问一次。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的空间复杂度为 O(h)，迭代方法中栈的空间复杂度也为 O(h)。
 */

public class Code_98_ValidateBinarySearchTree {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 递归
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }
        return validate(node.left, lower, val) && validate(node.right, val, upper);
    }

    // 迭代
    public boolean isValidBSTIterative(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        Integer prev = null;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (prev != null && current.val <= prev) {
                return false;
            }
            prev = current.val;
            current = current.right;
        }
        return true;
    }
        
}