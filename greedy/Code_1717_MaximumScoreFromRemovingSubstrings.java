package greedy;

/**
 * 题目大意：
 * 给你一个字符串 s 和两个整数 x 和 y 。你可以执行以下两种操作任意次数：
 * 删除子字符串 "ab" 并获得 x 分。
 * 删除子字符串 "ba" 并获得 y 分。
 * 返回你在对 s 执行上述操作若干次后可以获得的 最大分数 。
 * 
 * 解题思路：
 * 贪心策略：优先删除得分更高的子字符串。使用栈模拟删除过程，先处理得分更高的子字符串，然后处理剩余的子字符串。
 * 
 * 复杂度分析：
 * 时间复杂度：O(N)，其中 N 是字符串 s 的长度。需要遍历字符串两次。
 * 空间复杂度：O(N)，用于栈存储字符。
 */

public class Code_1717_MaximumScoreFromRemovingSubstrings {
    public int maximumGain(String s, int x, int y) {
        int totalScore = 0;
        char firstChar = x >= y ? 'a' : 'b';
        char secondChar = x >= y ? 'b' : 'a';
        int firstScore = Math.max(x, y);
        int secondScore = Math.min(x, y);

        // First pass: remove all instances of the higher scoring substring
        RemoveResult firstResult = removeSubstrings(s, firstChar, secondChar, firstScore);
        totalScore += firstResult.score;

        // Second pass: remove all instances of the lower scoring substring from the remaining string
        RemoveResult secondResult = removeSubstrings(firstResult.remaining, secondChar, firstChar, secondScore);
        totalScore += secondResult.score;

        return totalScore;
    }

    private RemoveResult removeSubstrings(String s, char firstChar, char secondChar, int score) {
        int currentScore = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == secondChar && sb.length() > 0 && sb.charAt(sb.length() - 1) == firstChar) {
                sb.deleteCharAt(sb.length() - 1);
                currentScore += score;
            } else {
                sb.append(c);
            }
        }
        return new RemoveResult(currentScore, sb.toString());
    }

    // Helper class to store both score and remaining string
    private static class RemoveResult {
        int score;
        String remaining;

        RemoveResult(int score, String remaining) {
            this.score = score;
            this.remaining = remaining;
        }
    }

    /**
     * 方法2：使用char数组模拟栈（空间优化版）
     * 优势：避免StringBuilder的自动扩容和对象创建开销，使用原始数组更高效
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)，但常数因子更小
     */
    public int maximumGain2(String s, int x, int y) {
        // 确定优先处理的字符对
        if (x < y) {
            return maximumGainHelper(s, y, x, 'b', 'a');
        } else {
            return maximumGainHelper(s, x, y, 'a', 'b');
        }
    }

    private int maximumGainHelper(String s, int highScore, int lowScore, char first, char second) {
        int totalScore = 0;
        char[] chars = s.toCharArray();
        int top = -1; // 栈顶指针

        // 第一遍：删除高分字符对
        for (char c : chars) {
            if (top >= 0 && chars[top] == first && c == second) {
                top--; // 弹出栈顶
                totalScore += highScore;
            } else {
                chars[++top] = c; // 入栈
            }
        }

        // 第二遍：在剩余字符中删除低分字符对
        int newTop = -1;
        for (int i = 0; i <= top; i++) {
            if (newTop >= 0 && chars[newTop] == second && chars[i] == first) {
                newTop--;
                totalScore += lowScore;
            } else {
                chars[++newTop] = chars[i];
            }
        }

        return totalScore;
    }

    /**
     * 方法3：分段计数贪心法（最优解）
     * 核心思想：
     * 1. 将字符串按照非a、非b字符分段
     * 2. 对每段只包含a和b的子串，使用贪心策略：
     *    - 优先消除得分高的字符对（模拟栈的配对过程）
     *    - 剩余的字符再消除得分低的字符对
     * 3. 通过计数而非栈操作，实现O(1)空间处理每个段
     * 
     * 优势：
     * - 时间复杂度：O(N)
     * - 空间复杂度：O(1)（除了输入输出，不使用额外空间）
     * - 常数因子最小，没有栈操作开销
     */
    public int maximumGain3(String s, int x, int y) {
        int totalScore = 0;
        
        // 确定哪个字符对得分更高及其配对规则
        // 如果x>=y，优先删除"ab"；否则优先删除"ba"
        char highFirst, highSecond;
        int highScore, lowScore;
        
        if (x >= y) {
            highFirst = 'a';   // "ab"中的第一个字符
            highSecond = 'b';  // "ab"中的第二个字符
            highScore = x;
            lowScore = y;
        } else {
            highFirst = 'b';   // "ba"中的第一个字符
            highSecond = 'a';  // "ba"中的第二个字符
            highScore = y;
            lowScore = x;
        }
        
        int aCount = 0, bCount = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == 'a') {
                // 如果优先删除"ba"（highFirst='b', highSecond='a'），
                // 且当前遇到'a'（第二个字符），若有'b'则立即配对
                if (highSecond == 'a' && bCount > 0) {
                    bCount--;
                    totalScore += highScore;
                } else {
                    aCount++;
                }
            } else if (c == 'b') {
                // 如果优先删除"ab"（highFirst='a', highSecond='b'），
                // 且当前遇到'b'（第二个字符），若有'a'则立即配对
                if (highSecond == 'b' && aCount > 0) {
                    aCount--;
                    totalScore += highScore;
                } else {
                    bCount++;
                }
            } else {
                // 遇到非a、b字符，处理剩余的a和b（形成低分配对）
                totalScore += processRemaining(aCount, bCount, lowScore);
                aCount = 0;
                bCount = 0;
            }
        }
        
        // 处理最后剩余的a和b
        totalScore += processRemaining(aCount, bCount, lowScore);
        
        return totalScore;
    }
    
    private int processRemaining(int aCount, int bCount, int score) {
        // 剩余的a和b可以配对的数量是min(aCount, bCount)
        return Math.min(aCount, bCount) * score;
    }
}
