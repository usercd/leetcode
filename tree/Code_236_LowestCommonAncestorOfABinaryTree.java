package tree;

/**
 * 题目大意：
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 解题思路：
 * 使用递归的方法遍历二叉树。
 * 对于每个节点，检查它的左子树和右子树是否包含目标节点 p 或 q。
 * 如果当前节点是 p 或 q，则返回当前节点。
 * 如果左子树和右子树都返回非空值，说明 p 和 q 分别在当前节点的两侧，当前节点即为最近公共祖先。
 * 否则，返回非空的子树结果。
 * <p>
 * 复杂度分析：
 * 时间复杂度：O(N)，其中 N 是二叉树中的节点数。每个节点最多被访问一次。
 * 空间复杂度：O(H)，其中 H 是二叉树的高度。递归调用栈的空间取决于树的高度。
 */

public class Code_236_LowestCommonAncestorOfABinaryTree {
    class TreeNode {
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
