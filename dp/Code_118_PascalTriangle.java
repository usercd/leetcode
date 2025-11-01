package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 118. 杨辉三角
 * 
 * 题目描述：
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * 
 * 解题思路：
 * 使用动态规划思想逐行构建杨辉三角
 * 1. 状态定义：triangle[i][j] 表示第 i 行第 j 个位置的值
 * 2. 状态转移：
 *    - 每行的第一个和最后一个元素都是 1
 *    - 其他位置：triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j]
 * 3. 初始状态：第 0 行只有一个元素 1
 * 4. 逐行构建，每一行都依赖于上一行的结果
 * 
 * 时间复杂度：O(numRows^2)
 * 空间复杂度：O(1) 不考虑输出空间
 */
public class Code_118_PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        
        // 逐行构建杨辉三角
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            
            // 构建第 i 行，该行有 i+1 个元素
            for (int j = 0; j <= i; j++) {
                // 每行的第一个和最后一个元素都是 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // 中间的元素等于上一行两个相邻元素之和
                    // triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j]
                    row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
                }
            }
            
            triangle.add(row);
        }
        
        return triangle;
    }
}
