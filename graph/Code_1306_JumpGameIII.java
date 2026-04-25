package graph;

public class Code_1306_JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        if (start < 0 || start >= arr.length || arr[start] < 0) {
            return false;
        }
        if (arr[start] == 0) {
            return true;
        }
        int jump = arr[start];
        // 标记为已访问 避免死循环 -1 代表已访问过
        arr[start] = -arr[start];
        return canReach(arr, start + jump) || canReach(arr, start - jump);
    }
}
