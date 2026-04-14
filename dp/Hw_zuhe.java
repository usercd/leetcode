package dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 2024年6月-100分-组合问题
 * 题目描述：游乐场通过进行游戏获取游戏币，游戏币可以用来兑换奖品，每个奖品价值不同的游戏币数量。
 * 可兑换的奖品列表通过数组 values 给出，其中 values[i] 表示兑换第 i 个奖品价值的游戏币数量，
 * 价值相同则记为同一奖品。给出获取的游戏币数量 num ，
 * 请计算刚好价值 num 个游戏币的奖品组合的个数，如果不存在价值 num 个游戏币的奖品组合，则返回 0 。
 * 输入描述
 *
 * 第一行:正整数 len ，表示奖品列表 values 长度，1<=len<=100;
 *
 * 第二行:正整数数组 values ，长度为 len ，values[i] 表示第i 个奖品价值的游戏币数量，1<=values[i]<=100 ;
 *
 * 第三行:正整数 num ，表示获取的游戏币数量，0<=num<=100.

 * 输出描述
 *
 * 整数，代表本次可以兑换的奖品组台数量
 *
 * 样例1
 * 输入
 * 3
 * 3 3 3
 * 4
 * 输出 0
 * 说明:无可以兑换的奖品组合，输出 0

 * 样例2
 * 输入
 * 8
 * 2 5 4 5 3 7 1 4
 * 8
 * 输出:5
 * 说明:可以兑换的奖品组合为
 * 可以兑换的奖品组合为: [
 * [2,5,1]
 * [5,3]
 * [4,4]
 * [4,3,1]
 * [7,1]
 * ]
 * 输出组合数量 5
 */
public class Hw_zuhe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int len = sc.nextInt();
        int[] values = new int[len];
        for (int i = 0; i < len; i++) {
            values[i] = sc.nextInt();
        }
        int num = sc.nextInt();
        
        // 统计每个价值的出现次数（可用次数）
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int v : values) {
            if (v > 0) {
                countMap.put(v, countMap.getOrDefault(v, 0) + 1);
            }
        }
        
        // dp[j] 表示价值刚好为 j 的组合个数
        long[] dp = new long[num + 1];
        dp[0] = 1;  // 什么都不选，价值 0，有 1 种方式
        
        // 对每种价值进行多重背包处理
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int v = entry.getKey();      // 奖品价值
            int cnt = entry.getValue();  // 该价值可使用的最大次数
            
            // 逆序遍历（0-1背包思想），避免重复使用同一实例
            for (int j = num; j >= v; j--) {
                long ways = 0;
                // k 表示使用该价值奖品的个数（0 到 cnt）
                for (int k = 1; k <= cnt && k * v <= j; k++) {
                    ways += dp[j - k * v];
                }
                dp[j] += ways;
            }
        }
        
        System.out.println(dp[num]);
        sc.close();
    }
    
}
