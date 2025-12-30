package basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. Number of Islands
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * 解题思路:
 * 使用深度优先搜索(DFS)遍历每个岛屿。当遇到'1'时，启动DFS将与之相连的所有'1'标记为'0'，表示已访问过。
 * 每次启动DFS时，岛屿数量加1。最终返回岛屿数量。
 * 时间复杂度: O(m*n)，其中m和n分别是网格的行数和列数。
 * 空间复杂度: O(m*n)用于递归栈空间，在最坏情况下，整个网格都是陆地。
 */

public class Code_200_NumberOfIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }
        return numIslands;
    }

    private void dfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (i < 0 || j < 0 || i >= rows || j >= cols || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0'; // Mark as visited

        dfs(grid, i + 1, j); // Down
        dfs(grid, i - 1, j); // Up
        dfs(grid, i, j + 1); // Right
        dfs(grid, i, j - 1); // Left
    }

    // 解法2: BFS
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int numIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    grid[i][j] = '0'; // Mark as visited
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});

                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        for (int[] dir : directions) {
                            int newRow = cell[0] + dir[0];
                            int newCol = cell[1] + dir[1];
                            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == '1') {
                                grid[newRow][newCol] = '0'; // Mark as visited
                                queue.offer(new int[]{newRow, newCol});
                            }
                        }
                    }
                }
            }
        }
        return numIslands;
    }
}