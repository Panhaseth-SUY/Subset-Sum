package src.algorithms;

import java.util.ArrayList;
import java.util.List;

public class SubsetSumApproximation {

    public static Result approximateSubsetSum(List<Integer> nums, int target, double epsilon) {
        int n = nums.size();
        double delta = epsilon / (2 * n);

        // Initialize list of sums and corresponding subsets
        List<Integer> sums = new ArrayList<>();
        List<Integer> subsets = new ArrayList<>();
        sums.add(0);
        subsets.add(0); // Empty subset for sum 0

        int bestSum = 0;
        int bestSubsetIndex = 0;

        // Iterate through each element
        for (int elementIndex = 0; elementIndex < nums.size(); elementIndex++) {
            int num = nums.get(elementIndex);

            // Generate new sums and subsets by adding the current number
            List<Integer> newSums = new ArrayList<>();
            List<Integer> newSubsets = new ArrayList<>();
            for (int i = 0; i < sums.size(); i++) {
                int newSum = sums.get(i) + num;
                if (newSum <= target) { // Prune sums that exceed the target sum
                    newSums.add(newSum);
                    newSubsets.add(subsets.get(i) | (1 << elementIndex)); // Use elementIndex for bitmask
                }
            }

            // Merge old and new sums and subsets using two-pointer technique
            List<Integer> mergedSums = new ArrayList<>();
            List<Integer> mergedSubsets = new ArrayList<>();
            int i = 0, j = 0;
            while (i < sums.size() && j < newSums.size()) {
                if (sums.get(i) < newSums.get(j)) {
                    mergedSums.add(sums.get(i));
                    mergedSubsets.add(subsets.get(i));
                    i++;
                } else {
                    mergedSums.add(newSums.get(j));
                    mergedSubsets.add(newSubsets.get(j));
                    j++;
                }
            }
            while (i < sums.size()) {
                mergedSums.add(sums.get(i));
                mergedSubsets.add(subsets.get(i));
                i++;
            }
            while (j < newSums.size()) {
                mergedSums.add(newSums.get(j));
                mergedSubsets.add(newSubsets.get(j));
                j++;
            }

            // Trim the merged list
            List<Integer> trimmedSums = new ArrayList<>();
            List<Integer> trimmedSubsets = new ArrayList<>();
            int last = -1;
            for (int k = 0; k < mergedSums.size(); k++) {
                int s = mergedSums.get(k);
                if (trimmedSums.isEmpty() || s > last * (1 + delta)) {
                    trimmedSums.add(s);
                    trimmedSubsets.add(mergedSubsets.get(k));
                    last = s;

                    // Update best sum and subset during trimming
                    if (s <= target && s > bestSum) {
                        bestSum = s;
                        bestSubsetIndex = mergedSubsets.get(k);
                    }
                }
            }

            // Update sums and subsets with the trimmed lists
            sums = trimmedSums;
            subsets = trimmedSubsets;
        }

        // Decode the best subset from its index representation
        List<Integer> bestSubset = decodeSubset(bestSubsetIndex, nums);

        return new Result(bestSubset, bestSum);
    }

    // Helper method to decode a subset from its index representation
    private static List<Integer> decodeSubset(int subsetIndex, List<Integer> nums) {
        List<Integer> subset = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            if ((subsetIndex & (1 << i)) != 0) { // Check if the i-th bit is set
                subset.add(nums.get(i));
            }
        }
        return subset;
    }

    // Helper class to store the result
    public static class Result {
        private final List<Integer> subset;
        private final int sum;

        public Result(List<Integer> subset, int sum) {
            this.subset = subset;
            this.sum = sum;
        }

        public List<Integer> getSubset() {
            return subset;
        }

        public int getSum() {
            return sum;
        }

        @Override
        public String toString() {
            return subset + ", Sum: " + sum;
        }
    }
}