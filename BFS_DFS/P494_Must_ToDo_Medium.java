/*
    494. Target sum
    You are given an integer array nums and an integer target.
    You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
    For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
    Return the number of different expressions that you can build, which evaluates to target.

    Example 1:
        Input: nums = [1,1,1,1,1], target = 3
        Output: 5
        Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
        -1 + 1 + 1 + 1 + 1 = 3
        +1 - 1 + 1 + 1 + 1 = 3
        +1 + 1 - 1 + 1 + 1 = 3
        +1 + 1 + 1 - 1 + 1 = 3
        +1 + 1 + 1 + 1 - 1 = 3

    Example 2:
        Input: nums = [1], target = 1
        Output: 1
    
    Constraints:
        1 <= nums.length <= 20
        0 <= nums[i] <= 1000
        0 <= sum(nums[i]) <= 1000
        -1000 <= target <= 1000
 */
package BFS_DFS;

import java.util.Arrays;

public class P494_Must_ToDo_Medium {
    /*
     * Solution: dfs
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, target, 0, 0);
    }

    private int dfs(int[] nums, int target, int i, int sum) {
        if(i >= nums.length) return sum==target? 1: 0;
        int minusTrack = dfs(nums, target, i+1, sum-nums[i]);
        int plusTrack = dfs(nums, target, i+1, sum+nums[i]);
        return minusTrack + plusTrack;
    }

    /*
     * Solution: using Map - optimization
     * time complexity: O(n^2)
     * space complexity: O(n^2)
     */
    Map<String, Integer> map = new HashMap<>();
    public int findTargetSumWays(int[] nums, int target) {
        return helper(nums, target, 0, 0);
    }
    private int helper(int[] nums, int target, int i, int sum) {
        if(i >=nums.length) return target == sum? 1: 0;
        String key = sum+"-"+i;
        if(map.containsKey(key)) {
            return map.get(key);
        }
        int count = 0;
        count += helper(nums, target, i+1, sum-nums[i]);
        count += helper(nums, target, i+1, sum+nums[i]);
        map.put(key, count);
        return count;
    }
}
