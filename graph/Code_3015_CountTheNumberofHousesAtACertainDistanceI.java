package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Code_3015_CountTheNumberofHousesAtACertainDistanceI {
public int[] countOfPairs(int n, int x, int y) {
        // 构建邻接表表示图的关系
        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 1; i < n; i++) {
            graph.get(i).add(i + 1);
            graph.get(i + 1).add(i);
        }
        
        // 额外边
        graph.get(x).add(y);
        graph.get(y).add(x);
        
        // 结果数组：result[k] = 最短距离为k的无序对数量
        int[] result = new int[n + 1];
        
        // 多源BFS：计算所有点对的最短距离
        for (int start = 1; start <= n; start++) {
            int[] dist = new int[n + 1];
            Arrays.fill(dist, -1);
            Queue<Integer> q = new ArrayDeque<>();
            
            dist[start] = 0;
            q.offer(start);
            
            while (!q.isEmpty()) {
                int curr = q.poll();
                
                for (int neighbor : graph.get(curr)) {
                    if (dist[neighbor] == -1) {
                        dist[neighbor] = dist[curr] + 1;
                        q.offer(neighbor);
                    }
                }
            }
            
            // 统计从start到所有更大编号的点的距离至少为K的所有数对（避免重复计数）
            for (int end = start + 1; end <= n; end++) {
                if (1 <= dist[end] && dist[end] <= n) {
                    result[dist[end]]++;
                }
            }
        }

        for (int i = 1; i < result.length; i++) {
            result[i] = result[i] * 2;
        }
        
        // 返回1-indexed结果（k从1到n）
        return Arrays.copyOfRange(result, 1, result.length);
    }
}
