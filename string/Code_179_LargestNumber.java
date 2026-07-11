package string;

import java.util.Arrays;

/**
 * @author CD
 * @date 7/11/2026
 */
public class Code_179_LargestNumber {
    public String largestNumber(int[] nums) {
        String[] values = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            values[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(values, (first, second) ->
                (second + first).compareTo(first + second));

        // 所有数字均为 0，例如 [0, 0]。
        if (values[0].equals("0")) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        for (String value : values) {
            result.append(value);
        }

        return result.toString();
    }
}
