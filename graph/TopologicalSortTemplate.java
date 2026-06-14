package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortTemplate {
    // 返回有向无环图（DAG）的其中一个拓扑序
    // 如果图中有环，返回空列表
    // 节点编号从 0 到 n-1

    public List<Integer> topologicalSort(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // 统计每个节点的入度
        int[] inDeg = new int[n];
        for (int[] e : edges) {
            int x = e[0];
            int y = e[1];
            // 有向图
            graph.get(x).add(y);
            inDeg[y]++; // 统计 y 的先修课数量
        }
        // 从入度为 0 的节点开始进行拓扑排序
        // 每次从队列中取出一个节点，加入拓扑序
        // 然后将它的后继节点的入度减一
        // 如果后继节点的入度变为 0，则加入队列
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) { // 没有先修课，可以直接上
                q.offer(i); // 加入学习队列
            }
        }
        List<Integer> topoOrder = new ArrayList<>();
        while (!q.isEmpty()) {
            int x = q.poll();
            topoOrder.add(x);
            for (int y : graph.get(x)) {
                inDeg[y]--; // 修完 x 后，y 的先修课数量减一
                if (inDeg[y] == 0) { // y 的先修课全部上完
                    q.offer(y); // 加入学习队列
                }
            }
        }
        if (topoOrder.size() < n) { // 图中有环
            return List.of();
        }
        return topoOrder;
    }
}