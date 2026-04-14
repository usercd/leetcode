package dp;

import java.util.Arrays;
import java.util.Scanner;

/****
 * 2024年6月-100分-无线网络覆盖问题
 * 题目内容：
 * 你正在设计一个大型无线网络覆盖计划，目标是通过布置多个无线接入点来覆盖一个区域。每个接入点都有不同的信号覆盖范围，安装成本。在预算有限的情况下，保证网络覆盖需求满足，并且总成本不超过预算。

约束条件：
信号覆盖范围：每个接入点能够覆盖一定的区域，区域面积单位为 m2m2 。
安装成本：每个接入点有一定的安装成本，单位为元，总成本不应超过预算。
区域需求：你知道整个区域的总覆盖面积需求，单位为 m2m2。
输入描述
1、第一行包含三个数字
areaRequirementareaRequirement：所需的区域覆盖面积(单位：m20<areaRequirement<=100000m20<areaRequirement<=100000)。
budgetbudget：总预算（单位：元 0<budget<=100000<budget<=10000 budgetbudget 为 1010 的整数倍）。
nn ：接入点的数量 (0<n<=10000)(0<n<=10000) 。
2、接下来的 nn 行每行包含两个数字，分别是：
coveragecoverage ：接入点的信号覆盖范围（单位：m20<coverage<=100000m20<coverage<=100000）。
costcost ：接入点的安装成本(单位：元 0<cost=1000000<cost=100000 costcost 为 1010 的整数倍)。
输出描述
1、输出在给定成本内能满足区域覆盖需求的最小预算以及此时的覆盖面积，如果有多个解预算都能满足覆盖范围要求，输出预算最小时最大的覆盖面积
2、如果给出的站点无法满足要求则输出 00 00
样例1
输入
2000 500 3
1000 200
1500 250
800 180
输出
430 2300
说明
1、第一行表示：目标是需要一个信号覆盖范围至少为 20002000 m2m2的区域，总预算为 500500 元，共有 33 个接入点可供选择
2、接下来 33 行表示：
接入点 11：覆盖范围 10001000 m2m2，成本 200200 元
接入点 22：覆盖范围 15001500 m2m2，成本 250250 元
接入点 33：覆盖范围 800800 m2m2，成本 180180 元
可选接入点 22 和接入点 33 成本最低 430430 元能满足覆盖范围 23002300m2m2
样例2
输入
3000 500 3
1000 200
1500 250
800 180
输出
0 0
说明
1、第一行表示：目标是需要一个信号覆盖范围至少为 30003000 m2m2的区域，总预算为 500500 元，共有 33 个接入点可供选择
2、接下来 33 行表示：
接入点 11：覆盖范围 10001000 m2m2，成本 200200 元
接入点 22：覆盖范围 15001500 m2m2，成本 250250 元
接入点 33：覆盖范围 800800 m2m2，成本 180180 元
无论选择哪些接入点，都无法满足 在不超过预算 500500 的情况下满足 30003000 m2m2的区域覆盖需求
所以输出 00 00
 */

public class Hw_wuxianwangluo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        long areaRequirement = sc.nextLong();  // 所需覆盖面积
        int budget = sc.nextInt();             // 总预算
        int n = sc.nextInt();                  // 接入点数量
        
        long[] coverage = new long[n];
        int[] cost = new int[n];
        
        for (int i = 0; i < n; i++) {
            coverage[i] = sc.nextLong();
            cost[i] = sc.nextInt();
        }
        
        // dp[c] = 使用恰好成本 c 时，能获得的最大覆盖面积
        long[] dp = new long[budget + 1];
        // 初始化：成本 0 时覆盖面积 0
        Arrays.fill(dp, 0);
        
        // 0-1 背包：逆序更新
        for (int i = 0; i < n; i++) {
            for (int c = budget; c >= cost[i]; c--) {
                dp[c] = Math.max(dp[c], dp[c - cost[i]] + coverage[i]);
            }
        }
        
        // 查找最小成本 + 该成本下的最大覆盖面积
        long minCost = -1;
        long maxArea = 0;
        
        for (int c = 0; c <= budget; c++) {
            if (dp[c] >= areaRequirement) {
                if (minCost == -1 || c < minCost) {
                    minCost = c;
                    maxArea = dp[c];
                } else if (c == minCost) {
                    maxArea = Math.max(maxArea, dp[c]);
                }
            }
        }
        
        if (minCost == -1) {
            System.out.println("0 0");
        } else {
            System.out.println(minCost + " " + maxArea);
        }
        
        sc.close();
    }
}
