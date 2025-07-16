/*
    542. 01 Matrix
    Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
    The distance between two cells sharing a common edge is 1.

    Example 1:
        Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
        Output: [[0,0,0],[0,1,0],[0,0,0]]

    Example 2:
        Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
        Output: [[0,0,0],[0,1,0],[1,2,1]]
    
    Constraints:
        m == mat.length
        n == mat[i].length
        1 <= m, n <= 10^4
        1 <= m * n <= 10^4
        mat[i][j] is either 0 or 1.
        There is at least one 0 in mat.
    
    Note: This question is the same as 1765: https://leetcode.com/problems/map-of-highest-peak/
 */

package BFS_DFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class P542_Medium {
    /*
     * Solution: BFS 1
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        for(int i=0; i<mat.length; i++) {
            for(int j=0; j<mat[0].length; j++) {
                if(mat[i][j] == 0) {
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }

        int[][] dirs = {{-1,0}, {1, 0}, {0, -1}, {0,1}};
        while(!queue.isEmpty()) {
            int[] curr = queue.remove();
            for(int[] dir: dirs) {
                int x = dir[0]+curr[0], y=dir[1]+curr[1];
                if(x>=0 && x<mat.length && y>=0 && y<mat[0].length && !visited[x][y]) {
                    visited[x][y]=true;
                    queue.add(new int[]{x,y});
                    mat[x][y] = mat[curr[0]][curr[1]]+1;
                }
            }
        }
        return mat;
    }

    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    queue.add(new int[] { i, j, 0 });
                    visited[i][j] = true;
                }
            }
        }

        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        while (!queue.isEmpty()) {
            int[] node = queue.remove();

            for (int[] dir : dirs) {
                int nx = node[0] + dir[0], ny = node[1] + dir[1];
                int step = node[2];
                if (nx >= 0 && ny >= 0 && nx < mat.length && ny < mat[0].length && !visited[nx][ny]) {
                    mat[nx][ny] = step + 1;
                    visited[nx][ny] = true;
                    queue.add(new int[] { nx, ny, step + 1 });
                }
            }
        }
        return mat;
    }

    /*
     * Solution:
     * time complexity:
     * space complexity:
     */
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> queue = new ArrayDeque<>();
        for(int i=0; i<mat.length; i++) {
            for(int j=0; j<mat[0].length; j++) {
                if(mat[i][j] == 0) {
                    queue.add(new int[]{i,j});
                } else {
                    mat[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] dirs = {{-1,0}, {1, 0}, {0, -1}, {0,1}};
        while(!queue.isEmpty()) {
            int[] curr = queue.remove();
            int cx = curr[0], cy = curr[1];
            for(int[] dir: dirs) {
                int x = dir[0]+cx, y=dir[1]+cy;
                if(x>=0 && x<mat.length && y>=0 && y<mat[0].length &&  mat[cx][cy] + 1< mat[x][y]) {
                    queue.add(new int[]{x,y});
                    mat[x][y] = mat[cx][cy]+1;
                }
            }
        }
        return mat;
    }

    /*
     * Solution: DP
     * 
     * min = n*m을 사용하는 이유: 
     *      int min = Integer.MAX_VALUE하면 min +1 -> Integer.MAX_VALUE+1 -> 오버플로우 발생 -> 음수 -> 오답
     *      int min = Integer.MAX_VALUE-1 은 가능
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                dp[i][j] = mat[i][j];
            }
        }

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(dp[i][j]==0) continue;
                int min = n*m;
                if(i>0) {
                    min = Math.min(min, dp[i-1][j]);
                }
                if(j>0) {
                    min = Math.min(min, dp[i][j-1]);
                }
                dp[i][j] = min+1;
            }
        }

        for(int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(dp[i][j]==0) continue;
                int min = n*m;
                if(i<m-1) {
                    min = Math.min(min, dp[i+1][j]);
                }
                if(j<n-1) {
                    min = Math.min(min, dp[i][j+1]);
                }

                dp[i][j] = Math.min(dp[i][j], min+1);
            }
        }
        return dp;
    }
}
