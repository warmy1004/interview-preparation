/*
    909. Snakes and Ladders
    You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.
    You start on square 1 of the board. In each move, starting from square curr, do the following:

        Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
            This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
        If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
        The game ends when you reach the square n2.
    A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake or ladder is board[r][c]. Squares 1 and n2 are not the starting points of any snake or ladder.
    Note that you only take a snake or ladder at most once per dice roll. If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.
        For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
        Return the least number of dice rolls required to reach the square n2. If it is not possible to reach the square, return -1.

    Example 1:
    Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
    Output: 4
    Explanation: 
    In the beginning, you start at square 1 (at row 5, column 0).
    You decide to move to square 2 and must take the ladder to square 15.
    You then decide to move to square 17 and must take the snake to square 13.
    You then decide to move to square 14 and must take the ladder to square 35.
    You then decide to move to square 36, ending the game.
    This is the lowest pos  sible number of moves to reach the last square, so return 4.

    Example 2:
        Input: board = [[-1,-1],[-1,3]]
        Output: 1
    
    Constraints:
        n == board.length == board[i].length
        2 <= n <= 20
        board[i][j] is either -1 or in the range [1, n^2].
        The squares labeled 1 and n2 are not the starting points of any snake or ladder.
 */
package Matrix;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/*
 * Overview:
 *  we can model the grid as a graph. Each square is a node. There are edges between squares with 6 of each other, and the snakes and ladders add new edge.
 *  The problem is asking us for the minimum number of moves, which suggests this is a shortest-path problem.
 *      - given an unweighted directed graph, the shortest path problem is the problem of finding a path from one vertex to another, such that the number of edges is the minimum possible. 
 *  Threr is an algorithm for solving the shortest path problem in an unweighted graph - BFS. It is feasible to implement during an interview. 
 *      * BFS is an algorithm for finding the shortest path in unweighted graphs (directed or undirected)
 */

public class P909_ToDo_Medium {
    /*
     * Solution: BFS
     * time complexity: O(n^2)
     *      We run BFS on a graph whose vertices are the board cells, and the edges are moves between them. There are n^2 vertices and no more than 6*n^2 = O(n^2) edge.
     *      The time complexity of BFS is O(V+E), where V is the number of vertices and E is the number of edges. We have V = n^2 and E < 6n^2, thus the total time complexity for BFS is O(7n^2) = O(n^2). 
     *      We also spend some time associating each (row, col) with a label, but this also costs O(n^2), so the overall time complexity is O(n^2)
     * space complexity: O(n^2)
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int move = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        boolean[][] visited = new boolean[n][n];
        visited[n-1][0] = true;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                int currCell = queue.remove();
                if(currCell == n*n) return move;
                for(int dice = 1; dice<=6; dice++) {
                    if(currCell + dice > n*n) break;
                    int[] nextPos = getNextCell(currCell + dice -1, n);
                    int row = nextPos[0], col = nextPos[1];
                    if(!visited[row][col]) {
                        visited[row][col] = true;
                        if(board[row][col] == -1) {
                            queue.add(currCell+dice);
                        } else {
                            queue.add(board[row][col]);
                        }
                    }
                }
            }
            move++;
        }
        return -1;
    }
    int[] getNextCell(int curr, int n) {
        int row = n- curr/n -1;
        int col = curr % n;
        if(row%2 == n%2) {
            return new int[] {row, n-1-col};
        } else {
            return new int[] {row, col};
        }
    }

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int move = 0;
        boolean[][] visited = new boolean[n][n];
        visited[n-1][0] = true;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        while(!queue.isEmpty()) {
            int size = queue.size();
            move++;
            for(int i=0; i<size; i++) {
                int curr = queue.remove();
                for(int dice = 1; dice <= 6 && curr+dice<=n*n; dice++) {
                    int[] nextCell = findXY(curr+dice, n);
                    int nr = nextCell[0], nc = nextCell[1];
                    if(curr+dice == n*n || board[nr][nc]==n*n) {
                        return move;
                    }
                    if(!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        if(board[nr][nc]!=-1) {
                            queue.add(board[nr][nc]);
                        } else {
                            queue.add(curr+dice);
                        }
                    }
                }
            }
        }
        return -1;
    }
    int[] findXY(int i, int n) {
        int row = (i-1)/n;
        row = n-1 - row;
        int col = (i-1)%n;

        if(row%2 == n%2) {
            return new int[]{row, n-1-col};
        } else {
            return new int[]{row, col};
        }
    }

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Map<Integer, Integer> visited = new HashMap<>();
        visited.put(1, 0);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        while(!queue.isEmpty()) {
            int curr = queue.remove();
            for(int dice = 1; dice<=6; dice++) {
                int next = curr+dice;
                if(next > n*n) return -1;
                int nextCellVal = getCellVal(next, board, n);
                if(nextCellVal!=-1) {
                    next = nextCellVal;
                }
                if(next==n*n) {
                    return visited.get(curr)+1;
                }
                if(!visited.containsKey(next)) {
                    visited.put(next, visited.get(curr)+1);
                    queue.add(next);
                }
            }
        }
        return -1;
    }
    int getCellVal(int i, int[][] board, int n) {
        int row = (i-1)/n;
        int col = (i-1)%n;
        if(row%2 != 0) {
            col = n-1-col;
        }
        row = n-1-row;
        return board[row][col];
    }

    /*
     * Solution : BFS-2
     * 
     * time complexity:
     * space complexity:
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        // Make a flatten array
        int[] flatten = new int[n*n+1];
        int idx = 1;
        int col = 0;
        boolean reverse = false;
        for(int row = n-1; row>=0; row--) {
            if(!reverse) {
                while(col<n) {
                    flatten[idx++] = board[row][col++];
                }
                col = n-1;
            } else {
                while(col>=0) {
                    flatten[idx++] = board[row][col--];
                }
                col = 0;
            }
            reverse = !reverse;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        int move = 0;
        boolean[] visited = new boolean[n*n+1];
        visited[1] = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                int curr = queue.remove();
                if(curr==n*n) return move;
                for(int next = curr+1; next <= Math.min(curr+6, n*n); next++) {
                    if(!visited[next]) {
                        visited[next] = true;
                        if(flatten[next]!=-1) {
                            queue.add(flatten[next]);
                        } else {
                            queue.add(next);
                        }
                    }
                }
            }
            move++;
        }
        return -1;
    }
}
