package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Code_3243_ShortestDistanceAfterRoadAdditionQueriesI {
    // BFS
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int len = queries.length;
        int[] result = new int[len];

        // dis[i] 表示从0到i的最短距离
        int[] dis = new int[n];
        for (int i = 0; i < n; i++) {
            dis[i] = i;
        }

        // 邻接表
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            if (i < n - 1) {
                graph.get(i).add(i + 1);
            }
        }

        for (int i = 0; i < len; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            graph.get(x).add(y);

            // 只有当新边可能产生更短路径时才更新
            if (dis[x] + 1 < dis[y]) {
                dis[y] = dis[x] + 1;
                // 从y开始BFS更新后续节点
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(y);

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    for (int next : graph.get(curr)) {
                        if (dis[curr] + 1 < dis[next]) {
                            dis[next] = dis[curr] + 1;
                            queue.offer(next);
                        }
                    }
                }
            }

            result[i] = dis[n - 1];
        }

        return result;
    }

    // DP

    public int[] shortestDistanceAfterQueries1(int n, int[][] queries) {
        List<List<Integer>> from = new ArrayList<>(n);
        // from[i] 存储所有查询中 r=i 的 l 值
        for (int i = 0; i < n; i++) {
            from.add(new ArrayList<>());
        }
        // costs[i] 表示从 0 到 i 的最短距离
        int[] costs = new int[n];
        for (int i = 1; i < n; i++) {
            costs[i] = i;
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            from.get(r).add(l);
            if (costs[l] + 1 < costs[r]) {
                costs[r] = costs[l] + 1;
                for (int j = r + 1; j < n; j++) {
                    costs[j] = Math.min(costs[j], costs[j - 1] + 1);
                    for (int k : from.get(j)) {
                        costs[j] = Math.min(costs[j], costs[k] + 1);
                    }
                }
            }
            ans[i] = costs[n - 1];
        }
        return ans;
    }
}
