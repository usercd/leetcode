package graph;

import java.util.Queue;
import java.util.LinkedList;

public class Code_547_NumberofProvinces {
    // DFS 方法
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                count++;
            }
        }

        return count;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        visited[i] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                dfs(isConnected, visited, j);
            }
        }
    }

    // BFS 方法
    public int findCircleNumBFS(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                queue.offer(i);
                visited[i] = true;
                count++;

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    for (int j = 0; j < isConnected.length; j++) {
                        if (isConnected[node][j] == 1 && !visited[j]) {
                            queue.offer(j);
                            visited[j] = true;
                        }
                    }
                }
            }
        }
        return count;
    }
}
