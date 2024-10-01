/*
    53. Maximum Subarray
    Given an integer array nums, find the subarray with the largest sum, and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Example 2:
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 
Constraints:
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
 

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
package minMaxInArray_twoPointers_DP_slidingWindows;

public class P53_Medium {
    /*
     * Solution: Dynamic programming - kadane's algorithm
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int maxSubArray(int[] nums) {
        int answer = nums[0];
        int curr = nums[0];
        for(int i=1; i<nums.length; i++) {
            curr = Math.max(nums[i], curr+nums[i]);
            answer = Math.max(answer, curr);
        }
        return answer;
    }

    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int maxvalue = nums[0];
        for (int i=1; i<nums.length; i++) {
            sum += nums[i];
            maxvalue = maxvalue < sum? sum: maxvalue;
            if (sum<0) sum = 0;
        }
        return maxvalue;
    }

    /*
     * Solution: Divide and Conquer
     * time complexity: O(NlogN)
     *      On our first call to recursive(), we use for loops to visit every element of nums. Then, we split the array in half and call recursive() with each half.
     *      both those calls will then iterate through every element in that half, which combined is every element of nums again. Then, both those halves will be split in half 
     *      and 4 more calls to recursive() will happen, each with a quater of nums. As you can see, every time the array is split, we still need to handle every element of the original input nums.
     *      we have to do this logN times since that's how many times an array can be split in half. 
     * space complexity: O(logN)
     *      The extra space we use relative to input size is soley occupied by the recursion stack. 
     *      Each time, the array gets split in half, another call of recursive() will be added to the recursion stack, until calls start to get resolved by the base case
     *      - remember, the base case happens at an empty array, which occurs after logN calls.
     */
    public int maxSubArray(int[] nums) {
        return recursive(nums, 0, nums.length-1);
    }

    private int recursive(int[] nums, int left, int right) {
        if (left > right) {
            return Integer.MIN_VALUE;
        }
        int mid = (left + right) / 2;
        int curr = 0;
        int maxleft = 0;
        int maxright = 0;
        for (int i = mid - 1; i >= left; i--) {
            curr += nums[i];
            maxleft = Math.max(maxleft, curr);
        }

        curr = 0;
        for (int j = mid + 1; j <=right; j++) {
            curr += nums[j];
            maxright = Math.max(maxright, curr);
        }

        int maxtotal = maxleft + nums[mid] + maxright;
        int lefthalf = recursive(nums, left, mid - 1);
        int righthalf = recursive(nums, mid + 1, right);
        return Math.max(maxtotal, Math.max(lefthalf, righthalf));
    }
}
