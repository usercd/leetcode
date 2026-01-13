package binary_search;

/**
 * LeetCode 74. 搜索二维矩阵
 * 
 * 题目描述：
 * 编写一个高效的算法来判断 m x n 矩阵中是否存在一个目标值。该矩阵具有以下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 
 * 解题思路：
 * 方法一：将二维矩阵视为一维有序数组，使用二分查找的方法进行搜索。
 * 方法二：逐行检查目标值是否在该行范围内，如果在则对该行进行二分查找。
 * 
 * 时间复杂度：O(log(mn)) 或 O(m log n)
 * 空间复杂度：O(1)
 */

public class Code_74_Searcha2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / cols][mid % cols];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public boolean searchMatrix1(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++) {
            if (matrix[i][0] <= target && matrix[i][col - 1] >= target) {
                int left = 0;
                int right = col - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (matrix[i][mid] == target) {
                        return true;
                    } else if (matrix[i][mid] > target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        Code_74_Searcha2DMatrix solver = new Code_74_Searcha2DMatrix();
        int[][] matrix = {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 60 }
        };
        int target = 3;
        System.out.println(solver.searchMatrix(matrix, target)); // Output: true

        target = 13;
        System.out.println(solver.searchMatrix(matrix, target)); // Output: false
    }
}
