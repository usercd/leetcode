package graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class Code_433_MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        
        int n = bank.length;
        boolean[] visited = new boolean[n];
        // Queue 存储当前的基因字符串，
        // 每次从队列中取出一个字符串，检查它是否与 end 相同，如果相同则返回当前的步数。
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        int step = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(end)) {
                    return step;
                }
                
                for (int j = 0; j < n; j++) {
                    if (!visited[j] && isValid(curr, bank[j])) {
                        visited[j] = true;
                        queue.offer(bank[j]);
                    }
                }
            }
            step++;
        }
        
        return -1;
    }

    // 判断两个字符串是否只有一个字符不同
    private boolean isValid(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
            if (diff > 1) {
                return false;
            }
        }
        return diff == 1;
    }
}