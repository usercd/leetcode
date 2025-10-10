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