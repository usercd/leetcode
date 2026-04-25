package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Code_2492_MinimumScoreofaPathBetweenTwoCities {
    private int simpleAns;

    // 思路：题目等价于“城市1所在连通分量内的最小边权”。
    // 从城市1遍历整个连通分量，在遍历过程中维护最小边权即可。
    // 由于每条边、每个点最多访问一次，时间复杂度 O(n + m)。

    public int minScore(int n, int[][] roads) {
        // 构建图
        List<List<int[]>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int score = road[2];

            graph.get(u).add(new int[]{v, score});
            graph.get(v).add(new int[]{u, score});
        }

        boolean[] visited = new boolean[n + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visited[1] = true;

        int minScore = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int city = queue.poll();

            for (int[] edge : graph.get(city)) {
                int next = edge[0];
                int score = edge[1];

                minScore = Math.min(minScore, score);

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        return minScore;
    }

    // 路径可以折返，所以只要找到城市1所在连通分量内的最小边权即可。
    public int minScore1(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int x = road[0] - 1;
            int y = road[1] - 1;
            int d = road[2];
            graph.get(x).add(new int[]{y, d});
            graph.get(y).add(new int[]{x, d});
        }

        simpleAns = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n];
        dfsMinScore(0, graph, visited);
        return simpleAns;
    }

    private void dfsMinScore(int city, List<List<int[]>> graph, boolean[] visited) {
        visited[city] = true;
        for (int[] edge : graph.get(city)) {
            int next = edge[0];
            int score = edge[1];
            simpleAns = Math.min(simpleAns, score);
            if (!visited[next]) {
                dfsMinScore(next, graph, visited);
            }
        }
    }
}
