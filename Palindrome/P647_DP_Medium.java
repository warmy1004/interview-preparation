/*
    647. Palindromic Substrings
        Given a string s, return the number of palindromic substrings in it.
        A string is a palindrome when it reads the same backward as forward.
        A substring is a contiguous sequence of characters within the string.

    Example 1:
        Input: s = "abc"
        Output: 3
        Explanation: Three palindromic strings: "a", "b", "c".

    Example 2:
        Input: s = "aaa"
        Output: 6
        Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
    
    Constraints:
        1 <= s.length <= 1000
        s consists of lowercase English letters.
 */

package Palindrome;

public class P647_DP_Medium {
    /*
     * Solution : DP - bottom up 1
     * time complexity: O(n^2)
     * space complexity: O(N^2)
     */
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = n;

        for(int i=0; i<n; i++) {
            dp[i][i] = true;
            for(int j=0; j<i; j++) {
                if(s.charAt(j) == s.charAt(i) && (dp[j+1][i-1] || i-j<=2)) {
                    dp[j][i] = true;
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * Solution : DP - Bottom up 2
     */
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = n;

        for(int i=n-1; i>=0; i--) {
            dp[i][i] = true;
            for (int j = i+1; j<n; j++) {
                if(s.charAt(i) == s.charAt(j) && (j-i<=2 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * Solution : DP - top down
     */
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;

        for(int i=0; i<n; i++) {
            for(int j= 0; j<=i; j++) {
                count += isPalindrome(s, i, j, dp);
            }
        }
        return count;
    }
    private int isPalindrome(String s, int i, int j, boolean[][] dp) {
        if(dp[i][j]) return 0;
        if(s.charAt(i)==s.charAt(j) && (j-i<=2 || isPalindrome(s, i+1, j-1, dp)!=0) {
            dp[i][j] = 1;
            return 1;
        }
        return 0;
    }

   /*
     * Solution: two pointers - expanding from the center
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    public int countSubstrings(String s) {
        int n = s.length();
        int count = 0;
        for(int i= 0; i<n; i++) {
            count += expandPalindrome(s, i, i); // odd palindrome
            count += expandPalindrome(s, i, i+1); // even palindrome
        }
        return count;
    }
    private int expandPalindrome(String s, int i, int j) {
        int count = 0;
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)) {
            count++;
            i--;
            j++;
        }
        return count;
    }
}
