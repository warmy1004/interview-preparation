/*
    66. Plus one
    You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
    Increment the large integer by one and return the resulting array of digits.

    Example 1:
        Input: digits = [1,2,3]
        Output: [1,2,4]
        Explanation: The array represents the integer 123.
        Incrementing by one gives 123 + 1 = 124.
        Thus, the result should be [1,2,4].

    Example 2:
        Input: digits = [4,3,2,1]
        Output: [4,3,2,2]
        Explanation: The array represents the integer 4321.
        Incrementing by one gives 4321 + 1 = 4322.
        Thus, the result should be [4,3,2,2].

    Example 3:
        Input: digits = [9]
        Output: [1,0]
        Explanation: The array represents the integer 9.
        Incrementing by one gives 9 + 1 = 10.
        Thus, the result should be [1,0].
    
    Constraints:
        1 <= digits.length <= 100
        0 <= digits[i] <= 9
        digits does not contain any leading 0's.
 */
package Math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class P66_Easy {
    /*
     * Solution: with carry
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i]++;
                return digits;
            }
        }
        digits = new int[n+1];
        digits[0] = 1;
        return digits;
    }


    /*
     * Solution: carry v2
     * time complexity: O(n)
     * space complexity:O(n)
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        List<Integer> arr = new ArrayList<>();
        int carry = 1;
        for(int i=n-1; i>=0; i--) {
            int num = digits[i]+carry;
            arr.add(num%10);
            carry = num/10;
        }
        if(carry == 1) {
            arr.add(1);
        }
        Collections.reverse(arr);
        int[] ans = new int[arr.size()];
        for(int i=0; i<arr.size(); i++) {
            ans[i] = arr.get(i);
        }
        return ans;
    }
}
