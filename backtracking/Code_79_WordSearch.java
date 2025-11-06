package backtracking;


/**
 * LeetCode 79. 单词搜索
 * 题目描述：
 * 给定一个二维网格 board 和一个字符串单词 word ，
 * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，顺着相邻的单元格构建，
 * 相邻的单元格是指上下左右相邻的单元格。
 * 你可以假设所有单元格都只包含一个字母。
 * 同一个单元格内的字母不允许被重复使用。
 * 解题思路：
 * 使用回溯法在二维网格中搜索单词
 * 1. 选择列表：当前单元格的上下左右四个方向
 * 2. 路径：当前匹配的单词字符索引
 * 3. 结束条件：当路径长度等于单词长度时，表示找到了单词
 * 4. 回溯过程：对于每个单元格，尝试匹配当前字符，标记为已访问，递归搜索相邻单元格，最后回溯取消标记
 * 时间复杂度：O(N * 3^L)，N 是网格中的单元格数，L 是单词长度
 * 空间复杂度：O(L)，递归栈的最大深度为单词长度
 */
public class Code_79_WordSearch {

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtrack(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, String word, int index, int row, int col, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] || board[row][col] != word.charAt(index)) {
            return false;
        }

        visited[row][col] = true;

        boolean found = backtrack(board, word, index + 1, row + 1, col, visited) ||
                        backtrack(board, word, index + 1, row - 1, col, visited) ||
                        backtrack(board, word, index + 1, row, col + 1, visited) ||
                        backtrack(board, word, index + 1, row, col - 1, visited);

        visited[row][col] = false;
        return found;
    }
}