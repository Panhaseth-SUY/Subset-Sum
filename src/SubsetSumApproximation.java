package src;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Implementation of the subset-sum approximation (FPTAS) using iterative merge and trimming.
 */
public class SubsetSumApproximation {

    /**
     * Computes an approximate subset sum that is the largest sum ≤ target,
     * within a factor of (1 - epsilon) of the optimal solution.
     *
     * @param nums    List of positive integers
     * @param target  Target sum
     * @param epsilon Approximation error tolerance (0 < epsilon < 1)
     * @return the approximate best subset sum ≤ target
     */
    public static double approximateSubsetSum(List<Integer> nums, int target, double epsilon) {
        int n = nums.size();
        // δ controls trimming granularity
        double delta = epsilon / (2 * n);

        // Initialize list of sums with only the empty subset
        List<Double> sums = new ArrayList<>();
        sums.add(0.0);

        // Iterate through each element
        for (int num : nums) {
            // Generate new sums by adding current number to all existing sums
            List<Double> newSums = new ArrayList<>(sums.size());
            for (double s : sums) {
                newSums.add(s + num);
            }

            // Merge old and new sums
            List<Double> merged = new ArrayList<>(sums.size() + newSums.size());
            merged.addAll(sums);
            merged.addAll(newSums);
            Collections.sort(merged);

            // Trim the merged list to remove values that are too close
            List<Double> trimmed = new ArrayList<>();
            double last = -1.0;
            for (double s : merged) {
                if (trimmed.isEmpty() || s > last * (1 + delta)) {
                    trimmed.add(s);
                    last = s;
                }
            }

            // Optionally prune sums that exceed the target to keep list smaller
            List<Double> pruned = new ArrayList<>();
            for (double s : trimmed) {
                if (s <= target) {
                    pruned.add(s);
                }
            }

            sums = pruned;
        }

        // Find the closest sum ≤ target
        double best = 0.0;
        for (double s : sums) {
            if (s <= target && s > best) {
                best = s;
            }
        }
        return best;
    }

    // Example usage
    public static void main(String[] args) {
        Random random = new Random();
        int setSize = 1000;
        int maxValue = 100;

        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < setSize; i++) {
            nums.add(random.nextInt(maxValue) + 1); // Generate random integers between 1 and maxValue
        }

        int target = 51263;
        double epsilon = 0.001;

        System.out.println("Random set: " + nums);
        double result = approximateSubsetSum(nums, target, epsilon);
        System.out.printf("Approximate subset sum ≤ %d: %.2f\n", target, result);
    }
}
