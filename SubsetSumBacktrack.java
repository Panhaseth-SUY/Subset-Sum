

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

//    public static void main(String[] args) {
//        int[] arr = {5, 8, 2, 3}; // Given set of numbers
//        int target = 13; // Target sum to be found
//        int n = arr.length; // Length of the array
//
//        // Call the function to check if a subset exists
//        if (!isSubsetSum(arr, n, target, new ArrayList<>())) {
//            System.out.println("No subset found.");
//        }
//    }


