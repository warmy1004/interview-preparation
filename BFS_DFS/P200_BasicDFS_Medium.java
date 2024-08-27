/*
    200. Number of Islands

    Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

    Example 1:
        Input: grid = [
        ["1","1","1","1","0"],
        ["1","1","0","1","0"],
        ["1","1","0","0","0"],
        ["0","0","0","0","0"]
        ]
        Output: 1

    Example 2:
        Input: grid = [
        ["1","1","0","0","0"],
        ["1","1","0","0","0"],
        ["0","0","1","0","0"],
        ["0","0","0","1","1"]
        ]
        Output: 3

    Constraints:
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 300
        grid[i][j] is '0' or '1'.
 */

package BFS_DFS;

import java.util.LinkedList;
import java.util.Queue;

public class P200_BasicDFS_Medium {
    /*
     * Solution 1: DFS
     * time complexity: O(n*m)
     * space complexity: O(n*m)
     */
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if( grid[i][j] == '1') {
                    dfs(i, j, grid, n, m);
                    count++;
                }
            }
        }
        return count;
    }

    boolean dfs(int i, int j, char[][] grid, int n, int m) {
        if(i<=-1 || i>=n || j<=-1 || j>=m || grid[i][j] == '0') {
            return false;
        }
        grid[i][j] = '0';
        dfs(i-1, j, grid, n, m);
        dfs(i+1, j, grid, n, m);
        dfs(i, j-1, grid, n, m);
        dfs(i, j+1, grid, n, m);
        return true;
    }

    /*
     * Solution 2: BFS
     * time complexity : O(n*m)
     * space complexity: O(n*m)
     */
    // public int numIslands(char[][] grid) {
    //     int n = grid.length;
    //     int m = grid[0].length;
    //     int count = 0;

    //     for(int i = 0 ; i< n; i++) {
    //         for(int j=0; j<m;j++) {
    //             if(grid[i][j]=='1') {
    //                 count++;
    //                 grid[i][j]='0';
    //                 Queue<Integer> queue = new LinkedList<>();
    //                 queue.add(i*m + j);
    //                 while(!queue.isEmpty()) {
    //                     int curr = queue.poll();
    //                     int row = curr/m;
    //                     int col = curr%m;
    //                     if ( row > 0 && grid[row-1][col] == '1') {
    //                         queue.add((row-1)*m + col);
    //                         grid[row-1][col]='0';
    //                     }
    //                     if (row < n-1 && grid[row+1][col] =='1') {
    //                         queue.add((row+1)*m+col);
    //                         grid[row+1][col] = '0';
    //                     }
    //                     if ( col >0 && grid[row][col-1]=='1') {
    //                         queue.add(row * m + (col-1));
    //                         grid[row][col-1]='0';
    //                     }
    //                     if(col<m-1 && grid[row][col+1]=='1') {
    //                         queue.add(row*m + (col+1));
    //                         grid[row][col+1]='0';
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     return count;
    // }
}
