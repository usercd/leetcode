package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. Path Sum III
 * 题目大意：
 * 给定一个二叉树的根节点 root 和一个整数 targetSum ，
 * 找出该二叉树中和为目标数 targetSum 的路径总数。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，
 * 但路径方向必须是向下的（只能从父节点到子节点）。
 * 
 * 解题思路：
 * 对于每个节点，计算以该节点为起点的所有路径中和为 targetSum 的路径数量。
 * 使用递归方法 countPathsFromNode 来计算从当前节点开始的路径数量。
 * 主函数 pathSum 遍历每个节点，并累加所有节点作为起点的路径数量。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n^2) 在最坏情况下（例如链状树），对于每个节点，我们可能需要遍历整个树来计算路径数量。
 * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用栈的最大深度为 O(h)。
 */

public class Code_437_PathSumIII {
    static class TreeNode {
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

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // Count paths with sum equal to targetSum starting from the current node
        int pathsFromRoot = countPathsFromNode(root, (long) targetSum);
        // Recursively count paths in the left and right subtrees
        int pathsFromLeft = pathSum(root.left, targetSum);
        int pathsFromRight = pathSum(root.right, targetSum);
        return pathsFromRoot + pathsFromLeft + pathsFromRight;
    }

    private int countPathsFromNode(TreeNode node, long targetSum) {
        if (node == null) {
            return 0;
        }
        int totalPaths = 0;
        // Check if the current node completes a path with the target sum
        if (node.val == targetSum) {
            totalPaths++;
        }
        // Continue to search in the left and right subtrees with the updated target sum
        totalPaths += countPathsFromNode(node.left, targetSum - node.val);
        totalPaths += countPathsFromNode(node.right, targetSum - node.val);
        return totalPaths;
    }

    // prefix sum + hashmap
    public int pathSum2(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1); // Base case: one way to have a prefix sum of 0
        return dfs(root, 0L, targetSum, prefixSumCount);
    }

    private int dfs(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSumCount) {
        if (node == null) {
            return 0;
        }
        currentSum += node.val;
        // Number of paths ending at the current node that sum to targetSum
        int numPathsToCurr = prefixSumCount.getOrDefault(currentSum - targetSum, 0);
        // Update the prefix sum count for the current sum
        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        // Recurse to left and right children
        int totalPaths = (int) (numPathsToCurr
                + dfs(node.left, currentSum, targetSum, prefixSumCount)
                + dfs(node.right, currentSum, targetSum, prefixSumCount));
        // Backtrack: remove the current sum from the map to avoid affecting other paths
        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);
        return totalPaths;
    }


    public int pathSum3(TreeNode root, int targetSum) {
        // 哈希表记录前缀和及其出现次数
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        // 初始化：前缀和为 0 出现一次（方便处理从根开始的路径）
        prefixSumCount.put(0L, 1);
        
        return dfs(root, 0L, targetSum, prefixSumCount);
    }

    private int dfs3(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSumCount) {
        if (node == null) return 0;
        currentSum += node.val;

        // 检查是否存在前缀和 s，使得 currentSum - s == targetSum
        // 即：从某个祖先到当前节点的路径和为 targetSum
        int count = prefixSumCount.getOrDefault(currentSum - targetSum, 0);

        // 将当前前缀和记录下来，供子树使用
        prefixSumCount.merge(currentSum, 1, Integer::sum);

        // 递归处理左右子树，并累加路径数量
        count += dfs(node.left, currentSum, targetSum, prefixSumCount);
        count += dfs(node.right, currentSum, targetSum, prefixSumCount);

        // 回溯：从哈希表中移除当前路径和的影响
        prefixSumCount.merge(currentSum, -1, Integer::sum);
        if (prefixSumCount.get(currentSum) == 0) {
            prefixSumCount.remove(currentSum); // 清理为0的项，防止干扰
        }

        return count;
    }
}
