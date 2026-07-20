package tree;

import java.util.HashMap;
import java.util.Map;

public class Code_105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
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

    // 时间复杂度：O(n) 空间复杂度：O(n)
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
        // key 为节点的值，value 为对应的中序索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            // 放入节点值和对应中序索引
            map.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }
    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        // map优化体现 不用遍历得到中序的索引
        int k = map.get(preorder[preStart]);
        // 左子树前序终点：preStart + k - 1 - inStart + 1
        root.left = build(preorder, preStart + 1, preStart + k - inStart, inorder, inStart, k - 1, map);
        // 右子树前序起点，就是前序终点+1
        root.right = build(preorder, preStart + k - inStart + 1, preEnd, inorder, k + 1, inEnd, map);
        return root;
    }
}
