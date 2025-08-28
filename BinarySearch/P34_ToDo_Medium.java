/*
    34. Find first and last position of element in sorted array
    Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
    If target is not found in the array, return [-1, -1].
    You must write an algorithm with O(log n) runtime complexity.

    Example 1:
        Input: nums = [5,7,7,8,8,10], target = 8
        Output: [3,4]

    Example 2:
        Input: nums = [5,7,7,8,8,10], target = 6
        Output: [-1,-1]

    Example 3:
        Input: nums = [], target = 0
        Output: [-1,-1]
    
    Constraints:
        0 <= nums.length <= 10^5
        -10^9 <= nums[i] <= 10^9
        nums is a non-decreasing array.
        -10^9 <= target <= 10^9
 */
package BinarySearch;

/*
 * Find upper bound
 * - P69. sqrt(x)
 * - P162. Find Peak Element
 */

public class P34_ToDo_Medium {
    /*
     * Solution: binary search
     * time complexity: O(logn)
     * space complexity: O(1)
     */
    public int[] searchRange(int[] nums, int target) {
        int leftmost = search(nums, target, true);
        if(leftmost == -1) {
            return new int[]{-1, -1};
        }

        int rightmost = search(nums, target, false);
        return new int[]{leftmost, rightmost};
    }

    int search(int[] nums, int target, boolean isLeftmost) {
        int n = nums.length;
        int i = 0, j=n-1;
        while(i<=j) {
            int mid = i + (j-i)/2;
            if(nums[mid] == target) {
                if(isLeftmost) {
                    if(mid==i || nums[mid-1]!=target) return mid;
                    else j=mid-1;
                } else {
                    if(mid==j || nums[mid+1]!=target) return mid;
                    else i = mid+1;
                }
            } else if(nums[mid] < target) {
                i = mid+1;
            } else {
                j = mid-1;
            }
        }
        return -1;
    }

    /*
     * Solution: binary search - v2
     */
    public int[] searchRange(int[] nums, int target) {
        int[] answer = {-1, -1};
        answer[0] = search2(nums, target, true);
        answer[1] = search2(nums, target, false);
        return answer;
    }
    int search2(int[] nums, int target, boolean isLeftmost) {
        int i=0, j=nums.length-1;
        int idx = -1;
        while(i<=j) {
            int mid = (i+j)/2;
            if(nums[mid] == target) {
                idx = mid;
                if(isLeftmost) {
                    j = mid-1;
                } else {
                    i = mid+1;
                }
            } else if(nums[mid] > target) {
                j = mid-1;
            } else {
                i = mid+1;
            }
        }
        return idx;
    }

    /*
     * Solution: binary search - v3
     */
    public int[] searchRange(int[] nums, int target) {
        int leftmost = findFirst(nums, target);
        int rightmost = findLast(nums, target);
        return new int[]{leftmost, rightmost};
    }

    int findFirst(int[] nums, int target) {
        int left =0, right = nums.length-1;
        int idx = -1;
        while(left<=right) {
            int mid = (left+right)/2;
            if(nums[mid] == target) idx = mid;
            
            if(nums[mid]>=target) right = mid-1;
            else left = mid+1;
        }
        return idx;
    }
    int findLast(int[] nums, int target) {
        int left =0, right = nums.length-1;
        int idx = -1;
        while(left<=right) {
            int mid = (left+right)/2;
            if(nums[mid] == target) idx = mid;
            
            if(nums[mid]<=target) {
                left = mid+1;
            } else right = mid-1;
        }
        return idx;
    }
}
