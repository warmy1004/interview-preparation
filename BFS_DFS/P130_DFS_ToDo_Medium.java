/*
    130. Surrounded Regions
    You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
        Connect: A cell is connected to adjacent cells horizontally or vertically.
        Region: To form a region connect every 'O' cell.
        Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
        A surrounded region is captured by replacing all 'O's with 'X's in the input matrix board.

    Example 1:
        Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
        Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
        Explanation:
        In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

    Example 2:
        Input: board = [["X"]]
        Output: [["X"]]

    Constraints:
        m == board.length
        n == board[i].length
        1 <= m, n <= 200
        board[i][j] is 'X' or 'O'.
 */

public class P130_DFS_ToDo_Medium {
    /*
     * Solution: DFS
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    public void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        if(row<=2 && col<=2) return;

        for(int i = 0; i< row; i++) {
            dfs_checkBoard(board, i, 0);
            dfs_checkBoard(board, i, col-1);
        }
        for(int j=0; j<col; j++) {
            dfs_checkBoard(board, 0, j);
            dfs_checkBoard(board, row-1, j);
        }

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                if(board[i][j] =='O') {
                    board[i][j] = 'X';
                }
                if(board[i][j]=='N') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    private void dfs_checkBoard(char[][] board, int i, int j) {
        if(i<0 || j<0 || i> board.length-1 || j>board[0].length-1 || board[i][j]!='O') return;
        board[i][j] = 'N';
        dfs_checkBoard(board, i-1, j);
        dfs_checkBoard(board, i+1, j);
        dfs_checkBoard(board, i, j-1);
        dfs_checkBoard(board, i, j+1);
    }
}
