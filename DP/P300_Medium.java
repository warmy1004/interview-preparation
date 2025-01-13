/*
    300. longest increasing subsequence
    Given an integer array nums, return the length of the longest strictly increasing subsequence.
    Example 1:
        Input: nums = [10,9,2,5,3,7,101,18]
        Output: 4
        Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

    Example 2:
        Input: nums = [0,1,0,3,2,3]
        Output: 4

    Example 3:
        Input: nums = [7,7,7,7,7,7,7]
        Output: 1

    Constraints:
        1 <= nums.length <= 2500
        -10^4 <= nums[i] <= 10^4
    
    Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P300_Medium {
    /*
     * Solution: DP
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for(int i=1; i<nums.length; i++) {
            for(int j=0; j<i; j++) {
                if(nums[j]<nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /*
     * Solution: Intelligently build a subsequence
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    public int lengthOfLIS(int[] nums) {
        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for(int i=1; i<nums.length; i++) {
            if(nums[i] > sub.get(sub.size()-1)) {
                sub.add(nums[i]);
            } else {
                int j=0;
                while(nums[i]>sub.get(j)) {
                    j+=1;
                }
                sub.set(j, nums[i]);
            }
        }
        return sub.size();
    }

    /*
     * Solution: improve with binary search
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    public int lengthOfLIS(int[] nums) {
        List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);
        for(int i=1; i<nums.length; i++) {
            int num = nums[i];
            if(num > sub.get(sub.size()-1)) {
                sub.add(num);
            } else {
                int j = binarySearch(sub, num);
                sub.set(j, num);
            }
        }
        return sub.size();
    }

    int binarySearch(List<Integer> sub, int num) {
        int low = 0, high = sub.size()-1;
        while(low<=high) {
            int mid = (low+high)/2;
            if(sub.get(mid) == num) return mid;
            if(sub.get(mid) < num) low = mid+1;
            else high = mid-1;
        }
        return low;
    }
}
