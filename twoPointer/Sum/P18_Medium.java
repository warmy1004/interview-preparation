/*
    18. 4Sum
    Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
        0 <= a, b, c, d < n
        a, b, c, and d are distinct.
        nums[a] + nums[b] + nums[c] + nums[d] == target
    You may return the answer in any order.

    Example 1:
    Input: nums = [1,0,-1,0,-2,2], target = 0
    Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

    Example 2:
    Input: nums = [2,2,2,2,2], target = 8
    Output: [[2,2,2,2]]
    
    Constraints:
        1 <= nums.length <= 200
        -10^9 <= nums[i] <= 10^9
        -10^9 <= target <= 10^9
 */
package twoPointer.Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P18_Medium {
    /*
     * Solution: Two pointers
     * time complexity: O(n^3)
     * space complexity: O(n)
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        for(int i=0; i<nums.length-3; i++) {
            for(int j=i+1; j<nums.length-2; j++) {
                long remaining = target - ( (long)nums[i] + (long)nums[j]);
                int left = j+1, right = nums.length-1;
                while(left<right) {
                    long sum = (long)nums[left] + (long)nums[right];
                    if(sum == remaining) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while(left<right && nums[left] == nums[left+1]) left++;
                        while(left<right && nums[right] == nums[right-1]) right--;
                        left++;
                        right--;
                    } else if (sum < remaining) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }
}
