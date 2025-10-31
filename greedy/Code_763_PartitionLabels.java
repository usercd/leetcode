package greedy;

import java.util.List;
import java.util.ArrayList;

public class Code_763_PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        // Record the last occurrence of each character
        int[] lastOccurrence = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        List<Integer> partitions = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            // Update the end of the current partition
            end = Math.max(end, lastOccurrence[s.charAt(i) - 'a']);
            if (i == end) {
                partitions.add(end - start + 1);
                start = i + 1;
            }
        }
        return partitions;
    }
}
