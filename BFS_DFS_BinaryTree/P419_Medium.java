/*
    419. Battleships in a board
    Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.
    Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).

    Example 1:
        Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
        Output: 2

    Example 2:
        Input: board = [["."]]
        Output: 0

    Constraints:
        m == board.length
        n == board[i].length
        1 <= m, n <= 200
        board[i][j] is either '.' or 'X'.
    
    Follow up: Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?
 */
package BFS_DFS_BinaryTree;

public class P419_Medium {
    /*
     * Solution 1: DFS
     * time complexity: O(nm)
     * space complexity: O(1)
     */
    public int countBattleships(char[][] board) {
        int count = 0;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(board[i][j] == 'X') {
                    count++;
                    dfs(board, i, j);
                }
            }
        }
        return count;
    }
    private void dfs(char[][] board, int i, int j) {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]=='.') return;
        board[i][j] = '.';
        dfs(board, i-1, j);
        dfs(board, i+1, j);
        dfs(board, i, j-1);
        dfs(board, i, j+1);
    }

    /*
     * Solution 2: one pass constant space - no modification on board
     * time complexity: O(nm)
     * space complexity: O(1)
     */
    public int countBattleships(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        int count = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] == 'X') {
                    if( (i==0 || board[i-1][j]=='.') && (j==0|| board[i][j-1]=='.')) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
