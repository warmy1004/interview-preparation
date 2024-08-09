/*
    80. Remove Duplicates from sorted Array 2

    Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
    Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
    Return k after placing the final result in the first k slots of nums.
    Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

    Custom Judge:
    The judge will test your solution with the following code:
        int[] nums = [...]; // Input array
        int[] expectedNums = [...]; // The expected answer with correct length

        int k = removeDuplicates(nums); // Calls your implementation

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    If all assertions pass, then your solution will be accepted.

    Example 1:
        Input: nums = [1,1,1,2,2,3]
        Output: 5, nums = [1,1,2,2,3,_]
        Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
        It does not matter what you leave beyond the returned k (hence they are underscores).

    Example 2:
        Input: nums = [0,0,1,1,1,1,2,3,3]
        Output: 7, nums = [0,0,1,1,2,3,3,_,_]
        Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
        It does not matter what you leave beyond the returned k (hence they are underscores).
    

    Constraints:
        1 <= nums.length <= 3 * 104
        -104 <= nums[i] <= 104
        nums is sorted in non-decreasing order.
 */

package Array_String;

public class Problem80_ToDo {
    public int removeDuplicates(int[] nums) {

        /*
         * Solution 1
         * time complexity: O(n)
         * space complexity: O(1)
         */
        // int count = 1, k = 1;
        // for(int i=1; i<nums.length; i++) {
        //     if(nums[i-1]==nums[i]) {
        //         count++;
        //     } else {
        //         count=1;
        //     }

        //     if(count<=2) {
        //         nums[k++] = nums[i];
        //     }
        // }
        // return k;

        /*
         * Solution 2
         * 
         * Approach:
         * Use two pointers, one for the current element, another for the previous two elements.
         */
        // int len = nums.length;
        // if(len<2) {
        //     return len;
        // }
        // int k = 2;
        // for(int i=2; i<len; i++) {
        //     if(nums[i]!=nums[k-2]) {
        //         nums[k++] = nums[i];
        //     }
        // }
        // return k;

        /*
         * Solution 3
         */
        // int k = 0;
        // for(int n: nums) {
        //     if(k<2 || n > nums[k-2]) {
        //         nums[k++]=n;
        //     }
        // }
        // return k;

        /*
         * Solution 4
         * 
         * Approach:
         * The approach employs a two-pointer strategy. The variable j is used to keep track of the current position in the modified array where elements are being stored without violating the constraint.
         * The loop iterates through the array, and for each element, it checks whether it is the same as the element two position behind the current j. 
         * If it is, it means there are already two occurrences of this element in the modified array, and we should skip adding another one to adhere to the constraint.
         * Otherwise, the element is added to the modified array at position j, and j is incremented.
         */
        int j = 1; 
        for(int i=1; i<nums.length; i++) {
            if(j<2 || nums[i]!=nums[j-2]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}

/*
 * Generic solution for when K repetitions are allowed - same as Solution 2
    public int removeDuplicates(int[] nums, int k) {
        int n = nums.length;
        if(n < k+1) return n;

        int j = k;
        for(int i=k; i<n; i++) {
            if(nums[i] != nums[j-k]) {
                nums[j++] = nums[i];
            }
        }
    }
 */