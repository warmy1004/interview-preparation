/*
    162. Find peak element
    A peak element is an element that is strictly greater than its neighbors.
    Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
    You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
    You must write an algorithm that runs in O(log n) time.

    Example 1:
        Input: nums = [1,2,3,1]
        Output: 2
        Explanation: 3 is a peak element and your function should return the index number 2.

    Example 2:
        Input: nums = [1,2,1,3,5,6,4]
        Output: 5
        Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
    
    Constraints:
        1 <= nums.length <= 1000
        -2^31 <= nums[i] <= 2^31 - 1
        nums[i] != nums[i + 1] for all valid i.
 */
package BinarySearch;

public class P162_Must_ToDo_Medium {
    /*
     * Solution: iterative binary search 1
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length-1;
        while(left<right) {
            int mid = (left+right)/2;
            if(nums[mid] > nums[mid+1]) {
                right = mid;
            } else {
                left = mid+1;
            }
        }
        return left;
    }

    /*
     * Solution: iterative binary search 2
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length; 
        if(n==1 || nums[0] > nums[1]) return 0;
        if(nums[n-2]<nums[n-1]) return n-1;

        int left = 1, right = n-2;
        while(left<right) {
            int mid = (left+right)/2;
            if(nums[mid-1]<nums[mid] && nums[mid]>nums[mid+1]) return mid;
            else if (nums[mid-1]>nums[mid]) right = mid;
            else if(nums[mid] < nums[mid+1]) left = mid+1;
        }
        return left;
    }

    /*
     * Solution: recursive binary search 1
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length-1);
    }
    int search(int[] nums, int start, int end) {
        if(start==end) return start;
        int mid = (start+end)/2;
        if(nums[mid] > nums[mid+1]) return search(nums, start, mid);
        return search(nums, mid+1, end);
    }
}
