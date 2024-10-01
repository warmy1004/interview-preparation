/*
    516. Longest Palindrome Subsequence
    Given a string s, find the longest palindromic subsequence's length in s.
    A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

    Example 1:
        Input: s = "bbbab"
        Output: 4
        Explanation: One possible longest palindromic subsequence is "bbbb".

    Example 2:
        Input: s = "cbbd"
        Output: 2
        Explanation: One possible longest palindromic subsequence is "bb".
    
    Constraints:
        1 <= s.length <= 1000
        s consists only of lowercase English letters.
 */

/*
 * Similar Questions:
 *      647. Palindromic substrings
 */
package Palindrome;

public class P516_DP_LPS_Medium {
    /*
     * Solution: Iterative DP - Bottom up
     * time complexity: O(n^2)
     * space complexity: O(n^2)
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i=n-1; i>=0; i--) {
            dp[i][i] = 1;
            for(int j=i+1; j<n; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    // choose a longer palindrom substring without each edge character
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }

    /*
     * Solution: Iterative DP with space optimization
     *      If we look closely at the previous iterative dp approach's transition, to fill dp[i][j] for a particular i and all possible values of j, 
     *      we only need the values from the current and previous rows. To fill row i+1 in the dp grid, we need the values from row i+1 (dp[i+1][j-1]) and previously computed value 
     *      in the ith row itself(dp[i][j-1]). Values in rows i+2, i+3 and so on are no longer needed. So, we can solve this by using two 1D arrays of size n, dp and dp_prev.
     * 
     *      dp[j] stores the length of longest palindromic subsequence of the substring from index i to j in s. It's similar to what dp[i][j] stroed in the original dp approach
     *      The other array dp_prev is important to understand. It helps us by remembering the previous state that we completed previously. 
     *      dp_prev[j] stores the length of the longest palindromic subsequence of the substring from index i+1 to j in s. It's analogous to dp[i+1][j] in the original dp approach. 
     *      Because dp_prev stores the answers of substrings beginning with index 1 and dp stores the answers of substrings beginning with index i, 
     *      we must copy the element of dp to dp_prev after iterating over all the substrings beginning with index i to prepare for the next iteration.
     *      After we copdy dp to dp_prev, for the next iteration which considers substrings from i-1, dp_prev will hold values of substrings beginning at index i which is exactly what we want.
     * 
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int[] dp_prev = new int[n];

        for(int i=n-1; i>=0; i--) {
            dp[i] = 1;
            for(int j = i+1; j<n; j++) {
                if(s.charAt(i)==s.charAt(j)) {
                    dp[j] = 2 + dp_prev[j-1];
                } else {
                    dp[j] = Math.max(dp[j-1], dp_prev[j]);
                }
            }
            dp_prev = dp.clone();
        }
        return dp[n-1];
    }
    
    /*
     * Solution: Recursive DP - top down
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        return lps(s, 0, n-1, dp);
    }
    private int lps(String s, int i, int j, int[][] dp) {
        if(dp[i][j]>0) {
            return dp[i][j];
        }
        if(i==j) {
            return 1;
        }
        if(i>j) {
            return 0;
        }
        if(s.charAt(i) == s.charAt(j)) {
            dp[i][j] = 2 + lps(s, i+1, j-1, dp);
        } else {
            dp[i][j] = Math.max(lps(s, i, j-1, dp), lps(s, i+1, j, dp));
        }
        return dp[i][j];
    }

    /*
     * Solution: Reverse String and LCS
     * time complexity: O(n^2)
     * space complexity: O(n^2)
     */
    public int longestPalindromeSubseq(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[][] dp = new int[n+1][n+1];
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++) {
                if(s.charAt(i-1) == reversed.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[n-1][n-1];
    }
}
