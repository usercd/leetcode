package graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author cd
 * @date 2026.09.08
 * @description
 */
public class Code_934_ShortestBridge {

    private int[] dirs = {-1, 0, 1, 0, -1};
    private Deque<int[]> queue = new ArrayDeque<>();

    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        boolean found = false;
        for (int i = 0; i < n && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                if (grid[i][j] == 1) {
                    found = true;
                    dfs(grid,i, j);
                }
            }
        }
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] arr = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int x = arr[0] + dirs[j];
                    int y = arr[1] + dirs[j + 1];
                    if (x >= 0 && y >= 0 && x < n && y < n) {
                        if (grid[x][y] == 1) return result;
                        if (grid[x][y] == 0) {
                            grid[x][y] = 2;
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
            result++;
        }


        return -1;
    }

    private void dfs(int[][] grid, int i, int j) {
        int n = grid.length;
        if (i < 0 || j < 0 || i >= n || j >= n || grid[i][j] != 1) return;
        grid[i][j] = 2;
        queue.offer(new int[]{i, j});
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
