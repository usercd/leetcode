package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Template {
    
    // 成员变量用于存储图和访问状态，方便 dfs 方法访问
    private List<List<Integer>> graph;
    private boolean[] visited;

    public List<Integer> solve(int n, List<List<Integer>> edges) {
        // 初始化邻接表
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 构建无向图
        for (List<Integer> edge : edges) {
            int x = edge.get(0);
            int y = edge.get(1);
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        // 初始化访问数组
        visited = new boolean[n];

        // 存储每个连通块的大小
        List<Integer> ans = new ArrayList<>();

        // 遍历所有节点，寻找未访问的节点进行 DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int size = dfs(i);
                ans.add(size);
            }
        }

        return ans;
    }

    /**
     * 深度优先搜索，返回当前连通块的大小
     * @param node 当前节点
     * @return 连通块的大小
     */
    private int dfs(int node) {
        visited[node] = true; // 标记为已访问
        int size = 1; // 当前节点计入大小

        // 遍历当前节点的所有邻居
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                size += dfs(neighbor); // 递归累加子树大小
            }
        }

        return size;
    }

    // 计算从 start 到各个节点的最短路长度
    // 如果节点不可达，则最短路长度为 -1
    // 节点编号从 0 到 n-1，边权均为 1

    public int[] bfs(int n, int[][] edges, int start) {
        List<List<Integer>> g = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g.get(x).add(y);
            g.get(y).add(x); // 无向图
        }

        int[] dis = new int[n];
        Arrays.fill(dis, -1); // -1 表示尚未访问到
        Queue<Integer> q = new ArrayDeque<>();
        dis[start] = 0;
        q.offer(start);
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : g.get(x)) {
                if (dis[y] < 0) {
                    dis[y] = dis[x] + 1;
                    q.offer(y);
                }
            }
        }
        return dis;
    }
}