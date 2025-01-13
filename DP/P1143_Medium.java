/*
    1143. Longest common subsequence
    Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
    A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
    For example, "ace" is a subsequence of "abcde".
    A common subsequence of two strings is a subsequence that is common to both strings.

    Example 1:
        Input: text1 = "abcde", text2 = "ace" 
        Output: 3  
        Explanation: The longest common subsequence is "ace" and its length is 3.

    Example 2:
        Input: text1 = "abc", text2 = "abc"
        Output: 3
        Explanation: The longest common subsequence is "abc" and its length is 3.

    Example 3:
        Input: text1 = "abc", text2 = "def"
        Output: 0
        Explanation: There is no such common subsequence, so the result is 0.
    
    Constraints:
        1 <= text1.length, text2.length <= 1000
        text1 and text2 consist of only lowercase English characters.
 */
package DP;

public class P1143_Medium {
    /*
     * Solution: Bottom up DP
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n+1][m+1];

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] =  dp[i-1][j-1] +1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n+1][m+1];

        for(int i=n-1; i>=0; i--) {
            for(int j=m-1; j>=0; j--) {
                if(text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1] +1;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        return dp[0][0];
    }

    /*
     * Solution: space optimized DP
     * 
     * Approach:
     *      We only ever looked at the current column and the previous column. After that, previously computed columns are no longer needed.
     *      So, we can save a lot of space by instead of keeping track of an entire 2D array, only keeping track of the last two columns. 
     *      This reduces the space complexity to be proportional to the length of the word going down. We should make sure thid is the shortest of the two words.
     * 
     * time complexity:
     * space complexity:
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1.length() > text2.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        int n = text1.length();
        int m = text2.length();
        int[] prev = new int[n+1];

        /*
         * outer loop이 col기준으로 진행되면, 이전 열(prev) 값은 계속 유지되며 새롭게 계산된 현재 열(curr)로 업데이트할 수 있다. 
         * 만약, row를 outer loop로 설정하면, 계산 중에 '필요한 이전 열의 값 prev'를 계속 덮어쓰게 된다. 
         * 예를 들어, dp[row][col]을 계산할 때 dp[row+1][col]와 dp[row][col+1]이 필요하지만, row 기준으로 진행하면, dp[row][col+1]을 덮어쓰는 문제가 발생한다.
         * 
         * col기준으로 계산한다는 것은, 두 문자열을 비교할 때, "한 문자열의 각 문자에 대해 다른 문자열 전체를 순회한다"는 논리를 따른다. 문자열 비교의 자연스러운 순서를 반영한 것.
         */
        for(int col = m-1; col>=0; col--) {
            int[] curr = new int[n+1];
            for(int row = n-1; row>=0; row--) {
                if(text1.charAt(row) == text2.charAt(col)) {
                    curr[row] = prev[row+1] +1;
                } else {
                    curr[row] = Math.max(curr[row+1], prev[row]);
                }
            }
            prev = curr;
        }
        return prev[0];
    }
}
