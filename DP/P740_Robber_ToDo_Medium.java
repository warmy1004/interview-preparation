/*
    740. Delete and Earn
    You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
    Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
    Return the maximum number of points you can earn by applying the above operation some number of times.

    Example 1:
        Input: nums = [3,4,2]
        Output: 6
        Explanation: You can perform the following operations:
            - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
            - Delete 2 to earn 2 points. nums = [].
            You earn a total of 6 points.

    Example 2:
        Input: nums = [2,2,3,3,3,4]
        Output: 9
        Explanation: You can perform the following operations:
            - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
            - Delete a 3 again to earn 3 points. nums = [3].
            - Delete a 3 once more to earn 3 points. nums = [].
            You earn a total of 9 points.
    
    Constraints:
        1 <= nums.length <= 2 * 10^4
        1 <= nums[i] <= 10^4
 */
package DP;

public class P740_Robber_ToDo_Medium {
    /*
     * Solution: DP - Bottom up
     *      Because it iteratively solve smaller subproblems and build up to the final solution. 
     *      In this case, the solution is constructed by iterating from the highest value down to 0, and at each stpe, 
     *      you're making decisions based on previously computed subproblem results. 
     *      That is, the idea is that this code is building the solution from the smallest subproblems and accumulating results to solve larger subproblems,
     *      eventually solving the problem for dp[0]
     *          
     * time complexity: O(n+max)
     * space complexity: O(max)
     */
    public int deleteAndEarn(int[] nums) {
        int max = Integer.MIN_VALUE;
        for(int num: nums) {
            max = Math.max(num, max);
        }

        int[] dist = new int[max+1];
        for(int i=0; i<nums.length; i++) {
            dist[nums[i]]++;
        }

        int[] dp = new int[max+2];
        dp[max+1] = 0;
        dp[max] = dist[max]*max;
        for(int i=max-1; i>=0; i--) {
            dp[i] = Math.max(dp[i+1], dp[i+2] + i*dist[i]);
        }
        return dp[0];
    }

    public int deleteAndEarn(int[] nums) {
        int max = Integer.MIN_VALUE;
       for(int num: nums) {
           max = Math.max(num, max);
       }

       int[] dist = new int[max+1];
       for(int i=0; i<nums.length; i++) {
           dist[nums[i]]+=nums[i];
       }

       int[] dp = new int[max+1];
       dp[0] = 0;
       dp[1] = dist[1];
       for(int i= 2; i< max+1; i++) {
           dp[i] = Math.max(dp[i-1], dp[i-2]+dist[i]);
       }
       return dp[max];
   }
}
