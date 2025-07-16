/*
    733. Flood fill
    You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].
    To perform a flood fill:
        Begin with the starting pixel and change its color to color.
        Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
        Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
        The process stops when there are no more adjacent pixels of the original color to update.
    Return the modified image after performing the flood fill.

    Example 1:
        Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
        Output: [[2,2,2],[2,2,0],[2,0,1]]
        Explanation:
        From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
        Note the bottom corner is not colored 2, because it is not horizontally or vertically connected to the starting pixel.

    Example 2:
        Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
        Output: [[0,0,0],[0,0,0]]

    Explanation:
        The starting pixel is already colored with 0, which is the same as the target color. Therefore, no changes are made to the image.

    Constraints:
        m == image.length
        n == image[i].length
        1 <= m, n <= 50
        0 <= image[i][j], color < 216
        0 <= sr < m
        0 <= sc < n
 */
package BFS_DFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class P733_Easy {
    /*
     * Solution: BFS
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int orignal = image[sr][sc];
        if(orignal == color) return image;
        int[][] dirs = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sr, sc});
        
        while(!queue.isEmpty()) {
            int[] curr = queue.remove();
            image[sr][sc] = color;
            for(int[] dir: dirs) {
                int ni = curr[0] + dir[0];
                int nj = curr[1] + dir[1];
                if(ni>=0 && nj>=0 && ni<image.length && nj<image[0].length && image[ni][nj] == orignal) {
                    queue.add(new int[]{ni, nj});
                }
            }
        }
        return image;
    }

    /*
     * Solution: DFS
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if(original == color) return image;
        dfs(image, sr, sc, color, original);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int color, int original) {
        if(i<0 || j<0 || i>=image.length || j>= image[0].length || image[i][j]!=original) return;
        image[i][j] = color;
        dfs(image, i-1, j, color, original);
        dfs(image, i+1, j, color, original);
        dfs(image, i, j-1, color, original);
        dfs(image, i, j+1, color, original);
    }
}
