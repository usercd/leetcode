package tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CD
 * @date 6/30/2026
 */
public class Code_662_MaximumWidthofBinaryTree {

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

    // 存储每层最左节点的原始编号
    Map<Integer, Integer> map = new HashMap<>();
    // 记录最大宽度
    int ans = 0;

    public int widthOfBinaryTree(TreeNode root) {
        // 从根节点开始，编号为1，深度为0
        dfs(root, 1, 0);
        return ans;
    }

    private void dfs(TreeNode root, int u, int depth) {
        if (root == null) return;
        // 每层只记录第一个访问到的节点（即最左节点）的原始编号
        if (!map.containsKey(depth)) map.put(depth, u);
        // 计算当前层的宽度：当前节点原始编号 - 最左节点原始编号 + 1
        ans = Math.max(ans, u - map.get(depth) + 1);
        // 重新编号：将当前节点映射为该层的相对位置
        u = u - map.get(depth) + 1;
        // 递归遍历子节点，传递重新编号后的值
        dfs(root.left, u << 1, depth + 1);
        dfs(root.right, u << 1 | 1, depth + 1);
    }

    public int widthOfBinaryTreeBFS(TreeNode root) {
        if (root == null) return 0;

        // BFS: 队列中同时存储节点和对应编号
        Deque<TreeNode> nodeQueue = new ArrayDeque<>();
        Deque<Long> indexQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        indexQueue.offer(1L);

        int maxWidth = 0;

        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();

            // 记录当前层最左和最右编号
            long leftmost = indexQueue.peekFirst();
            long rightmost = leftmost;

            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                long idx = indexQueue.poll();
                if (i == size - 1) {
                    rightmost = idx;
                }

                // 归一化后的编号，防止溢出
                long normalizedIdx = idx - leftmost;

                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    indexQueue.offer(normalizedIdx * 2);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    indexQueue.offer(normalizedIdx * 2 + 1);
                }
            }

            // 当前层宽度（归一化后计算）
            maxWidth = Math.max(maxWidth, (int) (rightmost - leftmost + 1));
        }

        return maxWidth;
    }
}
