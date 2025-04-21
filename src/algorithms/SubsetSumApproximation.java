package src.algorithms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @return the approximate best subset that produces the sum ≤ target
     */
    public static List<Integer> approximateSubsetSum(List<Integer> nums, int target, double epsilon) {
        int n = nums.size();
        // δ controls trimming granularity
        double delta = epsilon / (2 * n);

        // Initialize list of sums with only the empty subset
        List<Double> sums = new ArrayList<>();
        Map<Double, List<Integer>> subsets = new HashMap<>();
        sums.add(0.0);
        subsets.put(0.0, new ArrayList<>());

        // Iterate through each element
        for (int num : nums) {
            // Generate new sums by adding current number to all existing sums
            List<Double> newSums = new ArrayList<>(sums.size());
            Map<Double, List<Integer>> newSubsets = new HashMap<>();
            for (double s : sums) {
                double newSum = s + num;
                newSums.add(newSum);

                // Create a new subset by adding the current number
                List<Integer> newSubset = new ArrayList<>(subsets.get(s));
                newSubset.add(num);
                newSubsets.put(newSum, newSubset);
            }

            // Merge old and new sums
            List<Double> merged = new ArrayList<>(sums.size() + newSums.size());
            merged.addAll(sums);
            merged.addAll(newSums);
            Collections.sort(merged);

            // Trim the merged list to remove values that are too close
            List<Double> trimmed = new ArrayList<>();
            Map<Double, List<Integer>> trimmedSubsets = new HashMap<>();
            double last = -1.0;
            for (double s : merged) {
                if (trimmed.isEmpty() || s > last * (1 + delta)) {
                    trimmed.add(s);
                    trimmedSubsets.put(s, subsets.containsKey(s) ? subsets.get(s) : newSubsets.get(s));
                    last = s;
                }
            }

            // Optionally prune sums that exceed the target to keep list smaller
            List<Double> pruned = new ArrayList<>();
            Map<Double, List<Integer>> prunedSubsets = new HashMap<>();
            for (double s : trimmed) {
                if (s <= target) {
                    pruned.add(s);
                    prunedSubsets.put(s, trimmedSubsets.get(s));
                }
            }

            sums = pruned;
            subsets = prunedSubsets;
        }

        // Find the closest sum ≤ target
        double best = 0.0;
        for (double s : sums) {
            if (s <= target && s > best) {
                best = s;
            }
        }

        return subsets.get(best);
    }
}
