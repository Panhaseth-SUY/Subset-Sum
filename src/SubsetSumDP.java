package src;
public class SubsetSumDP {
    public static boolean isSubsetSum(int[] nums, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // Base case: zero sum is always possible

        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                if (dp[j - num]) {
                    dp[j] = true;
                }
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 7, 5, 2, 6, 4, 8, 9, 10};  // Size 10
        int target = 15;

        boolean result = isSubsetSum(nums, target);
        if (result) {
            System.out.println("A subset exists with the sum " + target);
        } else {
            System.out.println(" No subset exists with the sum " + target);
        }
    }
}
