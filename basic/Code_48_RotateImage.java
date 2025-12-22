package basic;

/** 
 * 旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 200
 * -1000 <= matrix[i][j] <= 1000
 * 
 * 解题思路：
 * 1. 按照位置交换，每次交换四个位置的元素
 * 2. 外层循环控制行，内层循环控制列
 * 3. 行循环到 n/2，列循环到 (n+1)/2
 * 4. 交换位置时，使用一个临时变量存储其中一个位置的值
 * 
 * 复杂度分析：
 * 时间复杂度：O(n^2)，需要遍历矩阵中的每个元素
 * 空间复杂度：O(1)，只使用了常数级别的额外空间
 */

public class Code_48_RotateImage {

    public void rotate1(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }    
    }

    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 先转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 再水平翻转矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
}
