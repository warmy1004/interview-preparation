/*
    169. Majority Element

    Given an array nums of size n, return the majority element.
    The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

    Example 1:
        Input: nums = [3,2,3]
        Output: 3

    Example 2:
        Input: nums = [2,2,1,1,1,2,2]
        Output: 2
    
    Constraints:
        n == nums.length
        1 <= n <= 5 * 104
        -109 <= nums[i] <= 109
    
    Follow-up: Could you solve the problem in linear time and in O(1) space?
 */

package Array_String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Top150_P169_BoyerMooreVotingAlgo_Easy {
    /*
     * Solution 1 - HashMap <mine>
     * time complexity : O(n)
     * space complexity: O(n) 
     */
    // public int majorityElement(int[] nums) {
    //     int maxKey = nums[0];
    //     HashMap<Integer, Integer> map = new HashMap<>();
    //     for(int n:nums) {
    //         if(!map.containsKey(n)) map.put(n, 1);
    //         else {
    //             map.put(n, map.get(n)+1);
    //             maxKey = map.get(maxKey) >= map.get(n) ? maxKey : n;
    //         }
    //     }
    //     return maxKey;
    // }

    /* 
     * Solution2 - HashMap
     * 
     * java.util.HashMap.entrySet(): returns a set view of all the entries of a hashmap. The returned set is a view of the map, which means the changing the set also changes the map.
     *      Syntax: public Set< Map.Entry<K,V>> entrySet()
     * 
     * Map.Entry stores both the key and value together in one class: entry.getKey(), entry.getValue()
     */
    // public int majorityElement(int[] nums) {
    //     HashMap<Integer, Integer> map = new HashMap<>();

    //     for(int n: nums) {
    //         map.put(n, map.getOrDefault(n, 0)+1);
    //     }

    //     for(Map.Entry<Integer,Integer> entry: map.entrySet()) {
    //         if(entry.getValue() > nums.length/2) {
    //             return entry.getKey();
    //         }
    //     }
    //     return 0;
    // }

    /* 
     * Solution3 - Sorting
     * time compelxity: O(nlogn)
     * space complexity: O(1)
     * 
     * Intuition: if an element occurs more than n/2 times in the array, it will always occupy the middle position when the array is sorted. 
     *          Therefore, we can sort the array and return the element at index n/2.
     */
    // public int majorityElement(int[] nums) {
    //     Arrays.sort(nums);
    //     return nums[nums.length/2];
    // }

    /*
     * Solution 4 - Boyer-Moore Voting Algorithm
     * 
     * one of the popular optiomal algorithms which is used to find the majority element among the given elements that have more than n/2 occurrences.
     * This works perfectly fine for finding the majority element which takes 2 traversals over the given elements, which works in O(N) time complexity and O(1) space complexity.
     * 
     * Intuition:
     *      If we had some way of counting instances of the majority element as +1 and instances of any other elements as -1, 
     *      summing them would make it obvious that the majority element is indeed the majority element.
     * Algorithm: 
     *      First, choose a candidate from the given set of elements.
     *      If it's the same as the candidate element, increase the votees.
     *      Otherwise, decrease the votes. 
     *      If the votes become 0, select another new element as the new candidate.     
     * 
     * time complexity: O(n), Boyer-Moore performs constant work exactly n times, so the algorithm runs in linear time
     * space complexity: O(1), Boyer-Moore allocates only constant additional memory
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for(int num : nums) {
            if(count==0) {
                candidate = num;
            }
            count+= (num == candidate) ? +1 : -1;
        }

        return candidate;
    }  
}
