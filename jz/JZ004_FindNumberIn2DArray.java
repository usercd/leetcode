package jz;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 * 
 * 在一个 n m 的二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 要求时间复杂度为 O(n + m)，空间复杂度为 O(1)。
 * 
 * 解题思路：
 * 利用二维数组的排序特性，从右上角开始查找。
 * 如果当前元素等于目标值，返回 true。
 * 如果当前元素大于目标值，说明目标值不可能在当前列，向左移动一列。
 * 如果当前元素小于目标值，说明目标值不可能在当前行，向下移动一行。
 * 重复上述过程直到找到目标值或超出数组边界。
 * 时间复杂度为 O(n + m)，其中 n 和 m 分别是二维数组的行数和列数。
 * 空间复杂度为 O(1)，只使用了常数级别的额外空间。
 */
public class JZ004_FindNumberIn2DArray {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
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