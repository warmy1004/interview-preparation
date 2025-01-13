/*
    209. Minimum size subarray sum
    Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

    Example 1:
        Input: target = 7, nums = [2,3,1,2,4,3]
        Output: 2
        Explanation: The subarray [4,3] has the minimal length under the problem constraint.

    Example 2:
        Input: target = 4, nums = [1,4,4]
        Output: 1

    Example 3:
        Input: target = 11, nums = [1,1,1,1,1,1,1,1]
        Output: 0
    
    Constraints:
        1 <= target <= 10^9
        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^4
 
    Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */
package Array_String.SlidingWindow;

import java.awt.Window;

public class P209_ToDo_Medium {
    /*
     * Solution: sliding window
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for(int right = 0; right<nums.length; right++) {
            sum += nums[right];
            while(sum >= target) {
                minLen = Math.min(minLen, right - left +1);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    /*
     * Solution: binary search 1
     * time complexity: O(nlogn)
     * space complexity: O(n)
     */
    int minLen = Integer.MAX_VALUE;
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        
        int start = 1;
        int end = n;
        
        while(start<=end) {
            int mid = (start+end)/2;
            
            if(findWindowSize(nums, target, mid)) {
                end = mid-1;
            } else {
                start = mid +1;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    boolean findWindowSize(int[] nums, int target, int mid) {
        int sum = 0;
        int i;
        for(i=0; i<mid; i++) {
            sum += nums[i];
            if(sum>=target) {
                minLen = Math.min(minLen, mid);
                return true;
            }
        }
        while(i<nums.length) {
            int startIndex = i-mid;
            sum -= nums[startIndex];
            sum += nums[i];
            if(sum>=target) {
                minLen = Math.min(minLen, mid);
                return true;
            }
            i++;
        }
        return false;
    }

    /*
     * Solution: Binary search 2
     * time complexity: O(nlogn)
     * space compelxity: O(1)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int start = 0, end = nums.length;
        int result = 0;

        while(start<=end) {
            int mid = (start + end)/2;
            if(hasTarget(nums, target, mid)) {
                result = mid;
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return result;
    }

    boolean hasTarget(int[] nums, int target, int mid) {
        int n = nums.length;
        int i=0, sum =0;
        while(i<mid && i<n) {
            sum += nums[i++];
        }
        if(sum >= target) {
            return true;
        }
        while(i<n) {
            sum += nums[i];
            sum-=nums[i-mid];
            if(sum>= target) return true;
            i++;
        }
        return false;
    }
}
