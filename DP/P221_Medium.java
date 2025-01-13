/*
    221. maximal square
    Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

    Example 1:
        Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
        Output: 4

    Example 2:
        Input: matrix = [["0","1"],["1","0"]]
        Output: 1

    Example 3:
        Input: matrix = [["0"]]
        Output: 0
    
    Constraints:
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 300
        matrix[i][j] is '0' or '1'.
 */
package DP;

public class P221_Medium {
    /*
     * Solution: bottom-up DP
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n+1][m+1];
        int maxLen = 0;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen*maxLen;
    }

    /*
     * Solution: space optimized bottom up DP
     * time complexity: O(nm)
     * space complexity: O(m)
     */
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[] dp = new int[m+1];
        int max = 0;
        int prev = 0; // for storing dp[i-1][j-1]
        for(int i=1;i<=n; i++) {
            for(int j=1; j<=m; j++) {
                // 현재 dp[j]값을 업데이트 하기 전에, 다음 열(j+1)에서 이전 행의 대각선 값으로 사용할 수 있도록, temp에 저장하고 후에 prev에 할당
                int temp = dp[j]; 
                if(matrix[i-1][j-1] == '1') {
                    // dp[j] = 현재 행의 값, dp[j-1] = 왼쪽 값
                    dp[j] = Math.min(dp[j], Math.min(dp[j-1], prev)) +1;
                    max = Math.max(dp[j], max);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return max*max;
    }
}
