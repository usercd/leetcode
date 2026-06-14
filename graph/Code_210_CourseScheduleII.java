package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code_210_CourseScheduleII {
    /**
     * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。
     * 给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，
     * 表示在选修课程 ai 前 必须 先选修 bi 。
     * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
     * 返回你为了学完所有课程所安排的学习顺序。
     * 可能会有多个正确的顺序，你只要返回 任意一种 就可以了。
     * 如果不可能完成所有课程，返回 一个空数组 。
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 构建邻接表和入度数组
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 初始化图和入度
        for (int[] info : prerequisites) {
            int course = info[0];
            int pre = info[1];
            graph.get(pre).add(course);
            inDegree[course]++;
        }
        
        // 将所有入度为 0 的节点加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] result = new int[numCourses];
        int count = 0;
        
        // BFS 拓扑排序
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result[count++] = curr;
            
            for (int nextCourse : graph.get(curr)) {
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        
        // 如果能够学完所有课程（无环），返回结果，否则返回空数组
        return count == numCourses ? result : new int[0];
    }
}
