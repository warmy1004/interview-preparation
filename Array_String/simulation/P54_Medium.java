/*
    54. Spiral matrix
    Given an m x n matrix, return all elements of the matrix in spiral order.

    Example 1:
        Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
        Output: [1,2,3,6,9,8,7,4,5]

    Example 2:
        Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        Output: [1,2,3,4,8,12,11,10,9,5,6,7]
    
    Constraints:
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 10
        -100 <= matrix[i][j] <= 100
 */
package Array_String.simulation;

import java.util.ArrayList;
import java.util.List;

public class P54_Medium {
    /*
     * Solution: set boundaries
     * time complexity: O(m*n)
     * space complexity: O(1)
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        List<Integer> answer = new ArrayList<>();
        int top=0, bottom=n-1, left=0, right=m-1;

        while(answer.size()<n*m) {
            if(answer.size()<n*m) {
                for(int j=left; j<=right; j++) {
                    answer.add(matrix[top][j]);
                }
            }
            if(answer.size()<n*m) {
                for(int i=top+1; i<bottom; i++) {
                    answer.add(matrix[i][right]);
                }
            }
            if(answer.size()<n*m) {
                for(int j=right; j>=left; j--) {
                    answer.add(matrix[bottom][j]);
                }
            }
            if(answer.size()<n*m) {
                for(int i=bottom-1; i>top; i--) {
                    answer.add(matrix[i][left]);
                }
            }
            top++;
            bottom--;
            left++;
            right--;
        }
        return answer;
    }
}
