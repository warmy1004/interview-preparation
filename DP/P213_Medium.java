/*
    213. House robber 2
    You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
    Input: nums = [2,3,2]
    Output: 3
    Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:
    Input: nums = [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    Total amount you can rob = 1 + 3 = 4.

Example 3:
    Input: nums = [1,2,3]
    Output: 3

Constraints:
    1 <= nums.length <= 100
    0 <= nums[i] <= 1000
 */
package DP;

public class P213_Medium {
    /*
     * Solution: space optimized DP
     * time comlexity: O(n)
     * space complexity: O(1)
     */
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int max1 = solve(nums, 0, nums.length-2);
        int max2 = solve(nums, 1, nums.length-1);
        return Math.max(max1, max2);
    }

    int solve(int[] nums, int start, int end) {
        int prev1 = 0, prev2= 0;
        for(int i=start; i<=end; i++) {
            int temp = prev1;
            prev1 = Math.max(prev1, nums[i]+prev2);
            prev2 = temp;
        }
        return prev1;
    }

    /*
     * Solution: regular dp
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int p1 = solve(nums, 0, nums.length-2);
        int p2 = solve(nums, 1, nums.length-1);
        return Math.max(p1, p2);
    }

    int solve(int[] nums, int start, int end) {
        if(start == end) return nums[start];
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start+1] = Math.max(nums[start], nums[start+1]);
        for(int i=start+2; i<=end; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return dp[end];
    }
}
