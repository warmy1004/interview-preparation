/*
    137. single number 2
    Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
    You must implement a solution with a linear runtime complexity and use only constant extra space.

    Example 1:
        Input: nums = [2,2,3,2]
        Output: 3

    Example 2:
        Input: nums = [0,1,0,1,0,1,99]
        Output: 99
    
    Constraints:
        1 <= nums.length <= 3 * 10^4
        -2^31 <= nums[i] <= 2^31 - 1
        Each element in nums appears exactly three times except for one element which appears once.
 */

package BitManipulation;

import java.util.HashSet;
import java.util.Set;

public class P137_Medium {
    /*
     * Solution: using set
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int singleNumber(int[] nums) {
        Set<Long> set = new HashSet<>();
        long setSum = 0, totalSum = 0;
        for(int num: nums) {
            if(!set.contains((long)num)) {
                setSum+= num;
                set.add((long)num);
            }
            totalSum += num;
        }
        return (int)((3*setSum-totalSum)/2);
    }

    public int singleNumber(int[] nums) {
        Set<Long> set = new HashSet<>();
        long setSum = 0, totalSum = 0;
        for(int num: nums) {
            set.add((long)num);
            totalSum+= num;
        }

        for(long num: set) {
            setSum+= num;
        }

        return (int)((3*setSum-totalSum)/2);
    }
}
