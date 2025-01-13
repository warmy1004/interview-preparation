/*
    377. combination sum IV
    Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
    The test cases are generated so that the answer can fit in a 32-bit integer.

    Example 1:
        Input: nums = [1,2,3], target = 4
        Output: 7
        Explanation:
        The possible combination ways are:
        (1, 1, 1, 1)
        (1, 1, 2)
        (1, 2, 1)
        (1, 3)
        (2, 1, 1)
        (2, 2)
        (3, 1)
        Note that different sequences are counted as different combinations.

    Example 2:
        Input: nums = [9], target = 3
        Output: 0

    Constraints:
        1 <= nums.length <= 200
        1 <= nums[i] <= 1000
        All the elements of nums are unique.
        1 <= target <= 1000
 */
package DP;

import java.util.HashMap;
import java.util.Map;

/*
 * Backtrack으로 풀 수 있으나, 비효율적이다. 중복계산을 하고, backtracking의 시간 복잡도가 O(b^n)으로, 모든 가능한 조합을 탐색하기 때문에, 최악의 경우 지수 시간 복잡도를 가짐. 
 * DP가 더 적합하다. 중복계산을 방지하기 때문.
 * 
 * in this problem, we are not asked to build the combinations, but rather the total number of combinations, which is actually a simpler problem that can be solved with DP. 
 */
public class P377_Must_ToDo_Medium {
    /*
     * Solution: top-down DP
     * 
     * Intuition:
     *      This is closer to a permutation, to be more precise, where the order of elements does matter. For example, both [1,3] and [3,1] could exist, which are valid and different for this problem.
     * 
     * time complexity: O(T*N), where T be the target value, and N be the number of elemtns in the input array
     * space complexity:
     */
    Map<Integer, Integer> topDown;
    public int combinationSum4(int[] nums, int target) {
        topDown = new HashMap<>();
        return recursiveTopDown(nums, target);
    }

    int recursiveTopDown(int[] nums, int target) {
        if(target == 0) return 1;
        if(target<0) return 0;
        if(topDown.containsKey(target)) {
            return topDown.get(target);
        }

        int result = 0;
        for(int num: nums) {
            result+=recursiveTopDown(nums, target-num);
        }
        topDown.put(target, result);
        return result;
    }

    /*
     * Solution: bottom-up DP
     */
    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target+1];
        dp[0] = 1;
        for(int combiSum = 1; combiSum <= target; combiSum++) {
            for(int num: nums) {
                if(combiSum - num >= 0) {
                    dp[combiSum] += dp[combiSum-num];
                }
            }
        }
        return dp[target];
    }
    
    /*
     * Solution: Backtracking - with large target number, TLE occured. So, not fully worked and not efficient.
     */
    int count = 0;
    public int combinationSum4(int[] nums, int target) {
        backtrack(nums, target);
        return count;
    }

    void backtrack(int[] nums, int target) {
        if(target<0) return;
        if(target == 0) {
            count++;
            return;
        }
        for(int num: nums) {
            backtrack(nums, target-num);
        }
    }
}
