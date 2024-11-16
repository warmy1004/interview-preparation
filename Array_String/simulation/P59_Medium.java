/*
    59. Spiral matrix 2
    Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
    Example 1:
        Input: n = 3
        Output: [[1,2,3],[8,9,4],[7,6,5]]

    Example 2:
        Input: n = 1
        Output: [[1]]
    
    Constraints:
        1 <= n <= 20
 */
package Array_String.simulation;

public class P59_Medium {
    /*
     * Solution: simulation
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    public int[][] generateMatrix(int n) {
        int[][] answer = new int[n][n];
        int top=0, bottom=n-1, left=0, right=n-1;
        int idx=1;
        while(idx<=n*n) {
            for(int j=left; j<=right; j++) {
                answer[top][j] = idx++;
            }
            top++;
            for(int i=top; i<=bottom; i++) {
                answer[i][right] = idx++;
            }
            right--;
            for(int j=right; j>=left;j--) {
                answer[bottom][j] = idx++;
            }
            bottom--;
            for(int i=bottom; i>=top; i--) {
                answer[i][left]=idx++;
            }
            left++;
        }
        return answer;
    }
    
}
