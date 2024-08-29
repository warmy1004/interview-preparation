/*
    463. Island Perimeter

    You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
    Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
    The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

    Example 1:
        Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
        Output: 16
        Explanation: The perimeter is the 16 yellow stripes in the image above.

    Example 2:
        Input: grid = [[1]]
        Output: 4

    Example 3:
        Input: grid = [[1,0]]
        Output: 4

    Constraints:
        row == grid.length
        col == grid[i].length
        1 <= row, col <= 100
        grid[i][j] is 0 or 1.
        There is exactly one island in grid.
 */

package BFS_DFS_BinaryTree;

public class P463_ToDo_Easy {
    /*
     * Solution: Counting 
     *      we only need to check two neighbors (left and up) because it's already been visited. 
     * 
     * time complexity: O(nm)
     * space complexity: O(1)
     */
    // public int islandPerimeter(int[][] grid) {
    //     int count = 0;

    //     for (int i=0; i<grid.length; i++) {
    //         for(int j=0; j<grid[0].length; j++) {
    //             if(grid[i][j]==1){
    //                 count +=4;
    //                 // check the above neighbor
    //                 if(i>0 && grid[i-1][j] == 1) count-=2;
    //                 // check the left neighbor
    //                 if(j>0 && grid[i][j-1] == 1) count-=2;
    //             }
    //         }
    //     }
    //     return count;
    // }

    /*
     * Solution
     */
    // public int islandPerimeter(int[][] grid) {
    //     int count = 0;

    //     for (int i=0; i<grid.length; i++) {
    //         for(int j=0; j<grid[0].length; j++) {
    //             if(grid[i][j]==1){
    //                 count += checkNearby(grid, i, j);
    //             } 
    //         }
    //     }
    //     return count;
    // }

    // private int checkNearby(int[][] grid, int i, int j) {
    //     int sum = 4;
    //     if( i>0 && grid[i-1][j]==1) sum--;
    //     if(i<grid.length-1 && grid[i+1][j]==1) sum--;
    //     if(j>0 && grid[i][j-1]==1) sum--;
    //     if(j<grid[0].length-1 && grid[i][j+1]==1) sum--;
    //     return sum;
    // }

     /*
     * Solution: DFS 1
     *      During the dfs, check the boundaries and water cells. For each out-of-boundary or water cell encountered, increment the count by 1, as it represents a part of the perimeter.
     * 
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    // int count = 0;
    // public int islandPerimeter(int[][] grid) {
    //     for (int i=0; i<grid.length; i++) {
    //         for(int j=0; j<grid[0].length; j++) {
    //             if(grid[i][j]==1){
    //                 dfs(grid, i, j);
    //             } 
    //         }
    //     }
    //     return count;
    // }

    // private void dfs(int[][] grid, int i, int j) {
    //     if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]==0) {
    //         count++;
    //         return;
    //     }

    //     if(grid[i][j]==-1) return;

    //     grid[i][j] = -1;
    //     dfs(grid, i-1, j);
    //     dfs(grid, i+1, j);
    //     dfs(grid, i, j-1);
    //     dfs(grid, i, j+1);
    // }

    /*
     * Solution: DFS 2
     */
    public int islandPerimeter(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j]==1 && !visited[i][j]) {
                    count+= dfs(grid, visited, i, j);
                }
            }
        }
        return count;
    }

    private int dfs(int[][] grid, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        int count = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for(int idx = 0; idx <4; idx++) {
            int next_i = i+dx[idx];
            int next_j = j+dy[idx];

            if(next_i < 0 || next_i > grid.length-1 || next_j <0 || next_j > grid[0].length-1 || grid[next_i][next_j] == 0) {
                count++;
            } else if (grid[next_i][next_j] == 1 && !visited[next_i][next_j]) {
                count += dfs(grid, visited, next_i, next_j);
            }
        }
        return count;
    }
}
