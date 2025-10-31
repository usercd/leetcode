package greedy;

public class Code_45_JumpGameII {
    public int jump(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < n - 1; i++) {
            // Update the farthest reachable index
            farthest = Math.max(farthest, i + nums[i]);

            // If we have reached the end of the current jump
            if (i == currentEnd) {
                jumps++;
                // Update the end for the next jump
                currentEnd = farthest;

                if (currentEnd >= n - 1) {
                    break;
                }
            }
        }

        return jumps;
    }
}
