/*
    1608. Special Array With X elements greater than or equal x

    You are given an array nums of non-negative integers. nums is considered special if there exists a number x such that there are exactly x numbers in nums that are greater than or equal to x.
    Notice that x does not have to be an element in nums.
    Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the value for x is unique.

    Example 1:
        Input: nums = [3,5]
        Output: 2
        Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.

    Example 2:
        Input: nums = [0,0]
        Output: -1
        Explanation: No numbers fit the criteria for x.
        If x = 0, there should be 0 numbers >= x, but there are 2.
        If x = 1, there should be 1 number >= x, but there are 0.
        If x = 2, there should be 2 numbers >= x, but there are 0.
        x cannot be greater since there are only 2 numbers in nums.

    Example 3:
        Input: nums = [0,4,3,0,4]
        Output: 3
        Explanation: There are 3 values that are greater than or equal to 3.
    
    Constraints:
        1 <= nums.length <= 100
        0 <= nums[i] <= 1000
 */
package BinarySearch;

import java.util.Arrays;

public class P1608_ToDo_Easy {
    /*
     * Solution: Counting sort + prefix sum
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int specialArray(int[] nums) {
        int n = nums.length;
        int[] counts = new int[n+1];
        for(int i=0; i<n; i++) {
            counts[Math.min(nums[i], n)]++;
        }

        int total = 0;
        for(int i=n; i>0; i--) {
            total += counts[i];
            if(total == i) {
                return i;
            }
        }
        return -1;
    }
    
    /*
     * Solution: Sorting
     * time complexity: O(nlogn), sorting takes O(nlogn) and binary search takes O(nlogn)
     * space complexity: O(n) for python or O(nlogn) for java
     * 
     * the value of x can't be more than the length of the array nums. This is because for an integer to be the answer, there must be at least that number of integers in nums. 
     * If we assume x to be nums.length+1, there must be precisely nums.length+1 integers in the array that are greater than or equal to x, but there are only nums.length integers in nums.
     * Also, the minimum possible value for x is 1. This is because if x equals 0, the array nums must be empty, but the constraints guarantee that nums has at least 1 element.
     * 
     * We can efficiently find the number of integers that are greater than or equal to an element if the array is sorted.
     * Then, we can use binary search to find the first index where the value is greater than or equal to the element. 
     * All elements after that index would also be greater or equal to the element. 
     * In each step of the binary search, we will check the mid index in the current range of nums. If this mid element is greater than val, it implies this can be our answer.
     * Thus, we will store the index and move on to the left half of the current range of nums to check if there's a better answer. 
     * If the mid element is smaller than val, we will move on to the right half. 
     */
    public int specialArray_sorting(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for(int i=1; i<=n; i++) {
            int k = countNumber(nums, i);
            if(n-k == i) {
                return i;
            }
        }
        return -1;
    }
    private int countNumber(int[] nums, int i) {
        int start = 0;
        int end = nums.length-1;
        int index = nums.length;
        while(start<=end) {
            int mid = start + (end -start)/2;
            if(nums[mid]>=i) {
                index = mid;
                end = mid-1;
            } else {
                start = mid +1;
            }
        }
        return index;
    }
}
