# Subset Sum Solver

## Overview

The **Subset Sum Solver** is a Java-based application that provides solutions to the Subset Sum Problem using three different approaches:
1. **Backtracking**: A brute-force method to find an exact solution.
2. **Dynamic Programming (DP)**: An efficient method to find an exact solution.
3. **Approximation (FPTAS)**: A faster method to find an approximate solution within a specified error tolerance.

The application generates a random set of integers based on user input and allows the user to choose a method to solve the problem interactively.

## Project Structures
/Subset-Sum/
├── src/
│   ├── main/
│   │   └── SubsetSumSolver.java
│   ├── algorithms/
│   │   ├── SubsetSumApproximation.java
│   │   ├── SubsetSumBacktrack.java
│   │   └── SubsetSumDP.java
├── bin/


## How to Use

### 1. **Setup**
- Ensure you have Java installed (version 17 or later is recommended).
- Clone this repository to your local machine:
  ```bash
  git clone https://github.com/your-username/Subset-Sum.git
  cd Subset-Sum

  Compile the project:
javac -d bin src/main/SubsetSumSolver.java src/algorithms/*.java
  Run the Application
  java -cp bin src.main.SubsetSumSolver

## Interactive Usage
When you run the application, you will be prompted to:

Enter the size of the set (number of elements).
Enter the maximum value for elements in the set.
Enter the target sum.
The application will generate a random set of integers and display the following menu:

Choose a method to solve the Subset Sum Problem:
1. Backtracking
2. Dynamic Programming
3. Approximation
4. Exit

Option 1: Backtracking
Solves the problem using a brute-force approach.
Displays the subset (if found) or indicates that no subset exists.
Shows the time taken to compute the result.
Option 2: Dynamic Programming
Solves the problem using an efficient DP-based approach.
Displays the subset (if found) or indicates that no subset exists.
Shows the time taken to compute the result.
Option 3: Approximation
Solves the problem using an approximation algorithm (FPTAS).
Prompts you to enter an epsilon value (error tolerance, e.g., 0.1).
Displays an approximate subset with a sum ≤ target.
Shows the time taken to compute the result.
Option 4: Exit (Exits the application.)

## Example Usage
Input:
Enter the size of the set: 5
Enter the maximum value for elements in the set: 20
Enter the target sum: 25

Output:
Generated Random Set: [3, 15, 7, 10, 5]
Target Sum: 25

Choose a method to solve the Subset Sum Problem:
1. Backtracking
2. Dynamic Programming
3. Approximation
4. Exit

### Example for Backtracking:
Backtracking:
A subset exists with the sum 25: [15, 10]
Time taken: 0.12 ms

### Example for Approximation:
Approximation:
Enter the approximation epsilon value: 0.1
An approximate subset exists with the sum ≤ 25: [15, 7]
Time taken: 0.05 ms

## Notes
The Backtracking method is slower for larger sets due to its brute-force nature.
The Dynamic Programming method is efficient but may consume more memory for large target sums.
The Approximation method is faster but provides an approximate solution within the specified error tolerance.

## License
This project aims to researching problem for data structure class. Feel free to use, modify, and distribute it as needed.
