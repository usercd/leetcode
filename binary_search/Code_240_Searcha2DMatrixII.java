package binary_search;

/**
 * LeetCode 240. Search a 2D Matrix II
 * 题目大意：
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。
 * 该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 解题思路：右上角和左下角是矩阵中同时具备一个递增方向和一个递减方向的位置，构成了天然的"二叉搜索树"结构。
 * 右上角 (0,n-1) 比 target 大 → 排除当前列；比 target 小 → 排除当前行
 * 左下角 (m-1,0) 比 target 小 → 排除当前行；比 target 大 → 排除当前列
 * 时间复杂度为 O(n + m)，其中 n 和 m 分别是二维数组的行数和列数。
 * 空间复杂度为 O(1)，只使用了常数级别的额外空间。
 */
public class Code_240_Searcha2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = cols - 1;
        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}
