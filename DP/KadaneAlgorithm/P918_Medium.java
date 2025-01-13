/*
    918. Maximum sum circular subarray
    Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
    A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
    A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

    Example 1:
        Input: nums = [1,-2,3,-2]
        Output: 3
        Explanation: Subarray [3] has maximum sum 3.

    Example 2:
        Input: nums = [5,-3,5]
        Output: 10
        Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

    Example 3:
        Input: nums = [-3,-2,-3]
        Output: -2
        Explanation: Subarray [-2] has maximum sum -2.
    
    Constraints:
        n == nums.length
        1 <= n <= 3 * 10^4
        -3 * 10^4 <= nums[i] <= 3 * 10^4
 */
package DP.KadaneAlgorithm;

public class P918_Medium {
    /*
     * Solution: calculate the minimum subarray
     * 
     * Approach:
     *      The 'special sum' would be the combination of a prefix sum and a suffix sum. A prefix is a subarray that starts at the first element of the array and a suffix is a subarray that ends at the final element of the array. 
     *      The 'special sum' would involve a prefix and suffix that do not overlap.
     *      We can think about it as the sum of all elements, minus a subarray in the middle. Kadane's algorithm with min() will give us the minimum subarray. Then, we can just subtract the minimum subarray from the total sum to find the 'special sum'.
     *      One case need to consider; what if the minimum subarray contains all elements, such as in the case where every element is negative. In that case, the sprecial sum would represent an empty array, which is invalid because the problem explicitly states 
     *      that we need a non-empty subarray. If we find that the minimum subarray is equal to the total sum, then we need to ignore the special sum and just return the normal sum.  
     * 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int maxSubarraySumCircular(int[] nums) {
        int currMin = 0;
        int currMax = 0;
        int totalMin = nums[0];
        int totalMax = nums[0];
        int totalSum = 0;
        for(int num: nums) {
            // normal kadane's algorithm for finding a max
            currMax = Math.max(currMax+num, num);
            totalMax = Math.max(currMax, totalMax);
            
            // normal kadane's algorithm but for finding a min
            currMin = Math.min(currMin+num, num);
            totalMin = Math.min(currMin, totalMin);

            totalSum += num;
        }

        if(totalSum == totalMin) return totalMax;
        return Math.max(totalMax, totalSum - totalMin);
    }

    int total;
    public int maxSubarraySumCircular(int[] nums) {
        int max = kadane(nums, true);
        int min = kadane(nums, false);

        // if the maximum is greater than 0, return the maximum of max or total-min
        // max<0 means all elements are negative
        return max>0 ? Math.max(max, total-min) : max;
    }
    int kadane(int[] nums, boolean isMax) {
        total = 0;
        int curr = 0;
        int sum = nums[0];
        for(int num: nums) {
            total += num;
            if(isMax) {
                curr = Math.max(curr+num, num);
                sum = Math.max(curr, sum);
            } else {
                curr = Math.min(curr+num, num);
                sum = Math.min(curr, sum);
            }
        }
        return sum;
    }

    /*
     * Solution: Emunerate prefix and suffix sums
     * 
     * Approach:
     *      the idea is to enumerate a prefix with its sum and add the maximum suffix sum that starts after the prefix so that the prefix and suffix don't overlap
     * 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] rightMax = new int[n];
        rightMax[n-1] = nums[n-1];
        int suffixSum = nums[n-1];

        for(int i=n-2; i>=0; i--) {
            suffixSum += nums[i];
            rightMax[i] = Math.max(rightMax[i+1], suffixSum);
        }

        int specialSum = nums[0];
        int maxSum = nums[0];
        int currMax = 0;
        for(int i=0, prefixSum = 0; i<n; i++) {
            currMax = Math.max(nums[i], currMax+nums[i]);
            maxSum = Math.max(currMax, maxSum);
            prefixSum += nums[i];
            if(i+1<n) {
                specialSum = Math.max(specialSum, prefixSum + rightMax[i+1]);
            }
        }
        return Math.max(maxSum, specialSum);
    }
}
