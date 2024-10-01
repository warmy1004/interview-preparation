/*
    5. Longest palindrome substring
    Given a string s, return the longest palindromic substring in s.

    Example 1:
        Input: s = "babad"
        Output: "bab"
        Explanation: "aba" is also a valid answer.

    Example 2:
        Input: s = "cbbd"
        Output: "bb"
    
    Constraints:
        1 <= s.length <= 1000
        s consist of only digits and English letters.
 */
package Palindrome; 

/*
 * Similar Questions:
 *      131. Palindrome Partitioning - Medium
 *      266. Palindrome Permutation - Easy
 *      516. Longest Palindromic Subsequence - Medium
 *      647. Palindromic Substrings - Medium
 */

public class P5_ToDo_Medium {
    /*
     * Solution: check all substrings - Brute-force
     * time complexity: O(n^3)
     * space complexity: O(1)
     */
    public String longestPalindrome_bruteForce(String s) {
        for(int len = s.length(); len >0; len--) {
            for(int start = 0; start <= s.length()-len; start++) {
                if( isPalindrome(s, start, start+len-1)) {
                    return s.substring(start, start+len);
                }
            }
        }
        return s.substring(0, 1);
    }

    boolean isPalindrome(String s, int start, int end) {
        while(start >=0 && end < s.length() && start<=end) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /*
     * Solution: Expand from center
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    public String longestPalindrome_expanding(String s) {
        String result =s.substring(0, 1);
        for(int i=0; i<s.length(); i++) {
            String odd = findPalindrome(s, i, i);
            String even = findPalindrome(s, i, i+1);
            if(result.length() < odd.length()) {
                result = odd;
            }
            if(result.length() < even.length()) {
                result = even;
            }
        }
        return result;
    }

    String findPalindrome(String s, int i, int j) {
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)) {
            i--;
            j++;
        }
        return s.substring(i+1, j);
    }

    /*
     * Solution: DP bottom up
     * time complexity: O(n^2)
     * space complexity: O(n^2)
     */
    public String longestPalindrome_DP(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        String result = "";
        for(int i=0; i<s.length(); i++) {
            dp[i][i] = true;
            for (int j=0; j<i; j++) {
                if(s.charAt(i) == s.charAt(j) && (dp[j+1][i-1] || i-j<=2)) {
                    dp[j][i]=true;

                    if( result.length() < i-j+1) {
                        result = s.substring(j, i+1);
                    }
                }
            }
        }
        return result;
    }
}
