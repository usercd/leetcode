package graph;

import java.util.ArrayList;
import java.util.List;

public class Code_1557_MinimumNumberofVerticesToReachAllNodes {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // 统计每个节点的入度
        int[] inDeg = new int[n];
        for (List<Integer> e : edges) {
            int y = e.get(1);
            inDeg[y]++;
        }
        // 入度为 0 的节点就是最小的顶点覆盖
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
