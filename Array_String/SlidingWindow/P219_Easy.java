/*
    219. Contains duplicate 2
    Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

    Example 1:
        Input: nums = [1,2,3,1], k = 3
        Output: true

    Example 2:
        Input: nums = [1,0,1,1], k = 1
        Output: true

    Example 3:
        Input: nums = [1,2,3,1,2,3], k = 2
        Output: false
    
    Constraints:
        1 <= nums.length <= 10^5
        -10^9 <= nums[i] <= 10^9
        0 <= k <= 10^5
 */
package Array_String.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P219_Easy {
    /*
     * Solution: using hashmap and linear search
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            if(map.containsKey(nums[i]) && Math.abs(i-map.get(nums[i]))<=k) {
                return true;
            } else {
                map.put(nums[i],i);
            }
        }
        return false;
    }

    /*
     * Solution: Hash set
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
            
            if(set.size()>k) {
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
}
