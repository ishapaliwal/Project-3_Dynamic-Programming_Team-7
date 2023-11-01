package assignments;

import java.util.ArrayList;
import java.util.List;

public class P3_DynamicProgramming {

    public static List<List<Integer>> partition(int[] nums) {
    	long startTime = System.nanoTime(); // Taking the start time to calculate experimental time
    	
    	int total = 0;
        for (int num : nums) {
            total += num; // Total sum of all the elements of the array 
        }
        System.out.println("Total sum: " + total);
        
        if (total % 2 != 0) {
            return null; // It cannot be partitioned into two equal sums
        }

        int subsetSum = total / 2; // For partition to achieve subsets with equal sum, we divide the sum by 2.
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][subsetSum + 1]; // Creating a matrix/table/2-D array of (n+1) rows and (subsetSum + 1) columns.

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // Base Case for achieving sum of 0.
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= subsetSum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        
        long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); // Taking the end time after the implementation of algorithm has ended
		
        if (!dp[n][subsetSum]) {
            return null; // No partition exists
        }

        // Retrieving the subsets using backtracking
        List<Integer> subset1 = new ArrayList<>();
        List<Integer> subset2 = new ArrayList<>();

        int w = subsetSum;
        
        for (int i = n; i > 0; i--) {
            if (!dp[i - 1][w]) {
                subset1.add(nums[i - 1]);
                w -= nums[i - 1];
            } else {
                subset2.add(nums[i - 1]);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(subset1);
        result.add(subset2);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 5, 1};
        System.out.println(partition(nums));

        int[] nums2 = {323, 28, 8, 194, 253, 4, 193, 253, 96, 323, 95, 99, 286, 20, 304, 95, 116, 302, 283, 415, 22, 357, 122, 353, 304, 122, 353, 286, 20, 99, 357, 245, 415, 283, 216, 409, 8, 97, 28, 22, 116, 194, 245, 216, 96, 193, 4, 97, 409, 302};
        System.out.println(partition(nums2));
    }
}