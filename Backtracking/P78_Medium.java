/*
    78. Subsets
    Given an integer array nums of unique elements, return all possible subsets (the power set).
    The solution set must not contain duplicate subsets. Return the solution in any order.

    Example 1:
        Input: nums = [1,2,3]
        Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

    Example 2:
        Input: nums = [0]
        Output: [[],[0]]
    
    Constraints:
        1 <= nums.length <= 10
        -10 <= nums[i] <= 10
        All the numbers of nums are unique.
 */
package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class P78_Medium {
    /*
     * Solution: backtrack
     * 
     * time complexity: O(2^n)
     *      N is the number of elements in the input array. We have two choices for each number; pick the element and move ahead (increment the index) OR, don't pick the element and move ahead
     * space complexity: O(n)
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), answer);
        return answer;
    }
    void backtrack(int[] nums, int start, List<Integer> list, List<List<Integer>> answer) {
        answer.add(list);
        for(int i=start; i<nums.length; i++) {
            list.add(nums[i]);
            backtrack(nums, i+1, list, answer);
            list.removeLast();
        }
    }

    /*
     * Solution: cascading
     * 
     * time complexity: O(N*2^N)
     * space complexity: O(N*2^N)
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(new ArrayList<>());
        for(int num: nums) {
            List<List<Integer>> subsets = new ArrayList<>();
            for(List<Integer> curr: answer) {
                List<Integer> temp = new ArrayList<>(curr);
                temp.add(num);
                subsets.add(temp);
            }
            for(List<Integer> curr: subsets) {
                answer.add(curr);
            }
        }
        return answer;
    }
}
