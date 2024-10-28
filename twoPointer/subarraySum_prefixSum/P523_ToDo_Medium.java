/*
    523. continuous subarray sum
    Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
    A good subarray is a subarray where:
        its length is at least two, and
        the sum of the elements of the subarray is a multiple of k.
    Note that:
        A subarray is a contiguous part of the array.
        An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.

    Example 1:
        Input: nums = [23,2,4,6,7], k = 6
        Output: true
        Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

    Example 2:
        Input: nums = [23,2,6,4,7], k = 6
        Output: true
        Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
        42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

    Example 3:
        Input: nums = [23,2,6,4,7], k = 13
        Output: false
    
    Constraints:
        1 <= nums.length <= 10^5
        0 <= nums[i] <= 10&9
        0 <= sum(nums[i]) <= 2^31 - 1
        1 <= k <= 2^31 - 1
 */
package twoPointer.subarraySum_prefixSum;

import java.util.HashMap;
import java.util.Map;

public class P523_ToDo_Medium {
    /*
     * Solution: hashmap
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        //initialization
        map.put(0, -1);

        int sum = 0;
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            
            int remainder = k==0? k : sum%k;
            if(map.containsKey(remainder)) {
                // ensure that the size of subarray is at least 2
                if (i-map.get(remainder) > 1) {
                    return true;
                }
            } else {
                // mark the value of remainder with the current index
                map.put(remainder, i);
            }
        }
        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            int remainder = sum % k;
            if(i - map.getOrDefault(remainder, i) > 1) return true;
            map.putIfAbsent(remainder, i);
        }
        return false;
    }
}
