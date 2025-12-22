package two_pointers;

/**
 * LeetCode 240. Search a 2D Matrix II
 * 题目大意：
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。
 * 该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 
 * 解题思路：
 * 利用二维数组的排序特性，从右上角开始查找。
 * 如果当前元素等于目标值，返回 true。
 * 如果当前元素大于目标值，说明目标值不可能在当前列，向左移动一列。
 * 如果当前元素小于目标值，说明目标值不可能在当前行，向下移动一行。
 * 重复上述过程直到找到目标值或超出数组边界。
 * 时间复杂度为 O(n + m)，其中 n 和 m 分别是二维数组的行数和列数。
 * 空间复杂度为 O(1)，只使用了常数级别的额外空间。
 * 
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
