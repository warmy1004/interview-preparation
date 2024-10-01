/*
    628. Maximum product of three number
    Given an integer array nums, find three numbers whose product is maximum and return the maximum product.

    Example 1:
        Input: nums = [1,2,3]
        Output: 6

    Example 2:
        Input: nums = [1,2,3,4]
        Output: 24

    Example 3:
        Input: nums = [-1,-2,-3]
        Output: -6

    Constraints:
        3 <= nums.length <= 10^4
        -1000 <= nums[i] <= 1000
    */
package minMaxInArray_twoPointers_DP_slidingWindows;

public class P628_Easy {
    /*
     * Solution: using built in sort
     * time complexity: O(NlogN)
     * space complexity: O(1)
     */
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return Math.max(nums[0]*num[1]*nums[n-1], nums[n-3]*nums[n-2]*nums[n-1]);
    }

    /*
     * Solution: one loop
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        for(int n : nums) {
            if(n<min1) {
                min2= min1;
                min1=n;
            } else if(n<min2) {
                min2= n;
            }

            if(n>max1) {
                max3=max2;
                max2= max1;
                max1=n;
            } else if(n>max2) {
                max3=max2;
                max2 = n;
            } else if(n>max3) {
                max3= n;
            }
        }
        return Math.max(min1*min2*max1, max1*max2*max3);
    }
}
