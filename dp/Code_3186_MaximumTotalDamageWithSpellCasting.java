package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3186. Maximum Total Damage With Spell Casting
 * 
 * 题目描述：一个魔法师有许多不同的咒语。给你一个数组 power ，其中每个元素表示一个咒语的伤害值，可能会有多个咒语有相同的伤害值。
 * 已知魔法师使用伤害值为 power[i] 的咒语时，他们就 不能 使用伤害为 power[i] - 2 ，power[i] - 1 ，power[i] + 1 或者 power[i] + 2 的咒语。
 * 
 * 每个咒语最多只能被使用 一次 。
 * 
 * 请你返回这个魔法师可以达到的伤害值之和的 最大值 。
 * 
 * 解题思路：
 * 
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */

public class Code_3186_MaximumTotalDamageWithSpellCasting {

    public long maximumTotalDamage(int[] power) {
        // map用于统计每个伤害值的咒语数量
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : power) {
            map.merge(num, 1, Integer::sum);
        }
        int n = map.size();
        // arr用于存储不同的伤害值
        int[] arr = new int[n];
        int idx = 0;
        for (int num : map.keySet()) {
            arr[idx++] = num;
        }
        // 对伤害值进行排序，方便后续动态规划的计算
        Arrays.sort(arr);
        
        // f[i] 表示使用前 i 个不同伤害值的咒语能达到的最大伤害值之和
        long[] f = new long[n + 1];
        int j = 0; // j 用于找到第一个满足 arr[j] >= arr[i] - 2 的索引
        for (int i = 0; i < n; i++) {
            int x = arr[i];
            while (arr[j] < x - 2) {
                j++;
            }
            // f[i + 1] 的计算基于两种情况：
            // 1. 不使用伤害值为 x 的咒语：f[i + 1] = f[i]
            // 2. 使用伤害值为 x 的咒语：f[i + 1] = f[j] + (long) x * map.get(x)，其中 f[j] 是使用前 j 个不同伤害值的咒语能达到的最大伤害值之和，(long) x * map.get(x) 是使用伤害值为 x 的咒语能达到的总伤害值
            f[i + 1] = Math.max(f[i], f[j] + (long) x * map.get(x));
        }
        return f[n];
    }
}
