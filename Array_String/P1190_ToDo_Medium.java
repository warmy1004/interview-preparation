/*
    1190. Reverse Substrings between each pair of parantheses
    You are given a string s that consists of lower case English letters and brackets.
    Reverse the strings in each pair of matching parentheses, starting from the innermost one.
    Your result should not contain any brackets.

    Example 1:
        Input:  s = "(abcd)"
        Output:"dcba"

    Example 2:
        Input: s = "(u(love)i)"
        Output: "iloveu"
        Explanation: The substring "love" is reversed first, then the whole string is reversed.

    Example 3:
        Input: s = "(ed(et(oc))el)"
        Output: "leetcode"
        Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
    

    Constraints:
        1 <= s.length <= 2000
        s only contains lower case English characters and parentheses.
        It is guaranteed that all parentheses are balanced.
 */
package Array_String;

import java.util.Stack;

public class P1190_ToDo_Medium {
    /*
     * Solution: Wormhole Teleportation technique
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer> openParenthesesLoc = new Stack<>();
        int[] pairs = new int[n];
        for(int i=0; i<n; i++) {
            if(s.charAt(i)=='(') {
                openParenthesesLoc.push(i);
            } else if (s.charAt(i)==')') {
                int opening = openParenthesesLoc.pop();
                pairs[i] = opening;
                pairs[opening] = i;
            }
        }

        StringBuilder result = new StringBuilder();
        for(int curr= 0, dir=1; curr<n; curr+=dir) {
            if(s.charAt(curr)=='(' || s.charAt(curr)==')') {
                curr = pairs[curr];
                dir = -dir;
            } else {
                result.append(s.charAt(curr));
            }
        }
        return result.toString();
    }
    
    /*
     * Solution: Stack with Brute force reverse
     * time complexity: O(n^2), since we iterate through whole s and every time we encounter ) we reverse substring so in worst case we will have O(n)*O(n).
     * space complexity: O(n)
     */
    public String reverseParentheses(String s) {
        Stack<Integer> opened = new Stack<>();
        StringBuilder result = new StringBuilder();

        for(char curr: s.toCharArray()) {
            if(curr=='(') {
                opened.push(result.length());
            } else if(curr==')') {
                int start = opened.pop();
                String reversed = new StringBuilder(result.substring(start)).reverse().toString();
                result.replace(start, result.length(), reversed);
            } else {
                result.append(curr);
            }
        }
        return result.toString();
    }
}
