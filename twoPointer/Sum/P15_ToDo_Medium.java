/*
    15. 3Sum
    Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
    Notice that the solution set must not contain duplicate triplets.

    Example 1:
        Input: nums = [-1,0,1,2,-1,-4]
        Output: [[-1,-1,2],[-1,0,1]]
        Explanation: 
        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
        The distinct triplets are [-1,0,1] and [-1,-1,2].
        Notice that the order of the output and the order of the triplets does not matter.

    Example 2:
        Input: nums = [0,1,1]
        Output: []
        Explanation: The only possible triplet does not sum up to 0.

    Example 3:
        Input: nums = [0,0,0]
        Output: [[0,0,0]]
        Explanation: The only possible triplet sums up to 0.
    
    Constraints:
        3 <= nums.length <= 3000
        -10^5 <= nums[i] <= 10^5
 */

package twoPointer.Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Similar Questions:
 *      1. Two Sum (Easy)
 *      16. 3Sum Closet (Medium) ** 
 *      18. 4Sum (Medium)
 *      167. Two Sum 2- Input Array Is Sorted (Medium)
 *      259. 3Sum Smaller (Medium)
 *      454. 4Sum 2 (Medium)
 *      523. Continuous subarray sum (Medium) **
 *      532. K-diff Pairs in an Array (Medium)
 *      560. Subarray Sum Equals K (Medium) **
 *      653. Two Sum IV - Input is a BST (Easy)
 *      713. Subarray product less than k (medium) **
 *      724. Find pivot index (easy) ** --> same as 1991
 *      974. subarray sums divisible by k (medium) ***
 *      1010. Pairs of songs with total durations divisible by 60 (medium) **
 *      1590. Make sum divisible by P ***
 *      1658. Minimum operations to reduce x to zero (medium)
 *      1679. Max Number of K-Sum pairs (Medium)
 *      2006. Count Number of Pairs With Absolute Difference K (Easy)
 *      2090. K radius subarray averages (medium)
 *      2367. Number of Arithmetic Triplets (Easy)
 *      2563. Count the number of fair pairs (Medium)
 *      2824. Count pairs whose sum is less than target (Easy)
 *      2845. Count of interesting subarrays (Medium) ** 
 *      3185. Count pairs that form a complete day 2 (Medium) --> same as 1010
 */

public class P15_ToDo_Medium {
    /*
     * Solution: Sort + two pointer
     * time complexity: O(n^2)
     * space complexity: O(n) 
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<List<Integer>> answer = new HashSet<>();
        for(int i=0; i<n-2; i++) {
            // Skip the duplicate elements, and reduce the running time
            //  - check if the current element is a dupliacte of the previous element and skip it if it is.
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int j = i+1;
            int k = n-1;
            while(j<k) {
                int sum = nums[i]+nums[j]+nums[k];
                if(sum==0) {
                    answer.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    // additional removing duplicates
                    while(j<k && nums[j] == nums[j+1]) j++;
                    while(j<k && nums[k] == nums[k-1]) k--;

                    j++;
                    k--;
                } else if(sum<0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return new ArrayList<>(answer);
    }

    /*
     * Solution: HashSet
     * time complexity: O(n^2)
     * space complexity: O(n) 
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<nums.length && nums[i]<=0; i++) {
            if(i==0 || nums[i-1]!=nums[i]) {
                twoSum(nums, i , result);
            }
        }
        return result;
    }
    void twoSum(int[] nums, int i, List<List<Integer>> result) {
        Set<Integer> seen = new HashSet<>();
        for(int j = i+1; j<nums.length; j++) {
            int target = -nums[i]-nums[j];
            if(seen.contains(target)) {
                result.add(Arrays.asList(nums[i], nums[j], target));
                while(j<nums.length-1 && nums[j]==nums[j+1]) {
                    j++;
                }
            }
            seen.add(nums[j]);
        }
    }

    /*
     * Solution: No-Sort
     * time complexity: O(n^2)
     * space complexity: O(n) 
     */
    public List<List<Integer>> threeSum(int[] nums) { 
        Set<List<Integer>> result = new HashSet<>();
        // Use this hashset to skip duplicates in the outer loop --> this is same as if(nums[i]==nums[i+1]) continue;
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();

        for(int i=0; i<nums.length; i++ ) {
            if(dups.add(nums[i])) {
                for(int j = i+1; j<nums.length; j++) {
                    int target = -nums[i]-nums[j];
                    if(seen.containsKey(target) && seen.get(target) == i) {
                        List<Integer> sets = Arrays.asList(nums[i], nums[j], target);
                        Collections.sort(sets);
                        result.add(sets);
                    }
                    seen.put(nums[j], i);
                }
            }
        }
        return new ArrayList(result);
    }
}
