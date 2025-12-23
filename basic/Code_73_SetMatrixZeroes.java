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


    // 只用一个变量 从（1, 1）开始遍历
    public void setZeroes_v2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean colZero = false;

        // Step 1: 第一列是否需要清零
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                colZero = true;
                break;
            }
        }

        // Step 2: 标记
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 3: 清零内部
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 4: 第一行
        if (matrix[0][0] == 0) {
            for (int j = 1; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 5: 第一列
        if (colZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    // 只使用一个变量 从 (0, 1) 开始遍历
    public void setZeroes_v3(int[][] matrix) {
        boolean col0_flag = false;
        int row = matrix.length;
        int col = matrix[0].length;

        // Step 1: 使用第一行和第一列作为标记
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) col0_flag = true;
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        // Step 2: 根据标记置零
        // 倒序遍历，保护第一行和第一列，在所有行都完成判断之前,不修改第一行和第一列
        // 如果修改了第一行和第一列，可能会影响后续行的判断
        // 要么延迟写入，要么倒序遍历
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0_flag) {
                matrix[i][0] = 0;
            }
        }
    }
}