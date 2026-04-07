package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 51. N 皇后
 * 题目描述：
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，
 * 使得皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，
 * 该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 解题思路：
 * 使用回溯法逐行放置皇后，确保每次放置都不与已有皇后冲突
 * 1. 选择列表：当前行的所有列位置
 * 2. 路径：当前棋盘的放置状态
 * 3. 结束条件：当路径长度等于 n 时，表示所有皇后已成功放置，添加当前方案到结果中
 * 4. 回溯过程：遍历当前行的所有列位置，检查该位置是否与已有皇后冲突
 *    - 如果不冲突，放置皇后，递归处理下一行
 *    - 递归返回后，移除该皇后（回溯）
 * 时间复杂度：O(N!)，每行有 N 个选择，递归深度为 N
 * 空间复杂度：O(N^2)，用于存储棋盘状态
 */
public class Code_51_NQueues {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), n);
        return result;
    }

    private void backtrack(List<List<String>> result, List<String> current, int n) {
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(current, col)) {
                current.add(constructRow(col, n));
                backtrack(result, current, n);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isValid(List<String> current, int col) {
        int row = current.size();
        for (int i = 0; i < row; i++) {
            // 检查列冲突
            if (current.get(i).charAt(col) == 'Q') {
                return false;
            }
            // 检查左上到右下对角线 (\)
            int leftDiag = col - (row - i);
            if (leftDiag >= 0 && current.get(i).charAt(leftDiag) == 'Q') {
                return false;
            }
            // 检查右上到左下对角线 (/)
            int rightDiag = col + (row - i);
            if (rightDiag < current.get(i).length() && current.get(i).charAt(rightDiag) == 'Q') {
                return false;
            }
        }
        return true;
    }

    private String constructRow(int col, int n) {
        StringBuilder sb = new StringBuilder(".".repeat(n));
        sb.setCharAt(col, 'Q');
        return sb.toString();
    }

}
