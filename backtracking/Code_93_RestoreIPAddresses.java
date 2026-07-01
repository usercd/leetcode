package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CD
 * @date 7/1/2026
 */
public class Code_93_RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        int n = s.length();
        if (n < 4 || n > 12) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        List<String> path = new ArrayList<>();

        backtrack(s, 0, 0, path, result);
        return result;
    }

    private void backtrack(String s, int start, int segmentCount, List<String> path, List<String> result) {
        int n = s.length();

        // 终止条件：恰好分了4段 且 用完了所有字符
        if (segmentCount == 4) {
            if (start == n) {
                result.add(String.join(".", path));
            }
            return;
        }

        int remainingSegments = 4 - segmentCount;
        int remainingChars = n - start;

        // 核心剪枝：剩余字符数必须在合法范围内
        if (remainingChars < remainingSegments || remainingChars > remainingSegments * 3) {
            return;
        }

        // 枚举当前段的长度（1~3位）
        for (int length = 1; length <= 3; length++) {
            int end = start + length;
            if (end > n) {
                break;
            }

            String segment = s.substring(start, end);

            // 前导零剪枝：长度>1 且 以'0'开头 → 非法
            if (length > 1 && segment.charAt(0) == '0') {
                break; // 更长的也必然非法，直接break
            }

            // 数值上限剪枝
            if (Integer.parseInt(segment) > 255) {
                break; // 更长的数字只会更大，直接break
            }

            path.add(segment);
            backtrack(s, end, segmentCount + 1, path, result);
            path.remove(path.size() - 1); // 撤销选择
        }
    }
}
