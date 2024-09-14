/*
    746. Min Cost Climbing Stairs

    You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps
    You can either start from the step with index 0, or the step with index 1.
    Return the minimum cost to reach the top of the floor.

    Example 1:
        Input: cost = [10,15,20]
        Output: 15
        Explanation: You will start at index 1.
            - Pay 15 and climb two steps to reach the top.
            The total cost is 15.

    Example 2:
        Input: cost = [1,100,1,1,1,100,1,1,100,1]
        Output: 6
        Explanation: You will start at index 0.
            - Pay 1 and climb two steps to reach index 2.
            - Pay 1 and climb two steps to reach index 4.
            - Pay 1 and climb two steps to reach index 6.
            - Pay 1 and climb one step to reach index 7.
            - Pay 1 and climb two steps to reach index 9.
            - Pay 1 and climb one step to reach the top.
            The total cost is 6.
    
    Constraints:
        2 <= cost.length <= 1000
        0 <= cost[i] <= 999
 */
package DP;

/*
 * Similar Questions:
 *      70. Climbing Stairs (Easy)
 *      3154. Find number of ways to reach the k-th stair (Hard)
 *      198. House Robber (Medium)
 *      256. Paint House (Medium)
 *      509. Fibonacci Number(Easy)
 *      931. Minimum Falling Path Sum (Medium)
 */
public class P746_ToDo_Easy {
    /*
     * Solution 1: DP
     * time complexity: O(n)
     * space compelxity: O(n)
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i<cost.length; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2])+cost[i];
        }
        return Math.min(dp[cost.length-1], dp[cost.length-2]);
    }

    /*
     * Solution 2: DP_second- Bottom up Tabulation
     * time complexity: O(n)
     * space compelxity: O(n)
     */
    public int minCostClimbingStairs_second(int[] cost) {
        int[] dp = new int[cost.length+1];
        dp[0]= 0;
        dp[1]= 0;
        for (int i = 2; i<cost.length+1; i++) {
            dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
        }
        return dp[cost.length];
    }

    /*
     * Solution 3: DP_Bottom-up_constant space 1
     * time complexity: O(n)
     * space compelxity: O(1)
     */
    public int minCostClimbingStairs_third(int[] cost) {
        int first = cost[0];
        int second = cost[1];
        
        for(int i = 2; i<cost.length+1; i++) {
            int curr = cost[i] + Math.min(first, second);
            first = second;
            second = curr;
        }
        return Math.min(first, second);
    }

    /*
     * Solution 4: DP_Bottom-up_constant space 2
     * time complexity: O(n)
     * space compelxity: O(1)
     */
    public int minCostClimbingStairs_leetcode(int[] cost) {
        int first = 0;
        int second = 0;
        
        // iterate over the array, again with 1 extra iteration at the end to treat the top floor as the final "step".
        // at each iteration, simulate moving 1 step up. This means, first will now refer to the current step, so apply the recurrence relation to update first.
        // second will be whatever first was prior to the update, so let's use a temporary variable to help with the update.
        for(int i = 2; i<cost.length+1; i++) {
            int temp = first;
            first = Math.min(first+cost[i-1], second+cost[i-2]);
            second = temp;
        }
        return first;
    }
}
