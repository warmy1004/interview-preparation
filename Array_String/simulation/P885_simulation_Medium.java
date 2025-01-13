/*
    885. Spiral matrix 3
    You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.
    You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we reach all rows * cols spaces of the grid.
    Return an array of coordinates representing the positions of the grid in the order you visited them.

    Example 1:
        Input: rows = 1, cols = 4, rStart = 0, cStart = 0
        Output: [[0,0],[0,1],[0,2],[0,3]]

    Example 2:
        Input: rows = 5, cols = 6, rStart = 1, cStart = 4
        Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]

    Constraints:
        1 <= rows, cols <= 100
        0 <= rStart < rows
        0 <= cStart < cols
 */
package Array_String.simulation;

import java.util.ArrayList;
import java.util.List;

public class P885_simulation_Medium {
    /*
     * Solution: simulation
     * time complexity: O(max(rows,cols)^2)
     * space complexity: O(rows*cols)
     */
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] answer = new int[rows*cols][2];
        int idx = 0;
        int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};

        for(int dir = 0, step= 1; idx < rows*cols;) {
            for(int count = 0; count<2; count++) {
                for(int i=0; i<step; i++) {
                    if(rStart >=0 && rStart < rows && cStart>=0 && cStart<cols) {
                        answer[idx][0] = rStart;
                        answer[idx][1] = cStart;
                        idx++;
                    }
                    rStart = rStart + directions[dir][0];
                    cStart = cStart + directions[dir][1];
                }
                dir=(dir+1)%4;
            }
            step++;
        }
        return answer;
    }

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] answer = new int[rows*cols][2];
        int idx = 0;
        answer[idx++] = new int[]{rStart, cStart};
        int[][] directions = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
        int step = 1;
        while(idx < rows*cols) {
            for(int[] dir: directions) {
                for(int i=0; i<step; i++) {
                    rStart += dir[0];
                    cStart += dir[1];
                    if(rStart >=0 && rStart < rows && cStart>=0 && cStart<cols) {
                        answer[idx][0] = rStart;
                        answer[idx][1] = cStart;
                        idx++;
                    }
                }
                if(dir==directions[1] || dir == directions[3]) {
                    step++;
                }
            }
        }
        return answer;
    }

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        List<int[]> answer = new ArrayList<>();
        answer.add(new int[] {rStart, cStart});
        int direction = 0;
        int step = 1;
        while(answer.size()<rows*cols) {
            for(int c =0; c<2; c++) {
                for(int i=0; i<step; i++) {
                    switch(direction) {
                        case 0:
                            cStart+=1;
                            break;
                        case 1:
                            rStart+=1;
                            break;
                        case 2:
                            cStart-=1;
                            break;
                        case 3:
                            rStart-=1;
                            break;
                        default:
                            throw new ArithmeticException("Invalid direction!");
                    }
                    if(rStart >=0 && rStart < rows && cStart>=0 && cStart<cols) {
                        answer.add(new int[]{rStart, cStart});
                    }
                }
                direction = (direction+1)%4;
            }
            step++;
        }
        return answer.toArray(new int[answer.size()][]);
    }

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] answer = new int[rows*cols][2];
        answer[0] = new int[] {rStart, cStart};
        int idx = 1;

        int[][] direction = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
        int dir = 0;

        int step = 0;

        while(idx<rows*cols) {
            if(dir==0 || dir==2) {
                step++;
            }

            for(int i=1; i<=step; i++) {
                rStart += direction[dir][0];
                cStart += direction[dir][1];

                if(rStart>=0 && rStart<rows && cStart>=0 && cStart<cols) {
                    answer[idx++] = new int[] {rStart, cStart};
                }
            }
            dir = (dir+1)%4;
        }
        return answer;
    }
}
