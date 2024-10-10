/*
    286. Walls and Gates
    You are given an m x n grid rooms initialized with these three possible values.
        -1 A wall or an obstacle.
        0 A gate.
        INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
    Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

    Example 1:
        Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
        Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]

    Example 2:
        Input: rooms = [[-1]]
        Output: [[-1]]
    

    Constraints:
        m == rooms.length
        n == rooms[i].length
        1 <= m, n <= 250
        rooms[i][j] is -1, 0, or 2^31 - 1.
 */
package BFS_DFS_BinaryTree;

public class P286_Medium {
    /*
     * Solution: DFS
     * time complexity: O(mn)
     * space complexity: O(1)
     */
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i<rooms.length; i++) {
            for(int j=0; j<rooms[0].length; j++) {
                if(rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int i, int j, int dist) {
        if(i<0 || j<0 || i>=rooms.length || j>=rooms[0].length || rooms[i][j] == -1) return;

        if(rooms[i][j] <= dist && dist!=0) {
            return;
        }

        rooms[i][j] = dist;
        dfs(rooms, i-1, j, dist+1);
        dfs(rooms, i+1, j, dist+1);
        dfs(rooms, i, j-1, dist+1);
        dfs(rooms, i, j+1, dist+1);
    }

    /*
     * Solution : BFS
     *      We initiate BFS from all gates at the same time. Since BFS guarantees that we search all rooms of distance d before searching rooms of distance d+1, 
     *      the distance to an empty room must be the shortest.
     * time complexity: O(mn)
     * space complexity: O(mn)
     */
    public void wallsAndGates(int[][] rooms) {
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<rooms.length; i++) {
            for(int j=0; j<rooms[0].length; j++) {
                if(rooms[i][j]==0) {
                    queue.offer(new int[]{i,j});
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int[] dir: directions ) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];

                if(x < 0 || y<0 || x>=rooms.length || y>=rooms[0].length || rooms[x][y]!=Integer.MAX_VALUE)
                    continue;
                rooms[x][y] = rooms[curr[0]][curr[1]]+1;
                queue.offer(new int[]{x, y});
            }
        }
    }
}
