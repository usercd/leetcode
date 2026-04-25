package graph;

import java.util.ArrayList;
import java.util.List;

public class Code_1319_NumberofOperationstoMakeNetworkConnected {

    class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }

    // 并查集解法
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] connection : connections) {
            uf.union(connection[0], connection[1]);
        }
        return uf.getCount() - 1;
    }

    // DFS解法
    public int makeConnectedDFS(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] connection : connections) {
            graph.get(connection[0]).add(connection[1]);
            graph.get(connection[1]).add(connection[0]);
        }
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(graph, i, visited);
                count++;
            }
        }
        return count - 1;

    }
    private void dfs(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(graph, next, visited);
            }
        }
    }
}
