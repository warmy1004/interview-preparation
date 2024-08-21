/*
    13. Roman to Integer

    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
        Symbol       Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
    For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

    Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
    I can be placed before V (5) and X (10) to make 4 and 9. 
    X can be placed before L (50) and C (100) to make 40 and 90. 
    C can be placed before D (500) and M (1000) to make 400 and 900.
    Given a roman numeral, convert it to an integer.

    Example 1:
        Input: s = "III"
        Output: 3
        Explanation: III = 3.

    Example 2:
        Input: s = "LVIII"
        Output: 58
        Explanation: L = 50, V= 5, III = 3.

    Example 3:
        Input: s = "MCMXCIV"
        Output: 1994
        Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
    
    Constraints:
        1 <= s.length <= 15
        s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
        It is guaranteed that s is a valid roman numeral in the range [1, 3999].
*/

package Array_String;

import java.util.HashMap;
import java.util.Map;

public class Top150_P13_Easy {

    /*
     * Solution
     * time complexity: O(1): as there is a finite set of roman numerals, the possible maximum number can be 3999, which in roman numerals is MMMCMXCIX. As such the time complexity is O(1).
     *      If roman numerals had an arbitrary number of symbols, then the time complexity would be proportional to the length of the input, ie. O(n)
     * space complexity: O(1)
     */
    // public int romanToInt(String s) {
    //     Map<Character, Integer> map = new HashMap<>();
    //     map.put('I', 1);
    //     map.put('V', 5);
    //     map.put('X', 10);
    //     map.put('L', 50);
    //     map.put('C', 100);
    //     map.put('D', 500);
    //     map.put('M', 1000);

    //     int result = 0;
    //     for(int i=0; i<s.length()-1; i++) {
    //         if(map.get(s.charAt(i)) < map.get(s.charAt(i+1))) {
    //             result -= map.get(s.charAt(i));
    //         } else {
    //             result += map.get(s.charAt(i));
    //         }
    //     }
    //     return result += map.get(s.charAt(s.length()-1));
    // }

    /*
     * Solution : Same as above, but faster (100% runtime)
     */
    public int romanToInt(String s) {
        int result = 0, right=0, curr = 0;
        for(int i=s.length()-1; i>=0; i--) {
            switch(s.charAt(i)) {
                case 'M': curr = 1000; break;
                case 'D': curr = 500; break;
                case 'C': curr = 100; break;
                case 'L': curr = 50; break;
                case 'X': curr = 10; break;
                case 'V': curr = 5; break;
                case 'I': curr = 1; break;
            }
            if(curr < right) {
                result -= curr;
            } else {
                result += curr;
            }
            right = curr;
        }
        return result;
    }
}