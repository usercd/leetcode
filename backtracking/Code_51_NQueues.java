package backtracking;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        Code_51_NQueues solver = new Code_51_NQueues();
        List<List<String>> result = solver.solveNQueens(4);
        System.out.println(result);
        System.out.println("Total solutions: " + result.size());
    }
}
