package basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目大意：
 * 在一个二维网格中，每个单元格可以是以下三种状态之一：
 * 0 - 空单元格
 * 1 - 新鲜橘子
 * 2 - 腐烂橘子
 * 每分钟，任何与腐烂橘子（4 个方向上）相邻的新鲜橘子都会变成腐烂橘子。返回直到没有新鲜橘子为止所需的最小分钟数。如果不可能，返回 -1。
 * 
 * 解题思路：
 * 使用广度优先搜索（BFS）来模拟腐烂过程。首先将所有腐烂橘子的位置加入队列，然后每分钟处理队列中的所有腐烂橘子，感染相邻的新鲜橘子，并将它们加入队列。重复此过程直到没有新鲜橘子剩下或无法感染更多橘子。
 * 
 * 复杂度分析：
 * 时间复杂度：O(N*M)，其中 N 和 M 分别为网格的行数和列数。每个单元格最多被访问一次。
 * 空间复杂度：O(N*M)，用于存储队列中的腐烂橘子位置。
 */

public class Code_994_RottingOranges {

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int freshCount = 0;
        Queue<int[]> queue = new LinkedList<>();

        // 初始化队列，记录所有腐烂的橘子位置，并统计新鲜橘子数量
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) return 0; // 没有新鲜橘子，直接返回0

        int minutes = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // BFS过程
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean infectedThisMinute = false;

            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                for (int[] dir : directions) {
                    int newRow = pos[0] + dir[0];
                    int newCol = pos[1] + dir[1];

                    // 检查新位置是否在边界内且是新鲜橘子
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2; // 标记为腐烂
                        queue.offer(new int[]{newRow, newCol});
                        freshCount--;
                        infectedThisMinute = true;
                    }
                }
            }

            if (infectedThisMinute) {
                minutes++;
            }
        }

        return freshCount == 0 ? minutes : -1; // 如果还有新鲜橘子，返回-1
    }
    
}