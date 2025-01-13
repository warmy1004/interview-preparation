/*
    39. Combination sum
    Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
    The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
    The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

    Example 1:
        Input: candidates = [2,3,6,7], target = 7
        Output: [[2,2,3],[7]]
        Explanation:
        2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
        7 is a candidate, and 7 = 7.
        These are the only two combinations.

    Example 2:
        Input: candidates = [2,3,5], target = 8
        Output: [[2,2,2,2],[2,3,3],[3,5]]

    Example 3:
        Input: candidates = [2], target = 1
        Output: []
    
    Constraints:
        1 <= candidates.length <= 30
        2 <= candidates[i] <= 40
        All elements of candidates are distinct.
        1 <= target <= 40
 */
package Backtracking;

/*
 * Similar Questions
 *  78. Subsets
    90. Subsets II
    46. Permutations
    47. Permutations II
    77. Combinations
    40. Combination Sum II
    216. Combination Sum III
    131. Palindrome Partition
    377. Combination Sum IV
 */

import java.util.ArrayList;
import java.util.List;

public class P39_ToDo_Medium {
    /*
     * Solution: backtracking
     * 
     * Let N be the number of candidates, T be the target value, and M be the minimal value among the candidates
     * time complexity: O(n^((T/M)+1))
     *      1. the fan-out of each node would be bounded to N, i.e. the total number of candidates
     *      2. the maximal depth of the tree, would be t/m, where we keep on adding the smallest element to the combination
     *      3. as we know, the maximal number of nodes in N-ary tree of t/m height would be N^((t/m)+1)
     * space complexity: O(t/m)
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        backtrack(candidates, 0, answer, new ArrayList<>(), target);
        return answer;
    }
    void backtrack(int[] candidates, int start, List<List<Integer>> answer, List<Integer> list, int target) {
        if(target < 0) return;
        if(target == 0) {
            answer.add(new ArrayList<>(list));
            return;
        }
        for(int i=start; i<candidates.length; i++) {
            list.add(candidates[i]);
            // duplicate is allowed so i instead of i+1
            backtrack(candidates, i, answer, list, target-candidates[i]);
            list.removeLast();
        }
    }
}
