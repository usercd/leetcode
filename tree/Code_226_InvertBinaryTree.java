package tree;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 226. Invert Binary Tree
 * 题目大意：
 * 给定一个二叉树，翻转它并返回翻转后的树。
 * 
 * 解题思路：
 * 使用递归的方法，对于每个节点，交换其左子树和右子树，然后递归地对左右子树进行同样的操作。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树的节点数。每个节点被访问一次。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的最大深度为 O(h)。
 */
public class Code_226_InvertBinaryTree {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // BFS 方法
    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return root;
    }
}
