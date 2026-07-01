package two_pointers;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目大意：
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 
 * 解题思路：
 * 模拟过程，定义四个边界，依次遍历四个边界，然后更新边界，直到遍历完成。
 * 
 * 复杂度分析：
 * 时间复杂度：O(mn)，其中 m 和 n 分别为矩阵的行数和列数。需要遍历整个矩阵一次。
 * 空间复杂度：O(1)，不需要额外的空间存储数据，返回结果不计入空间复杂度
 */

public class Code_54_SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new ArrayList<Integer>();
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }
}
