/*
    238. Producgt of Array except self

    Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
    You must write an algorithm that runs in O(n) time and without using the division operation.

    Example 1:
    Input: nums = [1,2,3,4]
    Output: [24,12,8,6]

    Example 2:
    Input: nums = [-1,1,0,-3,3]
    Output: [0,0,9,0,0]
    

    Constraints:
        2 <= nums.length <= 10^5
        -30 <= nums[i] <= 30
        The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
    
    Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */

package Array_String;

public class Top150_P238_Medium {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int prefix=1; // to store a result of the product of all left values
        for(int i=0; i<n; i++) {
            result[i]=prefix;
            prefix*=nums[i];
        }

        int suffix=1; // to store a result of the product of all right values
        for(int i=n-1; i>=0; i--) {
            result[i]*=suffix;
            suffix*=nums[i];
        }
        return result;
    }
}

/*
 * time complexity : O(n)
 * space complexity: O(1)
 */