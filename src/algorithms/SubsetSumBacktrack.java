package src.algorithms;
import java.util.ArrayList;
import java.util.List;

public class SubsetSumBacktrack {

    // Method to determine the subset with the given sum
    public static List<Integer> findSubsetSum(int[] arr, int n, int target) {
        List<Integer> subset = new ArrayList<>();
        return findSubsetSumHelper(arr, n, target, subset);
    }

    private static List<Integer> findSubsetSumHelper(int[] arr, int n, int target, List<Integer> subset) {
        // If target sum is reached, return the subset
        if (target == 0) {
            return new ArrayList<>(subset);
        }

        // If no elements left and target is not met, return null
        if (n == 0) {
            return null;
        }

        // Exclude the last element and recurse
        List<Integer> result = findSubsetSumHelper(arr, n - 1, target, new ArrayList<>(subset));
        if (result != null) {
            return result;
        }

        // Include the last element if it does not exceed the target and recurse
        if (arr[n - 1] <= target) {
            subset.add(arr[n - 1]);
            result = findSubsetSumHelper(arr, n - 1, target - arr[n - 1], new ArrayList<>(subset));
            if (result != null) {
                return result;
            }
            subset.remove(subset.size() - 1); // Backtrack
        }

        // If no valid subset is found, return null
        return null;
    }
}
