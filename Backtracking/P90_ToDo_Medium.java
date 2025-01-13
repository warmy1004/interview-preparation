/*
    90. Subsets 2
    Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
    The solution set must not contain duplicate subsets. Return the solution in any order.

    Example 1:
        Input: nums = [1,2,2]
        Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

    Example 2:
        Input: nums = [0]
        Output: [[],[0]]
    
    Constraints:
        1 <= nums.length <= 10
        -10 <= nums[i] <= 10
 */
package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P90_ToDo_Medium {
    /*
     * Solution: backtrack
     * 
     * time complexity: O(n*2^n)
     * space complexity: O(n)
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), answer);
        return answer;
    }

    void backtrack(int[] nums, int start, List<Integer> list, List<List<Integer>> answer) {
        answer.add(new ArrayList<>(list));
        for(int i=start; i<nums.length; i++) {
            // if the current element is a duplicate, ignore
            if(i!=start && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            backtrack(nums, i+1, list, answer);
            list.removeLast();
        }
    }

    /*
     * Solution: cascading - iterative
     * 
     * time complexity: O(n*2^n)
     * space complexity: O(n)
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(new ArrayList<>());
        int subsetSize = 0;
        for(int i=0; i<nums.length; i++) {
            int start = (i>=1 && nums[i]==nums[i-1]) ? subsetSize : 0;
            subsetSize = answer.size();
            for(int j=start; j<subsetSize; j++) {
                List<Integer> curr = new ArrayList<>(answer.get(j));
                curr.add(nums[i]);
                answer.add(curr);
            }
        }
        return answer;
    }
}
