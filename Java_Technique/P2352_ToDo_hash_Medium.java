
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
    2352. equal row and column pairs
    Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
    A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).

    Example 1:
        Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
        Output: 1
        Explanation: There is 1 equal row and column pair:
        - (Row 2, Column 1): [2,7,7]

    Example 2:
        Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
        Output: 3
        Explanation: There are 3 equal row and column pairs:
        - (Row 0, Column 0): [3,1,2,2]
        - (Row 2, Column 2): [2,4,2,2]
        - (Row 3, Column 2): [2,4,2,2]

    Constraints:
        n == grid.length == grid[i].length
        1 <= n <= 200
        1 <= grid[i][j] <= 10^5
 */
public class P2352_ToDo_hash_Medium {
    /*
     * Solution: hash map
     * time complexity:
     * space complexity:
     */
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int count = 0;
        Map<String, Integer> rowList = new HashMap<>();
        for(int[] row: grid) {
            String key = Arrays.toString(row);
            rowList.put(key, rowList.getOrDefault(key, 0)+1);
        }

        for(int j=0; j<n; j++) {
            int[] col = new int[n];
            for(int i=0; i<n; i++) {
                col[i] = grid[i][j];
            }
            count+= rowList.getOrDefault(Arrays.toString(col), 0);
        }
        return count;
    }
}
