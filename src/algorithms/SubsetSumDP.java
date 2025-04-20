package src.algorithms;
import java.util.ArrayList;
import java.util.List;

public class SubsetSumDP {
    public static List<Integer> findSubsetWithSum(int[] nums, int target) {
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        dp[0][0] = true; // Base case: zero sum is always possible with an empty subset

        // Fill the DP table
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j]; // Exclude the current number
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]]; // Include the current number
                }
            }
        }

        // If no subset exists, return an empty list
        if (!dp[nums.length][target]) {
            return new ArrayList<>();
        }

        // Backtrack to find the subset
        List<Integer> subset = new ArrayList<>();
        int i = nums.length, j = target;
        while (i > 0 && j > 0) {
            if (!dp[i - 1][j]) { // If the current number is included
                subset.add(nums[i - 1]);
                j -= nums[i - 1];
            }
            i--;
        }

        return subset;
    }
}