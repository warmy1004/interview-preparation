/*
    409. Longest Palindrome
    Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
    Letters are case sensitive, for example, "Aa" is not considered a palindrome.

    Example 1:
        Input: s = "abccccdd"
        Output: 7
        Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.

    Example 2:
        Input: s = "a"
        Output: 1
        Explanation: The longest palindrome that can be built is "a", whose length is 1.
    
    Constraints:
        1 <= s.length <= 2000
        s consists of lowercase and/or uppercase English letters only.
 */
package Array_String;

import java.util.HashSet;
import java.util.Set;

public class P409_Palindrome_ToDo_Easy {
    /*
     * Solution : Greedy with HashMap
     * time complexity: O(n)
     * space complexity: O(m)
     */
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char ch: s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }

        int result = 0;
        boolean odd = false;
        for(int n: map.values()) {
            if(n%2==0) {
                result += n;
            } else {
                odd = true;
                result += (n-1);
            }
        }

        // if odd is true, we have at least one unmatched character to make the center of an odd length palindrome.
        return odd ? result +1 : result;
    }

    public int longestPalindrome2(String s) {
        int numberOfOdd = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(char ch: s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0)+1);
            if(map.get(ch)%2==0) {
                numberOfOdd--;
            } else {
                numberOfOdd++;
            }
        }

        return numberOfOdd >0 ? s.length() - (numberOfOdd-1) : s.length();
    }

    /*
     * Solution : Greedy with HashSet
     * time complexity: O(n)
     * space complexity: O(m)
     */
    public int longestPalindrome(String s) {
        Set<Character> dist = new HashSet<>();
        int result = 0;

        for(char ch: s.toCharArray()) {
            // ch char's number is made as even, so it can be a part of palindrome. So adding length by 2 and removing ch from set
            if(dist.contains(ch)) {
                result += 2;
                dist.remove(ch);
            } else {
                dist.add(ch);
            }
        }

        // if any char remains in dist, there is at least one unmatched odd character to make the center of an odd length palindrome
        if(!dist.isEmpty()) {
            result++;
        }
        return result;
    }

    /*
     * Solution : Greedy with array
     * time complexity: O(n)
     * space complexity: O(m)
     */
    public int longestPalindrome(String s) {
        int[] freq = new int[58];
        int result = 0;
        for (char ch: s.toCharArray()) {
            freq[ch-'A']++;
        }
        boolean odd = false;
        for(int count: freq) {
            if(count%2==0) {
                result += count;
            } else {
                odd = true;
                result += count-1;
            } 
        }
        return odd ? result +1 : result;

    }
}
