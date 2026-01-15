package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2322. 树中删除边的最小得分
 * 
 * 题目描述：
 * 给定一棵有 n 个节点的树，每个节点都有一个与之关联的整数值。你需要删除两条边，将树分成三个子树。
 * 每个子树的得分是该子树中所有节点值的异或和。你的任务是找到删除两条边后，三个子树得分的最大值与最小值之差的最小可能值。
 * 
 * 解题思路：
 * 1. 使用深度优先搜索（DFS）预处理每个节点的子树异或值以及进入和离开时间戳。
 * 2. 枚举所有可能的两条边组合，计算删除这两条边后形成的三个子树的得分。
 * 3. 根据三种情况（一个子树是另一个子树的后代、两个子树相互独立）计算三个子树的得分，并更新最小得分差。
 * 
 * 复杂度分析：
 * 时间复杂度：O(n^2)，其中 n 是树中的节点数。需要枚举所有边对进行计算。
 * 空间复杂度：O(n)，用于存储图的邻接表和辅助数组。
 */

public class Code_2322_MinimumScoreAfterRemovalsOnATree {
    private int[] xor;  // 每个节点的子树异或值
    private int[] in;   // DFS 进入时间戳
    private int[] out;  // DFS 离开时间戳
    private int clock;  // 时间戳计数器
    
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 预处理：计算每个子树的异或值和时间戳
        xor = new int[n];
        in = new int[n];
        out = new int[n];
        clock = 0;
        dfs(0, -1, graph, nums);
        
        int totalXor = xor[0];  // 整棵树的异或值
        int minScore = Integer.MAX_VALUE;
        
        // 枚举所有边对
        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                // 找到边的子节点（深度更大的节点）
                int x = findChild(edges[i][0], edges[i][1]);
                int y = findChild(edges[j][0], edges[j][1]);
                
                int part1, part2, part3;
                
                // 判断 x 和 y 的祖先关系
                if (isAncestor(x, y)) {
                    // y 是 x 的后代
                    part1 = xor[y];
                    part2 = xor[x] ^ xor[y];
                    part3 = totalXor ^ xor[x];
                } else if (isAncestor(y, x)) {
                    // x 是 y 的后代
                    part1 = xor[x];
                    part2 = xor[y] ^ xor[x];
                    part3 = totalXor ^ xor[y];
                } else {
                    // x 和 y 相互独立
                    part1 = xor[x];
                    part2 = xor[y];
                    part3 = totalXor ^ xor[x] ^ xor[y];
                }
                
                int currentScore = Math.max(part1, Math.max(part2, part3)) 
                                 - Math.min(part1, Math.min(part2, part3));
                minScore = Math.min(minScore, currentScore);
            }
        }
        return minScore;
    }

    private void dfs(int node, int parent, List<List<Integer>> graph, int[] nums) {
        in[node] = clock++;
        xor[node] = nums[node];
        
        for (int child : graph.get(node)) {
            if (child != parent) {
                dfs(child, node, graph, nums);
                xor[node] ^= xor[child];
            }
        }
        
        out[node] = clock++;
    }
    
    // 判断 x 是否是 y 的祖先（通过时间戳判断）
    private boolean isAncestor(int x, int y) {
        return in[x] < in[y] && out[x] > out[y];
    }
    
    // 找到边的子节点（深度更大的节点）
    private int findChild(int u, int v) {
        return in[u] < in[v] ? v : u;
    }
}
