/*
    47. Permutations 2
    Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
    Example 1:
    Input: nums = [1,1,2]
    Output:
        [[1,1,2],
        [1,2,1],
        [2,1,1]]

    Example 2:
        Input: nums = [1,2,3]
        Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    
    Constraints:
        1 <= nums.length <= 8
        -10 <= nums[i] <= 10
 */
package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P47_ToDo_Medium {
    /*
     * Solution: backtrack
     * time complexity: O(n*n!)
     *      Number of permutations = P(N, N) = N! and Each permutation takes O(N)
     * space complexity: O(n)
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, new ArrayList<>(), answer, new boolean[nums.length]);
        return answer;
    }

    void backtrack(int[] nums, List<Integer> list, List<List<Integer>> answer, boolean[] visited) {
        if(list.size() == nums.length) {
            answer.add(new ArrayList<>(list));
            return;
        }
        for(int i=0; i<nums.length; i++) {
            if(visited[i] || i>0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            visited[i] = true;
            list.add(nums[i]);
            backtrack(nums, list, answer, visited);
            list.removeLast();
            visited[i] = false;
        }
    }

    /*
     * Solution: backtrack with HashMap
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        backtrack(nums, new ArrayList<>(), map, answer);
        return answer;
    }
    void backtrack(int[] nums, List<Integer> list, Map<Integer, Integer> map, List<List<Integer>> answer) {
        if(list.size() == nums.length) {
            answer.add(new ArrayList<>(list));
            return;
        }
        for(int num: map.keySet()) {
            int count = map.get(num);
            if(count == 0) continue;
            map.put(num, count-1);
            list.add(num);
            backtrack(nums, list, map, answer);
            list.removeLast();
            map.put(num, count);
        }
    }
}
