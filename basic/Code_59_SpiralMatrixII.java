package basic;

/**
 * 题目大意：
 * 给你一个正整数 n ，生成一个包含 1 到 n² 所有元素，且元素按顺时针顺序排列的 n x n 正方形矩阵 matrix 。
 * 
 * 解题思路：
 * 模拟过程，定义四个边界，依次遍历四个边界，然后更新边界，直到填充完成。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n²)，需要填充整个矩阵。
 * 空间复杂度：O(1)，不需要额外的空间存储数据，返回结果不计入空间复杂度
 */
public class Code_59_SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        int num = 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    matrix[bottom][i] = num++;
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = num++;
                }
                left++;
            }
        }
        return matrix;
    }
}
