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

    private Map<Integer, Integer> map;
    private int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) return null;
        this.preorder = preorder;
        this.map = new HashMap<>();

        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return build(0, 0, n - 1);
    }

    private TreeNode build(int root, int left, int right) {
        if (left > right) return null;
        int val = preorder[root];
        TreeNode node = new TreeNode(val);
        int k = map.get(val);
        node.left = build(root + 1, left, k - 1);
        node.right = build(root + k - left + 1, k + 1, right);
        return node;
    }
}
