package tree;

/**
 * 114. Flatten Binary Tree to Linked List
 * 题目大意：
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，
 * left 子指针始终为 null 。展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * 
 * 解题思路：
 * 使用递归的方式，先递归展开左子树和右子树，然后将左子树插入到根节点和右子树之间。
 * 最后将左子树的最右节点连接到右子树。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树的节点数。每个节点被访问一次。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的最大深度为 O(h)。
 */

public class Code_114_FlattenBinaryTreeToLinkedList {
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

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    // 迭代法
    public void flattenIterative(TreeNode root) {
        while (root != null) {
            // 当前节点左子树为空跳过
            if (root.left == null) {
                root = root.right;
            } else {
                // 找到左子树最右侧节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 将原来右子树接到左子树最右侧节点上
                pre.right = root.right;
                // 将左子树移动到右子树
                root.right = root.left;
                root.left = null;
                
                root = root.right;
            }
        }
    }
}