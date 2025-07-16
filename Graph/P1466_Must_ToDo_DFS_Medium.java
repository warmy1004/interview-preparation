/*
    1466. Reorder routes to make all paths lead to the city zero
    There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
    Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
    This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
    Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
    It's guaranteed that each city can reach city 0 after reorder.

    Example 1:
        Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
        Output: 3
        Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

    Example 2:
        Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
        Output: 2
        Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

    Example 3:
        Input: n = 3, connections = [[1,0],[2,0]]
        Output: 0
    
    Constraints:
        2 <= n <= 5 * 10^4
        connections.length == n - 1
        connections[i].length == 2
        0 <= ai, bi <= n - 1
        ai != bi
 */
package Graph;

import java.util.ArrayList;
import java.util.List;

public class P1466_Must_ToDo_DFS_Medium {
    /*
     * Solution: DFS with List
     * 
     * Intuition:
     *  want to connect all to nodes into from nodes (child->parent). 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int minReorder(int n, int[][] connections) {
        List<List<Integer>> al = new ArrayList<>();
        for(int i=0; i<n; i++) {
            al.add(new ArrayList<>());
        }
        for(int[] conn: connections) {
            al.get(conn[0]).add(conn[1]);
            al.get(conn[1]).add(-conn[0]);
        }
        return dfs(al, new boolean[n], 0);
    }

    private int dfs(List<List<Integer>> al, boolean[] visited, int curr) {
        visited[curr] = true;
        int count = 0;
        for(int next: al.get(curr)) {
            if(!visited[Math.abs(next)]) {
                count += dfs(al, visited, Math.abs(next)) + (next>0? 1: 0); 
            }
        }
        return count;
    }

    /*
     * Solution
     */
    int minCount = 0;
    public int minReorder(int n, int[][] connections) {
        List<List<Pair<Integer, Integer>>> adj = new ArrayList<>();
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] conn: connections) {
            adj.get(conn[0]).add(new Pair<>(conn[1],1));
            adj.get(conn[1]).add(new Pair<>(conn[0], -1));
        }
        dfs(adj, new boolean[n], 0);
        return minCount;
    }

    void dfs(List<List<Pair<Integer, Integer>>> adj, boolean[] visited, int curr) {
        visited[curr] = true;
        for(Pair<Integer, Integer> neighbors: adj.get(curr)) {
            if(!visited[neighbors.getKey()]) {
                if(neighbors.getValue() == 1) {
                    minCount ++;
                }
                dfs(adj, visited, neighbors.getKey());
            }
        }
    }
}
