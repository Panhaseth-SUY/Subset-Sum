package src.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import src.algorithms.SubsetSumApproximation;
import src.algorithms.SubsetSumBacktrack;
import src.algorithms.SubsetSumDP;

public class SubsetSumSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Subset Sum Solver Application!");
        System.out.println("Please configure the problem:");

        System.out.print("Enter the size of the set: ");
        int setSize = scanner.nextInt();

        System.out.print("Enter the maximum value for elements in the set: ");
        int maxValue = scanner.nextInt();

        System.out.print("Enter the target sum: ");
        int target = scanner.nextInt();

        List<Integer> nums = new ArrayList<>();

        // Generate a random set of integers
        for (int i = 0; i < setSize; i++) {
            nums.add(random.nextInt(maxValue) + 1); // Generate random integers between 1 and maxValue
        }

        System.out.println("\nGenerated Random Set: " + nums);
        System.out.println("Target Sum: " + target);

        while (true) {
            System.out.println("\nChoose a method to solve the Subset Sum Problem:");
            System.out.println("1. Backtracking");
            System.out.println("2. Dynamic Programming");
            System.out.println("3. Approximation");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("\nBacktracking:");
                    long btStart = System.nanoTime();
                    List<Integer> btResult = SubsetSumBacktrack.findSubsetSum(nums.stream().mapToInt(i -> i).toArray(), nums.size(), target);
                    if (btResult != null) {
                        System.out.println("A subset exists with the sum " + target + ": " + btResult);
                    } else {
                        System.out.println("No subset exists with the sum " + target);
                    }
                    long btEnd = System.nanoTime();
                    System.out.printf("Time taken: %.2f ms\n", (btEnd - btStart) / 1e6);
                }

                case 2 -> {
                    System.out.println("\nDynamic Programming:");
                    long dpStart = System.nanoTime();
                    List<Integer> dpResult = SubsetSumDP.findSubsetWithSum(nums.stream().mapToInt(i -> i).toArray(), target);
                    if (!dpResult.isEmpty()) {
                        System.out.println("A subset exists with the sum " + target + ": " + dpResult);
                    } else {
                        System.out.println("No subset exists with the sum " + target);
                    }
                    long dpEnd = System.nanoTime();
                    System.out.printf("Time taken: %.2f ms\n", (dpEnd - dpStart) / 1e6);
                }

                case 3 -> {
                    System.out.println("\nApproximation:");
                    System.out.print("Enter the approximation epsilon value: ");
                    double epsilon = scanner.nextDouble();
                    long approxStart = System.nanoTime();
                    List<Integer> approxResult = SubsetSumApproximation.approximateSubsetSum(nums, target, epsilon);
                    long approxEnd = System.nanoTime();
                    if (!approxResult.isEmpty()) {
                        System.out.println("An approximate subset exists with the sum ≤ " + target + ": " + approxResult);
                    } else {
                        System.out.println("No approximate subset exists with the sum ≤ " + target);
                    }
                    System.out.printf("Time taken: %.2f ms\n", (approxEnd - approxStart) / 1e6);
                }

                case 4 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
