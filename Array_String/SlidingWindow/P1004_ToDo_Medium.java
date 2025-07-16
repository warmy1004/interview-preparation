/*
    1004. max consecutive ones 3
    Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

    Example 1:
        Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
        Output: 6
        Explanation: [1,1,1,0,0,1,1,1,1,1,1]
        Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

    Example 2:
        Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
        Output: 10
        Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
        Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

    Constraints:
        1 <= nums.length <= 10^5
        nums[i] is either 0 or 1.
        0 <= k <= nums.length
 */
package Array_String;

public class P1004_ToDo_Medium {
    /*
     * Solution: sliding window
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0, right;
        for(right = 0; right<nums.length; right++) {
            if(nums[right]==0) {
                k--;
            }
            if(k<0) {
                k+= 1 - nums[left];
                left++;
            }
        }
        return right-left;
    }

    public int longestOnes(int[] nums, int k) {
        int left=0, right=0, zero=0;
        while(right<nums.length) {
            if(nums[right] == 0) {
                zero++;
            }
            right++;
            if(zero>k) {
                if(nums[left]==0) {
                    zero--;
                }
                left++;
            }
        }
        return right-left;
    }

    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0;
        while (right < nums.length) {
            k -= nums[right++] == 1 ? 0 : 1;
            if (k < 0) {
                k += nums[left++] == 1 ? 0 : 1;
            }
        }
        return right - left;
    }

    public int longestOnes(int[] nums, int k) {
        int left =0, zero = 0, max = 0;
        for(int right = 0; right<nums.length; right++) {
            if(nums[right]==0) {
                zero++;
            }
            while(zero>k) {
                if(nums[left]==0) {
                    zero--;
                }
                left++;
            }
            max = Math.max(max, right-left+1);
        }
        return max;
    }
}

/*
 * Python
 */
// case 1: because of using for-loop, right-left+1 is needed
class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        left = 0
        zero = 0
        for right in range(len(nums)):
            if nums[right]==0:
                zero+=1
            if zero > k:
                if nums[left] == 0:
                    zero-=1
                left+=1
        return right-left+1 

// case 2: same as above, but while loop is used so just right-left is enough
class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        left, right, zero = 0, 0, 0
        while right < len(nums):
            if nums[right]==0:
                zero+=1
            right+=1
            if zero>k:
                if nums[left]==0:
                    zero-=1
                left+=1
        return right-left