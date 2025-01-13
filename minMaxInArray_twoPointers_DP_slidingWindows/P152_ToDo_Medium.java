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

 /*
  * Since we have to find the contiguous subarray having maximum product then your approach should be combination of following three cases:
  *     case 1: all the elements are positive --> Then, your answer will be product of all the elements in the array
  *     case 2: Array have positive and negative elements both -->
  *         1. if the number of negative elements is EVEN, then again your answer will be complete array because on multiplying all the negative numbers it will become positive.
  *         2. if the number of negative elements is ODD, then you have to remove just one negative element and for that you need to check your subarrays to get the max product. 
  *     case 3: Array also contains 0 --> Then there will be not much difference. it's just that your array will be devided into subarray around that 0. 
  *                 What you have to so is just as soon as your product becomes 0 make it 1 for the enxt iteration, now you will be searching new subarray and previous max will already be updated.
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
            //nested Math.min/Math.max에서 nums[i]를 각각의 max와 min값에 곱한 후 비교해야 하는 것은, -값을 제대로 처리하기 위해서
            //subarray의 max product 를 구하는 것이기 때문에, minsofar은 비교 대상군에 해당되지 않는다 (subarray가 아니므로)
            minsofar = Math.min(nums[i], Math.min(minsofar*nums[i], maxsofar*nums[i]));
            maxsofar = Math.max(nums[i], Math.max(minForMax*nums[i], maxsofar*nums[i]));
            answer = Math.max(answer, maxsofar);
        }
        return answer;
    }

    /*
     * Solution: Two pointers - Kadane's algorithm
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int maxProduct(int[] nums) {
  
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

    /*
     * Solution: Two pointers - another version
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int product = 1;
        for(int num: nums) {
            product *= num;
            max = Math.max(max, product);
            if(product == 0) {
                product = 1;
            }
        }

        product = 1;
        for(int i=nums.length-1; i>=0; i--) {
            product *= nums[i];
            max = Math.max(max, product);
            if(product ==0) {
                product = 1;
            }
        }
        return max;
    }

    public int maxProduct(int[] nums) {
        int fromLeft = 1, fromRight =1, answer = nums[0];
        int n = nums.length;

        for(int i=0; i<n; i++) {
            if(fromLeft == 0) fromLeft =1;
            if(fromRight ==0) fromRight =1;

            fromLeft *= nums[i];
            fromRight *= nums[n-1-i];

            answer = Math.max(answer, Math.max(fromLeft, fromRight));
        }
        return answer;
    }
}
