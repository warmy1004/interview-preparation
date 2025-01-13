/*
    76. Minimum window substring
    Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
    The testcases will be generated such that the answer is unique.

    Example 1:
        Input: s = "ADOBECODEBANC", t = "ABC"
        Output: "BANC"
        Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

    Example 2:
        Input: s = "a", t = "a"
        Output: "a"
        Explanation: The entire string s is the minimum window.

    Example 3:
        Input: s = "a", t = "aa"
        Output: ""
        Explanation: Both 'a's from t must be included in the window.
        Since the largest window of s only has one 'a', return empty string.

    Constraints:
        m == s.length
        n == t.length
        1 <= m, n <= 10^5
        s and t consist of uppercase and lowercase English letters.
 
    Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
package Array_String.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class P76_ToDo_Hard {
    /*
     * Solution: sliding window + hashmap
     * time complexity: O(m)
     * space complexity: O(n)
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        // Count the frequency of characters in `t`
        Map<Character, Integer> targetCount = new HashMap<>();
        for (char ch : t.toCharArray()) {
            targetCount.put(ch, targetCount.getOrDefault(ch, 0) + 1);
        }

        // Sliding window variables
        int left = 0, right = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0; // To track the starting index of the minimum window
        int matchCount = 0; // Number of characters matched

        // Map to count characters in the current window
        Map<Character, Integer> windowCount = new HashMap<>();

        while (right < s.length()) {
            // Expand the window by including the character at `right`
            char rightChar = s.charAt(right);
            if (targetCount.containsKey(rightChar)) {
                windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);
                // If the frequency matches the target, increase the match count
                if (windowCount.get(rightChar).equals(targetCount.get(rightChar))) {
                    matchCount++;
                }
            }

            // Contract the window if all characters in `t` are matched
            while (matchCount == targetCount.size()) {
                // Update the minimum window if necessary
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }

                // Remove the leftmost character from the window
                char leftChar = s.charAt(left);
                if (targetCount.containsKey(leftChar)) {
                    if (windowCount.get(leftChar).equals(targetCount.get(leftChar))) {
                        matchCount--;
                    }
                    windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                }
                left++; // Shrink the window
            }

            right++; // Expand the window
        }

        // If no valid window is found, return an empty string
        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }

    /*
     * Solution: sliding window + array
     * time complexity: O(m)
     * space complexity: O(1)
     */
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";

        int[] map = new int[128];
        for(char ch: t.toCharArray()) {
            map[ch]++;
        }

        int left =0, right=0, start = 0, count = t.length();
        int minLen = Integer.MAX_VALUE;
        while(right < s.length()) {
            if(map[s.charAt(right)] > 0) {
                count--;
            }
            map[s.charAt(right)]--;
            right++;

            while(count == 0) {
                if(right-left < minLen) {
                    minLen = right-left;
                    start = left;
                }
                map[s.charAt(left)] ++;
                if(map[s.charAt(left)] > 0) {
                    count++;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE? "": s.substring(start,start+minLen);
    }
}
