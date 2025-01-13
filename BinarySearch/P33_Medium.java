/*
    33. Search in Rotated Sorted Array
    There is an integer array nums sorted in ascending order (with distinct values).
    Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
    Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
    You must write an algorithm with O(log n) runtime complexity.

    Example 1:
        Input: nums = [4,5,6,7,0,1,2], target = 0
        Output: 4

    Example 2:
        Input: nums = [4,5,6,7,0,1,2], target = 3
        Output: -1

    Example 3:
        Input: nums = [1], target = 0
        Output: -1
    
    Constraints:
        1 <= nums.length <= 5000
        -10^4 <= nums[i] <= 10^4
        All values of nums are unique.
        nums is an ascending array that is possibly rotated.
        -10^4 <= target <= 10^4
 */
package BinarySearch;

public class P33_Medium {
    /*
     * Solution: two binary search - my solution
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left<right) {
            int mid = left + (right-left)/2;
            if(nums[mid] > nums[right]) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        int pivot = left;
        if(nums[pivot] == target) return pivot;
        else if(nums[pivot] < target && target <= nums[n-1]) {
            left = pivot+1;
            right = n-1;
        } else {
            left = 0;
            right = pivot-1;
        }

        while(left<=right) {
            int mid = left + (right-left)/2;
            if(nums[mid]==target) return mid;
            else if(nums[mid]>target) right = mid-1;
            else left = mid+1;
        }
        return -1;
    }

    /*
     * Solution: two binary search - without shift
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left<=right) {
            int mid = left + (right-left)/2;
            if(nums[mid] > nums[n-1]) {
                // pivot is located to the right of mid
                left = mid+1;
            } else {
                // pivot could be either at mid or to the left of mid
                right = mid-1;
            }
        }

        int answer = binarySearch(nums, 0, left-1, target);
        if(answer != -1) return answer;
        return binarySearch(nums, left, n-1, target);
    }
    int binarySearch(int[] nums, int left, int right, int target) {
        while(left<=right) {
            int mid = left + (right-left)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid]>target) right = mid-1;
            else left = mid+1;
        }
        return -1;
    }

    /*
     * Solution: two binary search - shift
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left=0, right = n-1;
        while(left<=right) {
            int mid = left + (right-left)/2;
            if(nums[mid]>nums[n-1]) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return shiftedBinarySearch(nums, left, target);
    }
    // Shift elements in a circular manner, with the pivot element at index 0
    int shiftedBinarySearch(int[] nums, int pivot, int target) {
        int n = nums.length;
        // if we were to rotate it to the right by n-pivot steps (taking the modulus of n into account), it would return to its original position, index 0.
        int shift = n-pivot;
        int left = (pivot+shift)%n;
        int right = (pivot-1+shift)%n;
        while(left<=right) {
            int mid = (left+right)/2;
            // Remembering that we had to shift every element to the right by n-pivot steps to reach the sorted version of nums, 
            // we now need to shift the index in the sorted nums to the left by n-pivot steps to find its corresponding index, i,
            // in the original nums. This gives us i-(n-pivot) (taking the modulus of n into account)
            int idx = (mid-shift+n)%n;
            if(nums[idx] == target) return idx;
            else if(nums[idx] > target) right = mid-1;
            else left = mid+1; 
        }
        return -1;
    }

    /*
     * Solution: one binary search 
     * time complexity:
     * space complexity:
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left<=right) {
            int mid = left + (right-left)/2;
            if(nums[mid] == target) return mid;
            else if (nums[mid]>= nums[left]) {
                if(nums[left]<=target && target<nums[mid]) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else {
                if(nums[mid]<target && target<=nums[right]) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            }
        }
        return -1;
    }
}
