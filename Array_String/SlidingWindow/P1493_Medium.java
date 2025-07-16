/*
    1493. Longest subarray of 1's after deleting one element
    Given a binary array nums, you should delete one element from it.
    Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

    Example 1:
        Input: nums = [1,1,0,1]
        Output: 3
        Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.

    Example 2:
        Input: nums = [0,1,1,1,0,1,1,0,1]
        Output: 5
        Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].

    Example 3:
        Input: nums = [1,1,1]
        Output: 2
        Explanation: You must delete one element.
    
    Constraints:
        1 <= nums.length <= 10^5
        nums[i] is either 0 or 1.
 */
package Array_String.SlidingWindow;

public class P1493_Medium {
    /*
     * Solution: sliding window
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int longestSubarray(int[] nums) {
        int zero = 1, left = 0, max = 0;
        for(int right=0; right<nums.length; right++) {
            if(nums[right] == 0) {
                zero--;
            }
            while(zero<0) {
                if(nums[left]==0) zero++;
                left++;
            }
            max = Math.max(max, right-left);
        }
        return max;
    }

    public int longestSubarray(int[] nums) {
        int left = 0, zero = 0, max = 0;
        for(int right=0; right<nums.length; right++) {
            if(nums[right]==0) zero++;
            while(zero>1) {
                if(nums[left]==0) zero--;
                left++;
            }
            max = Math.max(max, right-left);
        }
        return max;
    }

    public int longestSubarray(int[] nums) {
        int prev_ones = 0;
        int curr_ones = 0;
        int max = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] == 1) {
                curr_ones ++;
                max = Math.max(max, curr_ones+prev_ones);
            } else {
                prev_ones = curr_ones;
                curr_ones = 0;
            }
        }
        return max == nums.length ? max-1: max;
    }
}
