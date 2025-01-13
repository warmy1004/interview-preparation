/*
    695. Max Area of Island
    You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
    The area of an island is the number of cells with a value 1 in the island.
    Return the maximum area of an island in grid. If there is no island, return 0.

    Example 1:
        Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
        Output: 6
        Explanation: The answer is not 11, because the island must be connected 4-directionally.

    Example 2:
        Input: grid = [[0,0,0,0,0,0,0,0]]
        Output: 0
    
    Constraints:
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 50
        grid[i][j] is either 0 or 1.
 */
package BFS_DFS_BinaryTree;

public class P695_Medium {
    /*
     * Solution: DFS
     * time complexity: O(mn)
     * space complexity: O(mn) because of call stack during the recursion
     */
    public int maxAreaOfIsland(int[][] grid) {
        int count = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j]==1) {
                    count = Math.max(count, dfs(grid, i, j));
                }
            }
        }
        return count;
    }
    private int dfs(int[][] grid, int i, int j) {
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]==0) {
            return 0;
        }
        grid[i][j] = 0;
        return 1 + dfs(grid, i-1, j) + dfs(grid, i+1, j) + dfs(grid, i, j-1) + dfs(grid, i, j+1);
    }

    /*
     * Solution: DFS - as antoher way
     */
    public int maxAreaOfIsland(int[][] grid) {
        int[] count = new int[];
        int max = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j]==1) {
                    count[0] = 0;
                    dfs(grid, i, j, count);
                    max = Math.max(count[0], max );
                }
            }
        }
        return max;
    }
    private void dfs(int[][] grid, int i, int j, int[] count) {
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]==0) {
            return;
        }
        grid[i][j] = 0;
        count[0]++;
        dfs(grid, i-1, j, count);
        dfs(grid, i+1, j, count);
        dfs(grid, i, j-1, count);
        dfs(grid, i, j+1, count);
    }

    /*
     * Solution: DFS - initial try
     */
    public int maxAreaOfIsland(int[][] grid) {
        int count = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    int curr = dfs(grid, i, j, 0);
                    count = Math.max(curr, count);
                }
            }
        }
        return count;
    }

    private int dfs(int[][] grid, int i, int j, int areacount) {
        if(i<0 || j<0 || i>=grid.length || j>= grid[0].length || grid[i][j]==0) 
            return 0;
        grid[i][j] = 0;
        areacount++;
        int up = dfs(grid, i-1, j, areacount);
        areacount = Math.max(up, areacount);

        int down = dfs(grid, i+1, j, areacount);
        areacount = Math.max(down, areacount);

        int left = dfs(grid, i, j-1, areacount);
        areacount = Math.max(left, areacount);
        
        int right = dfs(grid, i, j+1, areacount);
        areacount = Math.max(right, areacount);
        return areacount;
    }
}
