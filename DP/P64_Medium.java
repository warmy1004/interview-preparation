/*
    64. Minimum path sum
    Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
    Note: You can only move either down or right at any point in time.

    Example 1:
    Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
    Output: 7
    Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

    Example 2:
    Input: grid = [[1,2,3],[4,5,6]]
    Output: 12
    
    Constraints:
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 200
        0 <= grid[i][j] <= 200
 */
package DP;

public class P64_Medium {
    /*
     * Solution: DP 
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i==0 && j>0) {
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }
                if(i>0 && j==0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }
                if(i>0 && j>0) {
                    dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(i==m-1 && j<n-1) {
                    dp[i][j] = grid[i][j] + dp[i][j+1];
                } else if (i < m-1 && j==n-1) {
                    dp[i][j] = grid[i][j] + dp[i+1][j];
                } else if( i<m-1 && j<n-1) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i+1][j], dp[i][j+1]);
                } else {
                    dp[i][j] = grid[i][j];
                }
            }
        }
        return dp[0][0];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i=1; i<m; i++) {
            grid[i][0] += grid[i-1][0];
        }
        for(int j=1; j<n; j++) {
            grid[0][j] += grid[0][j-1];
        }
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }

    /*
     * Solution: 1D DP
     * 
     * Approach:
     *      Since for making the current entry all we need is the dp entry for the bottom and the right element.
     *      Thus, we start by initializing only the last element of the array as the last element of the given matrix. 
     *      The last entry is the bottom rightmost element of the given matrix. Then, we start moving forwards the left and update the entry dp(j) as: 
     *          dp(j) = grid(i,j) + min(dp(j), dp(j+1))
     * time complexity: O(nm)
     * space complexity: O(m)
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for(int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(i==m-1 && j<n-1) {
                    dp[j] = grid[i][j] + dp[j+1];
                } else if (i < m-1 && j==n-1) {
                    dp[j] = grid[i][j] + dp[j];
                } else if( i<m-1 && j<n-1) {
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j+1]);
                } else {
                    dp[j] = grid[i][j];
                }
            }
        }
        return dp[0];
    }

    /*
     * Solution: DP without extra space
     * time complexity: O(nm)
     * space complexity: O(1)
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for(int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(i==m-1 && j <n-1) {
                    grid[i][j] += grid[i][j+1];
                } else if(i<m-1 && j==n-1 ) {
                    grid[i][j] += grid[i+1][j];
                } else if(i < m-1 && j<n-1) {
                    grid[i][j] += Math.min(grid[i][j+1], grid[i+1][j]);
                }
            }
        }
        return grid[0][0];
    }
}

