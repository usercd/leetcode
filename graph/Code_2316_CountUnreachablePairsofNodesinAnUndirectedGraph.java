package graph;

import java.util.ArrayList;
import java.util.List;

public class Code_2316_CountUnreachablePairsofNodesinAnUndirectedGraph {

    class UnionFind {
            private int[] parent;
            private int[] size;
    
            public UnionFind(int n) {
                parent = new int[n];
                size = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
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
                    size[rootY] += size[rootX];
                }
            }
    
            public int getSize(int x) {
                return size[find(x)];
            }
        }

    // 并查集解法
    public long countPairs(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (uf.find(i) == i) {
                long size = uf.getSize(i);
                res += size * (n - size);
            }
        }
        return res / 2;
    }

    // DFS解法
    public long countPairsDFS(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        long res = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                long size = dfs(graph, i, visited);
                res += size * (n - size);
            }
        }
        return res / 2;
    }

    private long dfs(List<List<Integer>> graph, int node, boolean[] visited) {
        visited[node] = true;
        long size = 1;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                size += dfs(graph, neighbor, visited);
            }
        }
        return size;
    }
}