/*
    179. Largest Number
    Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
    Since the result may be very large, so you need to return a string instead of an integer.

    Example 1:
        Input: nums = [10,2]
        Output: "210"

    Example 2:
        Input: nums = [3,30,34,5,9]
        Output: "9534330"
    
    Constraints:
        1 <= nums.length <= 100
        0 <= nums[i] <= 10^9
 */
package Array_String;

import java.util.Arrays;

public class P179_ToDo_Medium {
    /*
     * Solution: Using built-in function
     * time complexity: O(nlogn) by sorting step
     * space complexity: O(n)
     */
    public String largestNumber(int[] nums) {
        String[] array = new String[nums.length];
        for(int i=0; i<nums.length; i++) {
            array[i] = String.valueOf(nums[i]);
        }

        // sorting as a descending order
        Arrays.sort(array, (a,b)-> (b+a).compareTo(a+b));
        if(array[0].equals("0")) return "0";

        StringBuilder result = new StringBuilder();
        for(String str: array) {
            result.append(str);
        }
        return result.toString();
    }

}
