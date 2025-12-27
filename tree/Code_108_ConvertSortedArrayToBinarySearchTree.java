package tree;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 * 题目大意：
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 
 * 解题思路：
 * 递归构建高度平衡的二叉搜索树。选择数组的中间元素作为根节点，
 * 左侧子数组递归构建左子树，右侧子数组递归构建右子树。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n)，其中 n 是数组的长度。每个元素都被访问一次。
 * 空间复杂度：O(logn)，递归调用栈的最大深度为 O(logn)。
 */

public class Code_108_ConvertSortedArrayToBinarySearchTree {
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

    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBST(nums, left, mid - 1);
        node.right = buildBST(nums, mid + 1, right);
        return node;
    }
}
