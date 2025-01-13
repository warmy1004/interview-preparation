/*
    697. Degree of an array
    Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
    Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

    Example 1:
    Input: nums = [1,2,2,3,1]
    Output: 2
    Explanation: 
        The input array has a degree of 2 because both elements 1 and 2 appear twice.
        Of the subarrays that have the same degree:
        [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
        The shortest length is 2. So return 2.

    Example 2:
    Input: nums = [1,2,2,3,1,4,2]
    Output: 6
    Explanation: 
        The degree is 3 because the element 2 is repeated 3 times.
        So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
    
    Constraints:
        nums.length will be between 1 and 50,000.
        nums[i] will be an integer between 0 and 49,999.
 */
package Array_String;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class P697_Easy {
    /*
     * Solution: HashMap
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0)+1);
            if(left.get(nums[i]) == null) left.put(nums[i], i);
            right.put(nums[i], i);
        }

        int minLen = Integer.MAX_VALUE;
        int degree = Collections.max(freq.values());
        for(int key: freq.keySet()) {
            if(freq.get(key) == degree) {
                minLen = Math.min(minLen, right.get(key)-left.get(key)+1);
            }
        }
        return minLen;
    }
    
    /*
     * Solution: HashMap v2
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int degree = 0;
        int minLen = 0;
        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            if(!map.containsKey(num)) {
                map.put(num, new int[]{i, i, 1});
                if(i==0) {
                    degree = 1;
                    minLen = 1;
                }
            } else {
                int[] data = map.get(num);
                data[1] = i;
                data[2]++;
                int currLen = i-data[0]+1;
                if(degree < data[2]) {
                    degree = data[2];
                    minLen = currLen;
                }
                if(degree == data[2]) {
                    minLen = Math.min(minLen, currLen);
                }
            }
        }
        return minLen;
    }

    /*
     * Solution: HashMap and arrayList -- very slow
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int num: nums) {
            list.add(num);
            freq.put(num, freq.getOrDefault(num, 0)+1);
        }

        int degree = Collections.max(freq.values());
        int minLen = Integer.MAX_VALUE;
        for(int key : freq.keySet()) {
            if(freq.get(key) == degree) {
                int left = list.indexOf(key);
                int right = list.lastIndexOf(key);
                minLen = Math.min(right-left+1, minLen);
            }
        }
        return minLen;
    }
}
