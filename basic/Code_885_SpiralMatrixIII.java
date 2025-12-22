package basic;

/**
 * 题目大意：
 * 在 R 行 C 列的矩阵中，我们从 (r0, c0) 面朝东面开始进行螺旋行走。
 * 这里的行和列的下标从 0 开始，到 R-1 和 C-1 结束。
 * 我们按顺时针方向行走，访问矩阵中的每个位置。
 * 如果我们在行走过程中到达了矩阵的边界外，我们继续按照螺旋的路径行走，直到我们再次到达矩阵的边界内。
 * 最终，我们需要返回一个表示矩阵中所有位置的坐标列表，按我们访问它们的顺序排列。
 * 
 * 解题思路：
 * 模拟螺旋过程，定义四个方向，依次遍历四个方向，然后更新步长，直到遍历完成。
 * 
 * 复杂度分析：
 * 时间复杂度：O(RC)，其中 R 和 C 分别为矩阵的行数和列数。需要遍历整个矩阵一次。
 * 空间复杂度：O(RC)，用于存储结果数组。
 */
public class Code_885_SpiralMatrixIII {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] result = new int[rows * cols][2];
        int index = 0;
        result[index][0] = rStart;
        result[index++][1] = cStart;
        int step = 1;
        int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // right, down, left, up

        while (index < rows * cols) {
            for (int d = 0; d < 4 && index < rows * cols; d++) {
                int moves = step;
                for (int s = 0; s < moves; s++) {
                    rStart += dirs[d][0];
                    cStart += dirs[d][1];
                    if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                        result[index][0] = rStart;
                        result[index++][1] = cStart;
                        if (index >= rows * cols)
                            break;
                    }
                }
                if (d % 2 == 1)
                    step++;
            }
        }
        return result;
    }
}
