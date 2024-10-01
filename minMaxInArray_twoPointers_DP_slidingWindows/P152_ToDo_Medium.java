/*
    152. Maximum Product Subarray
    Given an integer array nums, find a subarray that has the largest product, and return the product.
    The test cases are generated so that the answer will fit in a 32-bit integer.

    Example 1:
        Input: nums = [2,3,-2,4]
        Output: 6
        Explanation: [2,3] has the largest product 6.

    Example 2:
        Input: nums = [-2,0,-1]
        Output: 0
        Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
    
    Constraints:
        1 <= nums.length <= 2 * 10^4
        -10 <= nums[i] <= 10
        The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
 */
package minMaxInArray_twoPointers_DP_slidingWindows;

/*
 * Similar Questions:
 *      238. Product of Array Except self
 *      42. Trapping Rain Water
 *      53. Maximum Subarray
 */
public class P152_ToDo_Medium {
    /*
     * Solution: Dynamic programming
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int maxProduct(int[] nums) {
        int minsofar = nums[0];
        int maxsofar = nums[0];
        int answer = nums[0];

        for(int i=1; i<nums.length; i++) {
            int minForMax = minsofar;
            minsofar = Math.min(nums[i], Math.min(minsofar*nums[i], maxsofar*nums[i]));
            maxsofar = Math.max(nums[i], Math.max(minForMax*nums[i], maxsofar*nums[i]));
            answer = Math.max(answer, maxsofar);
        }
        return answer;
    }

    /*
     * Solution: Two pointers
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int maxProduct(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int leftsum = 0;
        int rightsum= 0;
        int maxvalue = 0;
        while(left<nums.length && right >= 0) {
            int left = 0;
            int right = nums.length-1;
            int leftproduct = 1;
            int rightproduct= 1;
            int maxvalue = nums[0];
            while(left<nums.length && right >= 0) {

                // if any of leftproduct and rightproduct becomes 0 (which means, the previous num[i] value was 0), then udpate it 1 to start fresh
                if (leftproduct == 0) {
                    leftproduct = 1;
                }
                if (rightproduct == 0) {
                    rightproduct = 1;
                }
                leftproduct *= nums[left++];
                rightproduct *= nums[right--];
                maxvalue = Math.max(maxvalue, Math.max(leftproduct, rightproduct));
            }
            return maxvalue;
    }
}
