/*
    74. Search a 2D matrix
    You are given an m x n integer matrix matrix with the following two properties:
        Each row is sorted in non-decreasing order.
        The first integer of each row is greater than the last integer of the previous row.
    Given an integer target, return true if target is in matrix or false otherwise.
    You must write a solution in O(log(m * n)) time complexity.

    Example 1:
        Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
        Output: true

    Example 2:
        Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
        Output: false
    
    Constraints:
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 100
        -10^4 <= matrix[i][j], target <= 10^4
 */

package BinarySearch;

public class P74_Must_ToDo_Medium {
    /*
     * Solution: Binary Search
     * time complexity: O(log(n*m))
     * space complexity: O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int low = 0, high = n*m-1;
        while(low<=high) {
            int mid = low+(high-low)/2;
            // Need to remember, always divide by col
            int x = mid/m;
            int y = mid%m;
            if(matrix[x][y] == target) return true;
            if(matrix[x][y] < target) low = mid+1;
            else high = mid-1;
        }
        return false;
    }
}
