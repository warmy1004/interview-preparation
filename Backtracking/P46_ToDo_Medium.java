/*
    46. Permutations
    Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

    Example 1:
        Input: nums = [1,2,3]
        Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

    Example 2:
        Input: nums = [0,1]
        Output: [[0,1],[1,0]]

    Example 3:
        Input: nums = [1]
        Output: [[1]]
    
    Constraints:
        1 <= nums.length <= 6
        -10 <= nums[i] <= 10
        All the integers of nums are unique.
 */
package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class P46_ToDo_Medium {
    /*
     * Solution: backtracking
     * time complexity: O(n*n!)
     *      Finding permutations is a well-studied problem in combinatorics. Given a set of length n, the number of permutations is n! (n factorial). There are n options for the first number, n-1 for the second, and so on.
     *      For each of the n! permutations, we need O(n) work to copy list into the answer. So, this gives us O(n*n!) work
     * space complexity: O(n)
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), answer);
        return answer;
    }

    void backtrack(int[] nums, List<Integer> list, List<List<Integer>> answer) {
        if(list.size() == nums.length) {
            answer.add(new ArrayList<>(list));
            return;
        }
        for(int num: nums) {
            if(!list.contains(num)) {
                list.add(num);
                backtrack(nums, list, answer);
                list.removeLast();
            }
        }
    }
}
