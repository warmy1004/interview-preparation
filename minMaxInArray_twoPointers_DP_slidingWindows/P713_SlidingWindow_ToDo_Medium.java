/*
    713. Subarray Product Less Than K
    Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

    Example 1:
        Input: nums = [10,5,2,6], k = 100
        Output: 8
        Explanation: The 8 subarrays that have product less than 100 are:
        [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
        Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

    Example 2:
        Input: nums = [1,2,3], k = 0
        Output: 0
    
    Constraints:
        1 <= nums.length <= 3 * 10^4
        1 <= nums[i] <= 1000
        0 <= k <= 10^6
 */
package minMaxInArray_twoPointers_DP_slidingWindows;

public class P713_SlidingWindow_ToDo_Medium {
    /*
     * Solution: Sliding window
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // the condition is "less than k", so 1 is also included
        if(k<=1) return 0; 
        int left = 0, right = 0, product = 1, count = 0; 
        int n = nums.length;
        for(;right<n; right++){
            product *= nums[right];
            while(product>=k) {
                product /= nums[left++];
            }
            //the count of subarrays is determined by the difference right-left+1, which represents the number of subarrays that end at right and start at any element between right and left, inclusive.
            //In essence, this count encomprasses the subarray consisting solely of the current element itself, as well as all possible subarrays extending back to the left boundary of the window (left).
            // Consider an example window containing elements 3,4 and 5. If we include 6 in the window, we need to count all possible subarrays that end with 6. 
            // These subarrays can be performed by starting at any element within the current window and extending to 6. Therefore, the subarrays would be [6], [5,6], [4,5,6], [3,4,5,6]. 
            // By calculating right-left+1, we enumerate all subarrays that end witht he current element of the window (nums[right]). This ensures that we count all possible subarrays as we slide the window
            // across the array. As we can observe, adding element 6 to the window created 4 new subarray. 
            count += (right-left+1);
        }
        return count;
    }

    /*
     * Solution: Brute Force
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0, product = 1;
        for(int i=0; i<nums.length; i++) {
            for(int j=i; j<nums.length; j++) {
                product *= nums[j];
                if(product<k) {
                    count++;
                } else {
                    break;
                }
            }
            product = 1;
        }
        return count;
    }
}
