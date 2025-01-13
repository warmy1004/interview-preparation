/*
    289. Game of Life
    According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
    The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
        Any live cell with fewer than two live neighbors dies as if caused by under-population.
        Any live cell with two or three live neighbors lives on to the next generation.
        Any live cell with more than three live neighbors dies, as if by over-population.
        Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    The next state of the board is determined by applying the above rules simultaneously to every cell in the current state of the m x n grid board. In this process, births and deaths occur simultaneously.
    Given the current state of the board, update the board to reflect its next state.
    Note that you do not need to return anything.

    Example 1:
        Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
        Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]

    Example 2:
        Input: board = [[1,1],[1,0]]
        Output: [[1,1],[1,1]]
    
    Constraints:
        m == board.length
        n == board[i].length
        1 <= m, n <= 25
        board[i][j] is 0 or 1.
    
    Follow up:
        Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells first and then use their updated values to update other cells.
        In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How would you address these problems?
 */
package Greedy;

public class P289_Medium {

    /*
     * Solution: Array
     * time complexity: O(nm)
     * space complexity: O(nm)
     */
    public void gameOfLife(int[][] board) {
        int n = board.length, m = board[0].length;
        int[][] copied = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                copied[i][j] = board[i][j];
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                int[] neighbors = findNeighbors(copied, i, j);
                if(copied[i][j] == 0 && neighbors[1] == 3) {
                    board[i][j] = 1;
                } else if(copied[i][j]==1 && (neighbors[1]<2 || neighbors[1]>3)) {
                    board[i][j] = 0;
                }
            }
        }
    }

    int[] findNeighbors(int[][] copied, int i, int j) {
        int[] answer = new int[2];
        int[] dir_x = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dir_y = {0, 1, 1, 1, 0, -1, -1, -1};

        for(int n=0; n<8; n++) {
            int x = i+dir_x[n];
            int y = j+dir_y[n];
            if(x>=0 && y>=0 && x<copied.length && y<copied[0].length) {
                answer[copied[x][y]]++;
            }  
        }
        return answer;
    }

    public void gameOfLife(int[][] board) {
        int n = board.length, m = board[0].length;
        int[][] copied = new int[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                int live = neighbor(board, i-1, j-1) + neighbor(board, i-1, j) + neighbor(board, i-1, j+1) + neighbor(board, i, j-1) + neighbor(board, i, j+1) + neighbor(board, i+1, j-1) + neighbor(board, i+1, j) + neighbor(board, i+1, j+1);
                if(board[i][j]==1) {
                    copied[i][j] = (live <2 || live>3) ? 0:1;
                } else {
                    copied[i][j] = live == 3 ? 1: 0;
                }
            }
        }

        for(int i=0; i<n; i++) {
            board[i] = copied[i].clone();
        }
    }

    int neighbor(int[][] board, int i, int j) {
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]==0) return 0;
        return 1;
    }

    /*
     * Solution: optimized space solution
     * time complexity: O(nm)
     * space complexity: O(1)
     * 
     * Approach:
     *      1. if the cell is currently alive and it will die in the next state, then encode this state as -1.
     *      2. if the cell is currently dead and it will become alive in the next state, then encode this state as 2.
     */
    public void gameOfLife(int[][] board) {
        int n = board.length, m = board[0].length;
        int[] dir_x = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dir_y = {0, 1, 1, 1, 0, -1, -1, -1};

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                int liveNeighbors = 0;
                for(int dir=0; dir<8; dir++) {
                    int x = i+dir_x[dir];
                    int y = j+dir_y[dir];
                    if(x>=0 && x<n && y>=0 && y<m && (board[x][y]==1 || board[x][y] == -1)) {
                        liveNeighbors++;
                    }
                }

                if(board[i][j] == 0 && liveNeighbors==3) {
                    board[i][j] = 2;
                }
                if(board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors>3)) {
                    board[i][j] = -1;
                }
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] == -1) {
                    board[i][j] = 0;
                }
                if(board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }
    
}
