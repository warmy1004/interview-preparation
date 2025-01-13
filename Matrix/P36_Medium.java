/*
    36. valid sudoku
    Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
        Each row must contain the digits 1-9 without repetition.
        Each column must contain the digits 1-9 without repetition.
        Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
    Note:
        A Sudoku board (partially filled) could be valid but is not necessarily solvable.
        Only the filled cells need to be validated according to the mentioned rules.
    
    Example 1:
        Input: board = 
            [["5","3",".",".","7",".",".",".","."]
            ,["6",".",".","1","9","5",".",".","."]
            ,[".","9","8",".",".",".",".","6","."]
            ,["8",".",".",".","6",".",".",".","3"]
            ,["4",".",".","8",".","3",".",".","1"]
            ,["7",".",".",".","2",".",".",".","6"]
            ,[".","6",".",".",".",".","2","8","."]
            ,[".",".",".","4","1","9",".",".","5"]
            ,[".",".",".",".","8",".",".","7","9"]]
        Output: true

    Example 2:
        Input: board = 
            [["8","3",".",".","7",".",".",".","."]
            ,["6",".",".","1","9","5",".",".","."]
            ,[".","9","8",".",".",".",".","6","."]
            ,["8",".",".",".","6",".",".",".","3"]
            ,["4",".",".","8",".","3",".",".","1"]
            ,["7",".",".",".","2",".",".",".","6"]
            ,[".","6",".",".",".",".","2","8","."]
            ,[".",".",".","4","1","9",".",".","5"]
            ,[".",".",".",".","8",".",".","7","9"]]
        Output: false
        Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
    

    Constraints:
        board.length == 9
        board[i].length == 9
        board[i][j] is a digit 1-9 or '.'.
 */
package Matrix;

import java.util.HashSet;
import java.util.Set;

public class P36_Medium {
    /*
     * Solution: using hash set
     * time complexity: O(n^2)
     * space complexity: O(n^2)
     */
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] blocks = new HashSet[9];

        for(int i=0; i<9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            blocks[i] = new HashSet<>();
        }

        for(int r=0; r<9; r++) {
            for(int c=0; c<9; c++) {
                char ch = board[r][c];
                if(ch!='.') {
                    if(rows[r].contains(ch)) return false;
                    rows[r].add(ch);

                    if(cols[c].contains(ch)) return false;
                    cols[c].add(ch);

                    int b = (r/3)*3+(c/3);
                    if(blocks[b].contains(ch)) return false;
                    blocks[b].add(ch);

                }
            }
        }
        return true;
    }

    /*
     * Solution: using array
     * time complexity: O(n^2)
     * space complexity: O(n^2)
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] blocks = new int[9][9];

        for(int r=0; r<9; r++) {
            for(int c=0; c<9; c++) {
                if(board[r][c]!='.') {
                    int pos = board[r][c]-'1';
                    if(rows[r][pos] == 1) return false;
                    rows[r][pos] = 1;

                    if(cols[c][pos] == 1) return false;
                    cols[c][pos] = 1;

                    int b = (r/3)*3+(c/3);
                    if(blocks[b][pos] == 1) return false;
                    blocks[b][pos] = 1;
                }
            }
        }
        return true;
    }

    /*
     * Solution: using set
     * time complexity: O(n^2)
     * space complexity: O(n^2)
     */
    public boolean isValidSudoku(char[][] board) {
        for(int r=0; r<9; r++) {
            Set<Character> row = new HashSet<>();
            for(int c=0; c<9; c++) {
                if(board[r][c]!='.') {
                    if(row.contains(board[r][c])) return false;
                    row.add(board[r][c]);
                }
            }
        }

        for(int c=0; c<9; c++) {
            Set<Character> col = new HashSet<>();
            for(int r=0; r<9; r++) {
                if(board[r][c]!='.') {
                    if(col.contains(board[r][c])) return false;
                    col.add(board[r][c]);
                }
            }
        }

        Set<Character> section;
        for(int r=0; r<9; r+=3) {
            for(int c=0; c<9; c+=3) {
                int count =0; 
                section = new HashSet<>();
                int i=r, j=c;
                while(count<9) {
                    if(board[i][j]!='.') {
                        if(section.contains(board[i][j])) return false;
                        section.add(board[i][j]);
                    }

                    if(count==2 || count ==5) {
                        i++;
                        j=c;
                    } else {
                        j++;
                    }
                    count++;
                }
            }
        }
        return true;
    }
}
