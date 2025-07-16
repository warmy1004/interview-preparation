/*
    643. Maximum Average Subarray I
    You are given an integer array nums consisting of n elements, and an integer k.
    Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.

    Example 1:
        Input: nums = [1,12,-5,-6,50,3], k = 4
        Output: 12.75000
        Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75

    Example 2:
    Input: nums = [5], k = 1
    Output: 5.0000  0
    
    Constraints:
        n == nums.length
        1 <= k <= n <= 10^5
        -104 <= nums[i] <= 10^4
 */
package Array_String;

public class P643_Easy {
    /*
     * Solution: one pass
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for(int i=0; i<k; i++) {
            sum += nums[i];
        }
        int max = sum;
        for(int i=k; i<nums.length; i++) {
            sum += nums[i] - nums[i-k];
            max = Math.max(max, sum);
        }
        return max*1.0/k;
    }

    public double findMaxAverage(int[] nums, int k) {
        double max = Double.NEGATIVE_INFINITY;
        int sum = 0;
        for(int i=0; i<nums.length; i++) {
            sum+= nums[i];
            if(i>=k-1) {
                max = Math.max(max, sum*1.0);
                sum-= nums[i-k+1];
            }
        }
        return max/k;
    }
}

/*
 * Python
 */
class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        cursum = maxsum = sum(nums[:k])
        for i in range(k, len(nums)):
            cursum += nums[i] - nums[i-k]
            maxsum = max(maxsum, cursum)
        return maxsum/k