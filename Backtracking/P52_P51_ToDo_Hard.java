/*
    51. N-Queens
    The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
    Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
    Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

    Example 1:
        Input: n = 4
        Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

    Example 2:
        Input: n = 1
        Output: [["Q"]]

    Constraints:
        1 <= n <= 9

    52. N-Queens 2
    Same problem as P52 instead of the return type.
    >> Given an integer n, return the number of distinct solutions to the n-queens puzzle.
    
 */
package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P52_P51_ToDo_Hard {

    /*
     * Solution: backtracking
     * time complexity: O(n!)
     *      while it costs O(n^2) to build each valid solution, the amount of valid solutions S(N) does not grow nearly as fast as n!, so O(N!+S(N)*N^2) = O(N!)
     * space complexity: O(n^2)
     */
    Set<Integer> cols = new HashSet<>();
    Set<Integer> diagonals = new HashSet<>();
    Set<Integer> reversedDiagonals = new HashSet<>();

    public List<List<String>> solveNQueens(int n) { 
        List<List<String>> answer = new ArrayList<>();
        char[][] board = new char[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(board[i], '.');
        }
        backtrack(board, 0, answer);
        return answer;
    }

    void backtrack(char[][] board, int row, List<List<String>> answer) {
        if(row == board.length) {
            answer.add(createPuzzle(board));
            return;
        }
        for(int col = 0; col<board.length; col++) {
            if(cols.contains(col) || diagonals.contains(row-col) || reversedDiagonals.contains(row+col)) continue;
            board[row][col] = 'Q';
            cols.add(col);
            diagonals.add(row-col);
            reversedDiagonals.add(row+col);
            backtrack(board, row+1, answer);
            reversedDiagonals.remove(row+col);
            diagonals.remove(row-col);
            cols.remove(col);
            board[row][col] = '.';
        }
    }

    List<String> createPuzzle(char[][] board) {
        List<String> list = new ArrayList<>();
        for(int row = 0; row<board.length; row++) {
            String set = new String(board[row]);
            list.add(set);
        }
        return list;
    }

    /*
     * Solution: backtracking - V2
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
        backtrack(0, new ArrayList<>(), answer, new boolean[n], new boolean[2 * n], new boolean[2 * n], n);
        return answer;
    }

    void backtrack(int row, List<String> list, List<List<String>> answer, boolean[] cols, boolean[] diags,
            boolean[] revDiags, int n) {
        if (row == n) {
            answer.add(new ArrayList<>(list));
            return;
        }
        for (int col = 0; col < n; col++) {
            int diag = row - col + n; // to prevent a minus index
            int revDiag = row + col;
            if (cols[col] || diags[diag] || revDiags[revDiag])
                continue;
            char[] rowSet = new char[n];
            Arrays.fill(rowSet, '.');
            rowSet[col] = 'Q';
            list.add(new String(rowSet));
            cols[col] = true;
            diags[diag] = true;
            revDiags[revDiag] = true;
            backtrack(row + 1, list, answer, cols, diags, revDiags, n);
            list.removeLast();
            cols[col] = false;
            diags[diag] = false;
            revDiags[revDiag] = false;
        }
    }
}
