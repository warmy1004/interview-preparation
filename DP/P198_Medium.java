/*
    198. House Rubber
    You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
    the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
    Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

    Example 1:
        Input: nums = [1,2,3,1]
        Output: 4
        Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
        Total amount you can rob = 1 + 3 = 4.

    Example 2:
        Input: nums = [2,7,9,3,1]
        Output: 12
        Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
        Total amount you can rob = 2 + 9 + 1 = 12.

    Constraints:
        1 <= nums.length <= 100
        0 <= nums[i] <= 400
 */
package DP;

public class P198_Medium {
    /*
     * Solution1: Bottom up DP with memoization
     * 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[n] = 0;
        dp[n-1] = nums[n-1];

        // Starting backward is more intuitive that propagating the max backwards will lead to the correct solution than doing so in the forward direction.
        // On the other way, it's easy to state a base case starting at the end. You can't do so at the beginning because you need the complete solution.
        // By starting at the end, you will already know which is the best option to take by the time you arrive at the previous indexes. 
        for(int i=n-2; i>=0; i--) {
            dp[i] = Math.max(dp[i+1], dp[i+2]+nums[i]);
        }
        return dp[0];
    }

    /*
     * Solution 2: Bottom up DP with memoization 2
     * 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int rob_memo(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return dp[n-1];
    }

    /*
     * Solution 3: Bottom up DP - 2nd
     * 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int rob_constant(int[] nums) {
        int rob = 0;  // max money if robbing the current house
        int pass = 0;   // max money if DO NOT robbing the current house
        for(int i=0; i<nums.length; i++) {
            int curr_rob = pass + nums[i]; // if rob the current house, the expected money from the previous house which is not right next to the current one
            pass= Math.max(pass, rob); // if don't rob the current house, take the max value of previously robbed houses ( i-1 house vs. !i-1 house)
            rob = curr_rob;
        }
        return Math.max(rob, pass);
    }
    
}
