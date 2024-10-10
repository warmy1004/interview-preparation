/*
    994. Rotting Oranges
    You are given an m x n grid where each cell can have one of three values:
        0 representing an empty cell,
        1 representing a fresh orange, or
        2 representing a rotten orange.
    Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
    Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

    Example 1:
        Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
        Output: 4

    Example 2:
        Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
        Output: -1
        Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

    Example 3:
        Input: grid = [[0,2]]
        Output: 0
        Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
    

    Constraints:
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 10
        grid[i][j] is 0, 1, or 2.
 */
package BFS_DFS_BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class P994_ToDo_Medium {
    /*
     * Solution 1: BFS
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;
        for(int i=0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(grid[i][j] == 1) {
                    freshOranges++;
                } else if(grid[i][j]==2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        if(freshOranges==0) return 0;
        if(queue.isEmpty()) return -1;

        int[][] dirs = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int minutes = 0;
        while(!queue.isEmpty() && freshOranges > 0) {
            int queueSize = queue.size();
            minutes++;
            while(queueSize > 0) {
                int[] currLoc = queue.poll();
                for(int[] dir : dirs) {
                    int i = currLoc[0] + dir[0];
                    int j = currLoc[1] + dir[1];
                    if(i>=0 && i<n && j>=0 && j<m && grid[i][j]==1) {
                        grid[i][j] = 2;
                        freshOranges--;
                        queue.offer(new int[]{i,j});
                    }
                }
                queueSize--;
            }
        }
        return freshOranges==0? minutes: -1;
    }

    /*
     * Solution 2: DFS
     * time complexity: O(mn)
     * space complexity: O(mn)
     */
    public int orangesRotting(int[][] grid) {
        for(int i=0; i<grid.length; i++) {
            for(int j = 0; j<grid[0].length; j++) {
                if(grid[i][j]==2) {
                    dfs(grid, i, j, 2);
                }
            }
        }

        int minutes = 2;
        for (int[] row : grid) {
            for(int cell: row) {
                if(cell == 1) return -1;
                minutes = Math.max(minutes, cell);
            }
        }
        return minutes-2;
    }

    private void dfs(int[][] grid, int i, int j, int minute) {
        /*
         * (grid[i][j]>1 && grid[i][j]<minute) : the orange is already rotten by another rotten orange within a shorter time
         */
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]==0 || (grid[i][j]>1 && grid[i][j]<minute) ) return;
        grid[i][j] = minute;
        dfs(grid, i-1, j, minute+1);
        dfs(grid, i+1, j, minute+1);
        dfs(grid, i, j-1, minute+1);
        dfs(grid, i, j+1, minute+1);
    }
}
