/*
 * 3201. Find the maximum length of valid subsequence 1
 * 
 * You are given an integer array nums.
    A subsequence sub of nums with length x is called valid if it satisfies:
        (sub[0] + sub[1]) % 2 == (sub[1] + sub[2]) % 2 == ... == (sub[x - 2] + sub[x - 1]) % 2.
    Return the length of the longest valid subsequence of nums.
    A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

    Example 1:
        Input: nums = [1,2,3,4]
        Output: 4
        Explanation:
        The longest valid subsequence is [1, 2, 3, 4].

    Example 2:
        Input: nums = [1,2,1,1,2,1,2]
        Output: 6
        Explanation:
        The longest valid subsequence is [1, 2, 1, 2, 1, 2].

    Example 3:
        Input: nums = [1,3]
        Output: 2
        Explanation:
        The longest valid subsequence is [1, 3].

    Constraints:
        2 <= nums.length <= 2 * 10^5
        1 <= nums[i] <= 10^7
 */
public class P3201_ToDo_Medium {
    /*
        Solution 1
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int maximumLength(int[] nums) {
        int max = 0;
        int[][] patterns = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        for(int[] pattern: patterns) {
            int count = 0;
            for(int n: nums) {
                if(n%2 == pattern[count%2]) {
                    count++;
                }
            }
            max= Math.max(max, count);
        }
        return max;
    }

    /*
     * Solution 2:
     * time complexity:
     * space complexity:
     */
    public int maximumLength(int[] nums) {
        int even = 0, odd = 0;
        for(int n: nums) {
            if(n%2 == 0) even++;
            else odd++;
        }

        int oddEven =0, evenOdd = 0;
        for(int n: nums) {
            if(n%2== 0) {
                oddEven = Math.max(oddEven, evenOdd+1);
            } else {
                evenOdd = Math.max(evenOdd, oddEven+1);
            }
        }

        return Math.max(Math.max(even, odd), Math.max(evenOdd, oddEven));
    }
}
