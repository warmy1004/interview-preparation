/*
    128. Longest consecutive sequence
    Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
    You must write an algorithm that runs in O(n) time.

    Example 1:
        Input: nums = [100,4,200,1,3,2]
        Output: 4
        Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

    Example 2:
        Input: nums = [0,3,7,2,5,8,4,6,0,1]
        Output: 9
    
    Constraints:
        0 <= nums.length <= 10^5
        -10^9 <= nums[i] <= 10^9
 */

package Array_String;

import java.util.HashSet;
import java.util.Set;

public class P128_ToDo_Medium {

    /*
     * Solution: HashSet    
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n: nums) {
            set.add(n);
        }

        int total = 0;
        for(int n: nums) {
            if(!set.contains(n-1)) {
                int curr = n;
                int count = 1;

                while(set.contains(curr+1)) {
                    count++;
                    curr++;
                }
                total = Math.max(total, count);
            }
        }
        return total;
    }

    /*
     * Solution: Sort
     * time complexity: O(nlogn)
     * space complexity: O(1)
     */
    public int longestConsecutive(int[] nums) {
        if(nums.length <= 1){
            return nums.length;
        }
        Arrays.sort(nums);
        int max = 0;
        int count = 1;
        for(int i=0; i<nums.length-1; i++){
            if(nums[i] + 1 == nums[i+1]){
                count++;
            }
            else if(nums[i] == nums[i+1]){
            }
            else{
                count = 1;
            }
            max = Math.max(count, max);
        }
        return max;
    }
}
