/*
    159. Longest Substring with at most two distinct characters
    Given a string s, return the length of the longest substring that contains at most two distinct characters.

    Example 1:
        Input: s = "eceba"
        Output: 3
        Explanation: The substring is "ece" which its length is 3.

    Example 2:
        Input: s = "ccaabbb"
        Output: 5
        Explanation: The substring is "aabbb" which its length is 5.
    
    Constraints:
        1 <= s.length <= 10^5
        s consists of English letters.
 */
package Array_String.SlidingWindow;

import java.util.Collection;

public class P159_Medium {
    /*
     * Solution: Sliding Widnow
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> seen = new HashMap<>();
        int count = 0;
        int left = 0;
        for(int right = 0; right<s.length(); right++) {
            char rc = s.charAt(right);
            seen.put(rc, seen.getOrDefault(rc,0)+1);
            if(seen.size()<2 ||(seen.size()==2 && seen.contains(rc))){
                count = Math.max(count, right-left+1);
            } else {
                while( seen.size() == 2) {
                    char lc = s.charAt(left);
                    if(seen.get(lc) == 1) {
                        seen.remove(lc);
                    } else {
                        seen.put(lc, seen.get(lc)-1);
                    }
                    left++;
                }
            }
        }
        return count;
    }

    public int lengthOfLongestSubstringTwoDistinct_BETTER(String s) {
        if(s.length()<3) return s.length();

        Map<Character, Integer> seen = new HashMap<>();
        int count = 0;
        int left = 0;
        int right = 0;

        while( right < s.length()) {
            seen.put(s.charAt(right), right);
            if(seen.size() == 3) {
                int target_index = Collections.min(seen.values());
                seen.remove(s.charAt(target_index));
                left = target_index+1;
            }
            count = Math.max(count, right-left+1);
            right++;
        }
        return count;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> seen = new HashMap<>();
        int count = 0;
        int left = 0;
        for(int right =0; right<s.length(); right++) {
            seen.put(s.charAt(right), right);
            if(seen.size() > 2) {
                left = right -1;
                char allowed = s.charAt(left);
                while(left>=0 && s.charAt(left-1)==allowed) {
                    left--;
                }
                seen.remove(s.charAt(left-1));
            }
            count = Math.max(count, right-left+1); 
        }
        return count;
    }

    /*
     * Solution: Using Array
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] arr = new int[128]; // storing frequencies of characters in the string
        int left = 0, count = 0, distinct = 0;
        for(int right=0; right<s.length(); right++) {
            char rc = s.charAt(right);
            if(arr[rc]==0) distinct++;
            arr[rc]++;
            
            while(distinct > 2) {
                char lc = s.charAt(left);
                arr[lc]--;
                if(arr[lc]==0) distinct--;
                left++;
            }
            count = Math.max(count, right-left+1);
        }
        return count;
    }
}
