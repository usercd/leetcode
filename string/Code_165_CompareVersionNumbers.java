package string;

/**
 * @author CD
 * @date 7/11/2026
 */
public class Code_165_CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        int i = 0;
        int j = 0;
        int m = version1.length();
        int n = version2.length();

        while (i < m || j < n) {
            int start1 = i;
            int start2 = j;

            while (i < m && version1.charAt(i) != '.') {
                i++;
            }

            while (j < n && version2.charAt(j) != '.') {
                j++;
            }

            int end1 = i;
            int end2 = j;

            // 去掉当前修订号的前导零。
            while (start1 < end1 && version1.charAt(start1) == '0') {
                start1++;
            }

            while (start2 < end2 && version2.charAt(start2) == '0') {
                start2++;
            }

            int length1 = end1 - start1;
            int length2 = end2 - start2;

            // 有效数字位数更多的修订号更大。
            if (length1 != length2) {
                return length1 > length2 ? 1 : -1;
            }

            // 位数相同时，按字典序逐位比较即可。
            for (int k = 0; k < length1; k++) {
                char ch1 = version1.charAt(start1 + k);
                char ch2 = version2.charAt(start2 + k);

                if (ch1 != ch2) {
                    return ch1 > ch2 ? 1 : -1;
                }
            }

            // 跳过 '.'，若已经到末尾则不会越界。
            if (i < m) {
                i++;
            }

            if (j < n) {
                j++;
            }
        }

        return 0;
    }
}
