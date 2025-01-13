/*
    79. Word search
    Given an m x n grid of characters board and a string word, return true if word exists in the grid.
    The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

    Example 1:
        Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
        Output: true

    Example 2:
        Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
        Output: true

    Example 3:
        Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
        Output: false
    
    Constraints:
        m == board.length
        n = board[i].length
        1 <= m, n <= 6
        1 <= word.length <= 15
        board and word consists of only lowercase and uppercase English letters.
    
    Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
package Backtracking;

public class P79_Medium {
    /*
     * Solution: backtracking
     * time complexity: O(N*3^L) where N is the number of cells in the board and L is the length of the word to be matched.
     *      - for the backtracking function, initially we could have at most 4 directions to explore, but further the choices are reduced into 3 (since we won't go back to where we come from). 
     *        As a result, the execution trace after the first step could be exploration in the corresponding direction. Therefore, in the worst case, the total number of invocation would be the number of nodes in a full 3-nary tree, which is about 3^L.
     *      - we iterate through the board for backtracking, i.e. there could be N times invocation for the backtracking function in the worst case.
     *      - as a result, overall the time complexity of the algorithm would be O(N*3^L)
     * 
     * space complexity: O(L)
     */
    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(backtrack(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean backtrack(char[][] board, int i, int j, String word, int idx) {
        if(idx>=word.length()) return true;
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=word.charAt(idx)) return false;
        
        board[i][j] = '#';
        boolean top = backtrack(board, i-1, j, word, idx+1);
        boolean bottom = backtrack(board, i+1, j, word, idx+1);
        boolean left = backtrack(board, i, j-1, word, idx+1);
        boolean right = backtrack(board, i, j+1, word, idx+1);
        board[i][j] = word.charAt(idx);
        return top || bottom || left || right;
    }

    boolean backtrack2(char[][] board, int i, int j, String word, int idx) {
        if(idx>=word.length()) return true;
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=word.charAt(idx)) return false;
        boolean result = false;
        board[i][j] = '#';
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1} , {0, 1}};
        for(int[] dir : directions) {
            result = backtrack2(board, i+dir[0], j+dir[1], word, idx+1);
            if(result) break;
        }
        board[i][j] = word.charAt(idx);
        return result;
    }

    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if( board[i][j] == word.charAt(0)) {
                    boolean result = backtrack(board, i, j, word, 0);
                    if(result) return true;
                }
            }
        }
        return false;
    }

    boolean backtrack3(char[][] board, int i, int j, String word, int idx) {
        if(idx>=word.length()) return true;
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=word.charAt(idx)) return false;
        boolean result = false;
        board[i][j] = '#';
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1} , {0, 1}};
        for(int[] dir : directions) {
            result = backtrack3(board, i+dir[0], j+dir[1], word, idx+1);
            if(result) return true; // which means, the word is found
        }
        board[i][j] = word.charAt(idx);
        return result;
    }
}
