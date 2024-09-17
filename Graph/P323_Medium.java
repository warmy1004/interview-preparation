/*
    323. Number of Connected Components in an Undirected Graph
    You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
    Return the number of connected components in the graph.

    Example 1:
        Input: n = 5, edges = [[0,1],[1,2],[3,4]]
        Output: 2

    Example 2:
        Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
        Output: 1

    Constraints:
        1 <= n <= 2000
        1 <= edges.length <= 5000
        edges[i].length == 2
        0 <= ai <= bi < n
        ai != bi
        There are no repeated edges.
 */

package Graph;

import java.util.ArrayList;

public class P323_Medium {
    /*
     * Solution: Union Find // Disjoing Set Union (DSU)
     * time complexity: O(V+E⋅α(n)), E is number of edges, V is number of vertices
     *      iterating over every edge requires O(E) ops, and for every operation, we are performing the union() which is O(α(n)), where α(n) is the inverse Ackermann function.
     *      We also require O(V) time to initialize the DSU array
     * space complexity: O(V)
     */
    public int countComponents_UF(int n, int[][] edges) {
        int[] root = new int[n];
        for(int i=0; i<n; i++) {
            root[i] = i;
        }

        for(int[] edge: edges) {
            int r1 = find(edge[0], root);
            int r2 = find(edge[1], root);

            if(r1!=r2) {
                root[r1] = r2;
                n--;
            }
        }
        return n;
    }

    private int find(int x, int[] root) {
        if(root[x]!=x) {
            root[x] = find(root[x], root);
        }
        return root[x];
    }

    /*
     * Sollution: Union Find 2
     */
    public int countComponents_UF(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) {
            if(uf.union(edge[0], edge[1])) {
                n--;
            }
        }
        return n;
    }

    /*
     * Solution: DFS
     * time complexity: O(E+V)
     * space complexity: O(E+V)
     */
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] edge: edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int count = 0;
        boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                count++;
                dfs(i, visited, adj);
            }
        }
        return count;
    }

    private void dfs(int node, boolean[] visited, List<List<Integer>> adj) {
        visited[node] = true;

        for(int neighbor : adj.get(node)) {
            if( !visited[neighbor] ) {
                dfs(neighbor, visited, adj);
            }
        }
    }
}

// For solution 2
class UnionFind {
    int[] root;
    int[] rank;

    public UnionFind(int n) {
        root = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++) {
            root[i] = i;
        }
    }

    int find(int x) {
        if(root[x]!=x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    boolean union(int x, int y ) {
        int rx = find(x);
        int ry = find(y);

        if(rx==ry) {
            return false;
        } else {
            if(rank[rx]<rank[ry]) {
                root[rx] = ry;
            } else if (rank[ry] < rank[rx]) {
                root[ry] = rx;
            } else {
                root[ry] = rx;
                rank[rx]++;
            }
        }
        return true;
    }
}