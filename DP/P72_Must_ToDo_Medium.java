/*
    72. Edit distance
    Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
    You have the following three operations permitted on a word:
        Insert a character
        Delete a character
        Replace a character
    
    Example 1:
        Input: word1 = "horse", word2 = "ros"
        Output: 3
        Explanation: 
        horse -> rorse (replace 'h' with 'r')
        rorse -> rose (remove 'r')
        rose -> ros (remove 'e')

    Example 2:
        Input: word1 = "intention", word2 = "execution"
        Output: 5
        Explanation: 
        intention -> inention (remove 't')
        inention -> enention (replace 'i' with 'e')
        enention -> exention (replace 'n' with 'x')
        exention -> exection (replace 'n' with 'c')
        exection -> execution (insert 'u')
    
    Constraints:
        0 <= word1.length, word2.length <= 500
        word1 and word2 consist of lowercase English letters.
 */
package DP;

public class P72_Must_ToDo_Medium {
    /*
     * Solution: 2D Bottom-up DP
     * 
     * Approach:
     *  if word1[i] == word2[j], dp[i][j] = dp[i-1][j-1] because no operation is required
     *  otherwise, dp[i][j] is the minimum of the following three values:
     *      dp[i-1][j-1]+1 : replace the character at position i-1 in word1 with the character at position j-1 in word2
     *      dp[i-1][j] +1: delete the character at position i-1 in word1
     *      dp[i][j-1] +1: insert the character at j-1 in word2 into word1 at position i
     * 
     * time complexity: O(mn)
     * space comlexity: O(mn)
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n=word2.length();
        if(m==0) return n;
        if(n==0) return m;
        int[][] dp = new int[m+1][n+1];
        for(int i=1; i<=m; i++) {
            dp[i][0] = i;
        }
        for(int j=1; j<=n; j++) {
            dp[0][j] = j;
        }
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) +1;
                }
            }
        }
        return dp[m][n];
    }
}
