/*
    75. Sort colors
    Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
    We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
    You must solve this problem without using the library's sort function.

    Example 1:
        Input: nums = [2,0,2,1,1,0]
        Output: [0,0,1,1,2,2]

    Example 2:
        Input: nums = [2,0,1]
        Output: [0,1,2]

    Constraints:
        n == nums.length
        1 <= n <= 300
        nums[i] is either 0, 1, or 2.
    
    Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
package Sort;

public class 75_MergeSort_ToDo_Medium {
    /*
     * Solution: Merge sort
     * time complexity: O(nlogn)
     * space complexity: O(n)
     */

     // used for keeping the sorted arrays so set as a global variable
    int[] sorted;
    public void sortColors(int[] nums) {
        sorted = new int[nums.length];
        partition(nums, 0, nums.length-1);
    }

    void partition(int[] nums, int low, int high) {
        // finishing condition of the recursion
        if(low<high) {
            int mid = (low+high)/2;
            partition(nums, low, mid);
            partition(nums, mid+1, high);
            merge(nums, low, mid, high);
        }
    }

    void merge(int[] nums, int low, int mid, int high) {
        int i=low, k = low, j=mid+1;
        while(i<=mid && j<=high) {
            if(nums[i] < nums[j]) {
                sorted[k++] = nums[i++];
            } else {
                sorted[k++] = nums[j++];
            }
        }

        for(;i<=mid; i++) {
            sorted[k++] = nums[i];
        }
        for(;j<=high; j++) {
            sorted[k++] = nums[j];
        }

        for(int idx = low; idx<=high; idx++) {
            nums[idx] = sorted[idx];
        }
    }

    /*
     * Solution: One pass
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public void sortColors(int[] nums) {
        int i = 0, curr = 0, j = nums.length-1;
        while(curr<=j) {
            if(nums[curr] == 0) {
                int temp = nums[curr];
                nums[curr++] = nums[i];
                nums[i++] = temp;
            } else if(nums[curr] == 2) {
                int temp = nums[curr];
                nums[curr] = nums[j];
                nums[j--] = temp;
            } else {
                curr++;
            }
        }
    }

    /*
     * Solution: two passes
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public void sortColors(int[] nums) {
        int p1 = 0, p2 = 0, p3 = 0;
        for(int num: nums) {
            if(num==0) p1++;
            if(num==1) p2++;
            if(num==2) p3++;
        }
        for(int i=0; i<nums.length; i++) {
            if(i<p1) {
                nums[i]=0;
            } else if(i <p1+p2) {
                nums[i]=1;
            } else {
                nums[i]=2;
            }
        }
    }
}
