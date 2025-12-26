package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * 题目大意：
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，inorder 是同一棵树的中序遍历，
 * 请构造二叉树并返回其根节点。
 * 
 * 解题思路：
 * 递归构建二叉树。先序遍历的第一个元素是根节点，在中序遍历中找到该根节点的位置，
 * 则其左侧为左子树的中序遍历，右侧为右子树的中序遍历。根据左子树和右子树的中序遍历长度，
 * 可以在先序遍历中划分出左子树和右子树的先序遍历。递归进行上述过程，直到数组为空。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树的节点数。每个节点都被访问一次。
 * 空间复杂度：O(n)，递归调用栈的最大深度为 O(n)。
 */
public class Code_105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static class TreeNode {
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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int k = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                k = i;
                break;
            }
        }
        root.left = build(preorder, preStart + 1, preStart + k - inStart, inorder, inStart, k - 1);
        root.right = build(preorder, preStart + k - inStart + 1, preEnd, inorder, k + 1, inEnd);
        return root;
    }

    // map优化
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) return null;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }
    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        
        int k = map.get(preorder[preStart]);
        root.left = build(preorder, preStart + 1, preStart + k - inStart, inorder, inStart, k - 1, map);
        root.right = build(preorder, preStart + k - inStart + 1, preEnd, inorder, k + 1, inEnd, map);
        return root;
    }
}
