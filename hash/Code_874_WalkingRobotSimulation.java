package hash;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 874. 模拟行走机器人
 * 
 * 题目描述：
 * 机器人在一个无限大小的网格上行走，初始位置为 (0, 0)，面朝北方。机器人可以接收一系列命令：
 * -2：向左转 90 度
 * -1：向右转 90 度
 * 1 <= x <= 9：向前移动 x 个单位
 * 同时，网格上有一些障碍物，机器人无法穿过这些障碍物。
 * 给定命令和障碍物的位置，返回机器人从原点到达的最大欧几里得距离的平方。
 * 
 * 解题思路：
 * 使用哈希集合存储障碍物的位置，方便 O(1) 时间内查找。模拟机器人的行走过程，根据命令更新机器人的位置和方向。
 * 1. 初始化机器人的位置和方向。
 * 2. 遍历命令，根据命令更新方向或移动位置。
 * 3. 在移动过程中检查是否遇到障碍物，如果遇到则停止移动。
 * 4. 更新最大距离的平方。
 * 
 * 时间复杂度：O(n + m)，其中 n 是命令的数量，m 是障碍物的数量。
 * 空间复杂度：O(m)，用于存储障碍物的位置。
 */

public class Code_874_WalkingRobotSimulation {

    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 上右下左（顺时针）

    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0, y = 0, direction = 0;
        int maxDistance = 0;
        Set<String> obstacleSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obstacleSet.add(obstacle[0] + "," + obstacle[1]);
        }
        for (int command : commands) {
            if (command == -2) { // turn left
                direction = (direction + 3) % 4;
            } else if (command == -1) { // turn right
                direction = (direction + 1) % 4;
            } else { // move forward
                for (int i = 0; i < command; i++) {
                    int nextX = x, nextY = y;
                    if (direction == 0) nextY++; // north
                    else if (direction == 1) nextX++; // east
                    else if (direction == 2) nextY--; // south
                    else nextX--; // west

                    if (!obstacleSet.contains(nextX + "," + nextY)) {
                        x = nextX;
                        y = nextY;
                        maxDistance = Math.max(maxDistance, x * x + y * y);
                    } else {
                        break; // hit an obstacle, stop moving in this direction
                    }
                }
            }
        }
        return maxDistance;
    }

    public int robotSim1(int[] commands, int[][] obstacles) {
        // 用Set存储所有障碍点
        Set<Integer> obstacleSet = new HashSet<>(obstacles.length, 1); // 预分配空间
        final int OFFSET = (int) 3e4;
        for (int[] p : obstacles) {
            // 位运算压缩坐标，用一个int代表一个点
            // p 是两个 16 位整数，合并成一个 32 位整数
            // 加上offset保证p[0]p[1]均为非负数
            obstacleSet.add((p[0] + OFFSET) << 16 | (p[1] + OFFSET));
        }

        int x = 0, y = 0, k = 0, ans = 0;
        for (int c : commands) {
            if (c == -1) { // 右转
                k = (k + 1) % 4;
            } else if (c == -2) { // 左转
                k = (k + 3) % 4;
            } else { // 直行
                while (c-- > 0) {
                    int nx = x + DIRS[k][0];
                    int ny = y + DIRS[k][1];
                    if (obstacleSet.contains((nx + OFFSET) << 16 | (ny + OFFSET))) {
                        break;
                    }
                    x = nx;
                    y = ny;
                }
                ans = Math.max(ans, x * x + y * y);
            }
        }
        return ans;
    }
}
