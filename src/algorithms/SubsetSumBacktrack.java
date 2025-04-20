package src.algorithms;
import java.util.ArrayList;
import java.util.List;

public class SubsetSumBacktrack {

    // Method to determine if a subset with the given sum exists
    public static boolean isSubsetSum(int[] arr, int n, int target, List<Integer> subset) {
        // If target sum is reached, return true and print the subset
        if (target == 0) {
            System.out.println("Subset found: " + subset);
            return true;
        }

        // If no elements left and target is not met, return false
        if (n == 0) {
            return false;
        }

        // Exclude the last element and recurse
        if (isSubsetSum(arr, n - 1, target, new ArrayList<>(subset))) {
            return true;
        }

        // Include the last element if it does not exceed the target and recurse
        if (arr[n - 1] <= target) {
            subset.add(arr[n - 1]);
            if (isSubsetSum(arr, n - 1, target - arr[n - 1], new ArrayList<>(subset))) {
                return true;
            }
            subset.remove(subset.size() - 1); // Backtrack
        }

        // If no valid subset is found, return false
        return false;
    }
}


