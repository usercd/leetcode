package tree;

import java.util.*;

/**
 * @author CD
 * @date 6/30/2026
 */
public class Code_145_BinaryTreePostorderTraversal {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 根右左 翻转 左右根
    public List<Integer> postorderTraversal0(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offer(root);

        while (!stack.isEmpty()) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = stack.poll();
                if (poll.left != null) stack.offer(poll.left);
                if (poll.right != null) stack.offer(poll.right);
                result.add(poll.val);
            }
        }

        Collections.reverse(result);
        return result;
    }


    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        TreeNode prev = null; // 记录上一个访问过的节点

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek(); // 注意：是peek不是pop！

            // 判断右子树是否需要处理
            if (cur.right != null && cur.right != prev) {
                // 右子树存在且未被访问过 → 转向右子树
                cur = cur.right;
            } else {
                // 右子树为空 或 已访问过 → 可以访问当前节点了
                stack.pop();
                result.add(cur.val);
                prev = cur; // 更新prev
                cur = null; // 防止重复向左压栈
            }
        }
        return result;
    }

    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;
        postOrder(root, result);
        return result;
    }

    private void postOrder(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.val);
    }
}
