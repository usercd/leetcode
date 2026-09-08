package sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cd
 * @date 2026.09.08
 * @description
 */
public class Code_159_LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        for (int i = 0, j = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.size() > 2) {
                char c1 = s.charAt(j);
                map.put(c1, map.get(c1) - 1);
                if (map.get(c1) == 0) map.remove(c1);
                j++;
            }
            result = Math.max(result, i - j + 1);
        }

        return result;
    }
}
