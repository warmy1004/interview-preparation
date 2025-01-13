/*
    414. Third maximum number
    Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.

    Example 1:
        Input: nums = [3,2,1]
        Output: 1
        Explanation:
            The first distinct maximum is 3.
            The second distinct maximum is 2.
            The third distinct maximum is 1.

    Example 2:
        Input: nums = [1,2]
        Output: 2
        Explanation:
            The first distinct maximum is 2.
            The second distinct maximum is 1.
            The third distinct maximum does not exist, so the maximum (2) is returned instead.

    Example 3:
        Input: nums = [2,2,3,1]
        Output: 1
        Explanation:
            The first distinct maximum is 3.
            The second distinct maximum is 2 (both 2's are counted together since they have the same value).
            The third distinct maximum is 1.

    Constraints:
        1 <= nums.length <= 10^4
        -2^31 <= nums[i] <= 2^31 - 1

    Follow up: Can you find an O(n) solution?
 */
package Array_String.Array_Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

public class P414_Easy {
    /*
     * Solution: using built-in sort
     * time complexity: O(nlogn)
     * space complexity: O(1)
     */
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int count = 1;
        for(int i=n-2; i>=0; i--) {
            if(nums[i]!=nums[i+1]) count++;
            if(count == 3) return nums[i];
        }
        return nums[n-1];
    }

    /*
     * Solution: priorityQueue - heap
     * time complexity: O(nlog3) = O(n)
     *      generally, heap takes O(nlogk), but here k==3, so it takes O(n)
     *      we iterate on nums array and can push each element in the min heap
     *      time taken to push and pop elements from min heap depends on number of elements in the heap (or height of the heap), and as here the heap will have at most three elemtns in it, those operations are considered constant time ops.
     * space complexity: O(3) = O(1)
     *      minHeap only stores at most three elements, so it is considered as constant space usage.
     */
    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num: nums) {
            if(!minHeap.contains(num)) {
                minHeap.add(num);
            }
            if(minHeap.size()>3) {
                minHeap.remove();
            }
        }
        if(minHeap.size() == 2) {
            minHeap.remove();
        }
        return minHeap.peek();
    }

    /*
     * Solution: pointers
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int thirdMax(int[] nums) {
        // Because of the nums[i] range (-2^31 and 2^31-1) integer is not enough to get the answer with -2147483648. So, using Long instead.
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;
        for(int num: nums) {
            // ignore duplicates
            if(max1==num || max2==num || max3 == num) continue;
            if(max1<=num) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if(max2 <=num) {
                max3 = max2;
                max2 = num;
            } else if(max3 <= num) {
                max3 = num;
            }
        }
        if(max3 == Long.MIN_VALUE) return (int)max1;
        return (int)max3;
    }
}
