package dp;

import java.util.*;

/**
 * 2026年1月22日-100分-投资最大收益周期
 * 
 * 题目描述：
 * 团团过年收获了很多压岁钱，妈妈帮他开了账户去投资。现在给出 n 天内投资收益情况，选出划中连续多少天的收益总和量大，这个收益是多少。
 * 输入描述
 * 第一行是一个整数 n ，表示天数，n 的范围为 [0,1000][0,1000]
 * 第二行是 n 整数组成的一个数组，表示每天的收益，有正数也有负数，范围为 [−10000,100000]
 * 解题思路：
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class Hw_MaximumSubarray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        // 处理 n=0 的边界情况
        if (n == 0) {
            System.out.println(0);
            sc.close();
            return;
        }
        
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        
        // Kadane 算法求最大子数组和
        long maxSum = nums[0];     // 全局最大和
        long currentSum = nums[0]; // 当前连续子数组的和
        
        for (int i = 1; i < n; i++) {
            // 当前位置的最大连续和 = max(当前元素, 前一个连续和 + 当前元素)
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // 更新全局最大和
            maxSum = Math.max(maxSum, currentSum);
        }
        
        System.out.println(maxSum);
        sc.close();
    }
}
