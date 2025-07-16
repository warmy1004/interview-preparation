/*
    1456. Maximum number of vowels in a substring of given length
    Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
    Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

    Example 1:
        Input: s = "abciiidef", k = 3
        Output: 3
        Explanation: The substring "iii" contains 3 vowel letters.

    Example 2:
        Input: s = "aeiou", k = 2
        Output: 2
        Explanation: Any substring of length 2 contains 2 vowels.

    Example 3:
        Input: s = "leetcode", k = 3
        Output: 2
        Explanation: "lee", "eet" and "ode" contain 2 vowels.
    
    Constraints:
        1 <= s.length <= 10^5
        s consists of lowercase English letters.
        1 <= k <= s.length
 */
package Array_String;

import java.util.Set;

public class P1456_Medium {
    /*
     * Solution: sliding window
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int maxVowels(String s, int k) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int count = 0;
        for(int i=0; i<k; i++) {
            count += vowels.contains(s.charAt(i))? 1: 0;
        }
        int answer = count;
        for(int i=k; i<s.length(); i++) {
            count += vowels.contains(s.charAt(i))? 1: 0;
            count -= vowels.contains(s.charAt(i-k))? 1: 0;
            answer = Math.max(answer, count);
        }
        return answer;
    }

    public int maxVowels(String s, int k) {
        int count = 0, max = 0;
        for(int i=0; i<s.length(); i++) {
            count += isVowel(s.charAt(i)) ? 1:0;
            if(i>=k) {
                count-=isVowel(s.charAt(i-k))?1:0;
            }
            max = Math.max(max, count);
        }
        return max;
    }
    private boolean isVowel(char ch) {
        return ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u';
    }
}

/*
 * python
 */
class Solution:
    def maxVowels(self, s: str, k: int) -> int:
        vowels = {'a','e','i','o','u'}
        # vowels = frozenset("aeiou")
        count, result = 0,0
        for i in range(len(s)):
            if s[i] in vowels:
                count+=1
            if i>=k and s[i-k] in vowels:
                count-=1
            result = max(result, count)
        return result