package graph;

import java.util.ArrayList;
import java.util.List;

public class Code_1971_FindifPathExistsinGraph {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        // 构建邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        // 初始化访问数组
        boolean[] visited = new boolean[n];

        // 使用 DFS 判断是否存在路径
        return dfs(graph, visited, source, destination);
    }

    private boolean dfs(List<List<Integer>> graph, boolean[] visited, int node, int destination) {
        if (node == destination) {
            return true; // 找到目标节点，返回 true
        }
        visited[node] = true; // 标记当前节点为已访问

        // 遍历当前节点的所有邻居
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) { // 如果邻居未访问过
                if (dfs(graph, visited, neighbor, destination)) { // 递归访问邻居
                    return true; // 如果在递归中找到目标节点，返回 true
                }
            }
        }

        return false; // 没有找到目标节点，返回 false
    }
}
