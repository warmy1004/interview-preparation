/*
    394. Decode String
    Given an encoded string, return its decoded string.
    The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
    You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
    The test cases are generated so that the length of the output will never exceed 105.

    Example 1:
        Input: s = "3[a]2[bc]"
        Output: "aaabcbc"

    Example 2:
        Input: s = "3[a2[c]]"
        Output: "accaccacc"

    Example 3:
        Input: s = "2[abc]3[cd]ef"
        Output: "abcabccdcdcdef"
    
    Constraints:
        1 <= s.length <= 30
        s consists of lowercase English letters, digits, and square brackets '[]'.
        s is guaranteed to be a valid input.
        All the integers in s are in the range [1, 300].
 */
package Stack;

import java.util.Stack;

public class P394_ToDo_Medium {
    /*
     * Solution: using two stacks
     * time complexity: O(maxK*n)
     *      where maxK is the maximum value of k and n is the length of a given string s. We traverse a string of size n and iterate k times to decode each pattern of form k[string]
     * space complexity: O(m+n)
     *      where m is the number of leeters(a-z) and n is the number of digit(0-9) in string s.
     */
    public String decodeString(String s) {
        Stack<Integer> counts = new Stack<>();
        Stack<StringBuilder> strings = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int n = 0;
        for(char ch: s.toCharArray()) {
            if(Character.isDigit(ch)) {
                n = n*10 + (ch-'0');
            } else if(ch == '[') {
                counts.push(n);
                n= 0;
                strings.push(sb);
                sb = new StringBuilder();
            } else if(ch==']') {
                int k = counts.pop();
                StringBuilder temp = sb;
                sb = strings.pop();
                while(k-->0) {
                    sb.append(temp);
                }
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
