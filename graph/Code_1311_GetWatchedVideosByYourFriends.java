package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;

public class Code_1311_GetWatchedVideosByYourFriends {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] used = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(id);
        used[id] = true;
        
        // BFS 找到 level 层的朋友
        for (int step = 1; step <= level; step++) {
            int span = q.size();
            for (int i = 0; i < span; i++) {
                int u = q.poll();
                for (int v : friends[u]) {
                    if (!used[v]) {
                        q.offer(v);
                        used[v] = true;
                    }
                }
            }
        }
        
        // 统计 level 层朋友观看的视频频次
        Map<String, Integer> freq = new HashMap<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            for (String watched : watchedVideos.get(u)) {
                freq.put(watched, freq.getOrDefault(watched, 0) + 1);
            }
        }
        
        // 根据频次和字典序排序
        List<String> ans = new ArrayList<>(freq.keySet());
        ans.sort((a, b) -> {
            int freqCompare = Integer.compare(freq.get(a), freq.get(b));
            if (freqCompare != 0) {
                return freqCompare;
            }
            return a.compareTo(b);
        });
        
        return ans;
    }
}
