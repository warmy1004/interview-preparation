/*
    3. Longest Substring Without Repeating Characters
    Given a string s, find the length of the longest substring without repeating characters.

    Example 1:
        Input: s = "abcabcbb"
        Output: 3
        Explanation: The answer is "abc", with the length of 3.

    Example 2:
        Input: s = "bbbbb"
        Output: 1
        Explanation: The answer is "b", with the length of 1.

    Example 3:
        Input: s = "pwwkew"
        Output: 3
        Explanation: The answer is "wke", with the length of 3.
        Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
    
    Constraints:
        0 <= s.length <= 5 * 10^4
        s consists of English letters, digits, symbols and spaces.
 */
package Array_String.SlidingWindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/*
 * Similar Questions
 *  159. Longest Substring with At Most Two Distinct Characters
 *  340. Longest Substring with At Most K Distinct Characters
 */

public class P3_ToDo_Medium {
    /*
     * Solution: HashSet + sliding window
     * time complexity: O(2n) = O(n), in the worst case each character will be visited twice by left and right.
     * space complexity: O(min(m,n))
     *      we need O(k) space for the sliding window, where k is the size of Set. The size of the Set is upper bounded by the size of the string n and the size of the charset/alphabet m.
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> seen = new HashSet<>();
        int maxCount = 0;
        int left = 0;
        for(int right = 0; right<s.length(); right++) {
            if(seen.contains(s.charAt(right))) {
                while(seen.contains(s.charAt(right))) {
                    seen.remove(s.charAt(left));
                    left++;
                }
            } else {
                maxCount = Math.max(right-left+1, maxCount);
            }
            seen.add(s.charAt(right));
        }
        return maxCount;
    }

    /*
     * Solution: Unordered HashMap + sliding window
     * time complexity: O(n)
     * space complexity: O(min(m, n))
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> seen = new HashMap<>();
        int left = 0;
    
        int maxCount = 0;
        for(int right = 0; right<s.length(); right++) {
            char curr = s.charAt(right);
            if(!seen.containsKey(curr) || seen.get(curr) < left) {
                seen.put(curr, right );
                maxCount = Math.max(right-left+1, maxCount);
                
            } else {
                left = seen.get(curr)+1;
                seen.put(curr, right);
            }
        }
        return maxCount;
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> seen = new HashMap<>();
        int left = 0, right = 0, count = 0;
        while(right < s.length()) {
            char rch = s.charAt(right);
            seen.put(rch, seen.getOrDefault(rch, 0)+1);

            while(seen.get(rch)>1) {
                char lch = s.charAt(left++);
                seen.put(lch, seen.get(lch)-1);
            }

            count = Math.max(count, right-left+1);
            right++;
        }
        return count;
    }

    /*
     * Solution: Array (replacing Set)
     *      int[26] for Letters 'a'-'z' or 'A'-'Z'
     *      int[128] for ASCII
     *      int[256] for Extended ASCII
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxCount = 0;
        int[] charIndex = new int[128];
        Arrays.fill(charIndex, -1);
        int left = 0;

        for(int right = 0; right<n; right++) {
            if(charIndex[s.charAt(right)]>=left) {
                left = charIndex[s.charAt(right)]+1;
            }
            charIndex[s.charAt(right)] = right;
            maxCount = Math.max(maxCount, right-left+1);
        }
        return maxCount;
    }
}
