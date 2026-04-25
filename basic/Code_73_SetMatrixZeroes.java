package basic;

/**
 * 73. Set Matrix Zeroes
 * 题目大意：
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 * 
 * 解题思路：
 * 使用第一行和第一列作为标记，记录对应的行和列是否需要置零。
 * 另外使用一个变量记录第一列是否需要置零，避免与第一行的标记冲突。
 * 最后从矩阵的末尾开始遍历，根据标记将对应的行和列置零。
 * 时间复杂度：O(m*n)
 * 空间复杂度：O(1)
 */

public class Code_73_SetMatrixZeroes {
    // 使用两个变量 从 (1, 1) 开始遍历
    public void setZeroes_v1(int[][] matrix) {
        boolean row0_flag = false;
        boolean col0_flag = false;
        int row = matrix.length;
        int col = matrix[0].length;

        // 记录第一行和第一列是否需要置零
        for (int j = 0; j < col; j++)
            if (matrix[0][j] == 0) row0_flag = true;

        for (int i = 0; i < row; i++)
            if (matrix[i][0] == 0) col0_flag = true;

        // 使用第一行和第一列作为标记
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 根据标记置零
        for (int i = row - 1; i >= 1; i--) {
            for (int j = col - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (col0_flag) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }

        if (row0_flag) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}