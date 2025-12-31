package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 207. 课程表
 * 
 * 题目描述：
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses-1 。
 * 在选修某些课程之前需要一些先修课程。先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程 bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * 
 * 解题思路：
 * 使用 BFS 拓扑排序法检测有向图中是否存在环
 * 1. 构建邻接表表示图
 * 2. 计算每个节点的入度
 * 3. 使用队列进行拓扑排序，初始时将所有入度为 0 的节点加入队列
 * 4. 不断从队列中取出节点，减少其邻居节点的入度，如果邻居节点入度变为 0，则加入队列
 * 5. 最后检查访问的节点数量是否等于总课程数
 * 
 * 时间复杂度：O(V + E)，其中 V 是课程数，E 是先修课程对数
 * 空间复杂度：O(V + E)
 */

public class Code_207_CourseSchedule {
    // BFS 拓扑排序法
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构建邻接表表示图
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        // 计算每个节点的入度
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++;
        }

        // 使用队列进行拓扑排序
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int visitedCourses = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            visitedCourses++;

            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return visitedCourses == numCourses;
    }

    // DFS 拓扑排序法
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        // 构建邻接表表示图
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }
        // 0 = 未访问，1 = 访问中，2 = 已访问
        int[] visitStatus = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycleDFS(graph, visitStatus, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCycleDFS(List<List<Integer>> graph, int[] visitStatus, int course) {
        if (visitStatus[course] == 1) {
            return true;
        }
        if (visitStatus[course] == 2) {
            return false;
        }

        visitStatus[course] = 1;
        for (int neighbor : graph.get(course)) {
            if (hasCycleDFS(graph, visitStatus, neighbor)) {
                return true;
            }
        }
        visitStatus[course] = 2;
        return false;
    }
}