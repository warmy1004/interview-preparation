/*
    261. Graph valid tree
    You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
    Return true if the edges of the given graph make up a valid tree, and false otherwise.

    Example 1:
        Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
        Output: true

    Example 2:
        Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
        Output: false
    
    Constraints:
        1 <= n <= 2000
        0 <= edges.length <= 5000
        edges[i].length == 2
        0 <= ai, bi < n
        ai != bi
        There are no self-loops or repeated edges.
 */

package Graph;

import java.util.ArrayList;
import java.util.HashSet;

import Graph.P547_ToDo_Medium.UnionFind;


public class P261_UF_ToDo_Medium {
    /*
     * Solution 1: UF
     * time compelxity: O(N⋅α(N)), N be the number of nodes and α(N) is the Inverse Ackermann function. 
     *      find()'s cost is dependent on how far the node it was searching for is from the root. Using the naive implementation of union find, this depth could be N. 
     *      If this was the case for all the calls, we'd have a final cost of O(n^2). 
     *      However, we did optimizations. Those keep the tree depths very shallow. It turns out that find() amortizes to O(α(N)), where α is the Inverse Ackermann Function. 
     *      The incredible thing about this function is that it grows so slowly that N will never go higher than 4 in the universe as we know it. So while in pratice it is effectively O(1), in theory it's not.
     *      N*O(α(N)) = O(N*α(N))
     * space complexity: O(N)
     */
    public boolean validTree_UF(int n, int[][] edges) {
        if(edges.length != n-1) return false;
        UnionFind uf = new UnionFind(n);

        for(int[] edge: edges) {
            if(!uf.union(edge[0], edge[1])) {
                return false;
            }
        }
        return true;
    }

    /*
     * Soltuion 2: graph theory + recursive DFS
     *      For the graph to be a valid tree, it must have exactly n-1 edges. Any less, and it can't possibly be fully connected. Any more, and it has to contain cycles. 
     *      Additionally, if the graph is fully connected and contains exactly n-1 edges, it can't possible contain a cycle and therefore must be a tree.
     *          1. Check whether or not there are n-1 edges. If there's not, then return false.
     *          2. Check whether or not the graph is fully connected. Return true if it is, false if otherwise. 
     *              --> this is very straightforward; G is fully connected if, and only if, we started a DFS from a single source and discovered all nodes in G during it.
     *          3. Check whether or not the graph has cycles.
     *              --> G contains no cycles if, and only if, the DFS never goes back to an already discovered node. we need to be careful though not to count trivial cycles of the form A->B->A
     *                  that occur with most implementations of undirected edges.
     * 
     * time complexity: O(N)
     *      creating an adjacency list has a time complexity of O(N+E). Because E is now bounded by N, we can reduce this slightly to O(N+N) = O(N).
     *      "neighbor" loop runs only once for each node. Therefore, in total, the function is called once for each edge. So, it is called E=N$$$$$E = N times, and N of those times, 
     *      it actually enters the "neighbor" loop. Collectively, the total number of iterations of the 'neighbor' loop is E=N. So, we get O(N).
     * space complexity: O(N)
     */
    List<List<Integer>> adj = new ArrayList<>();
    Set<Integer> seen = new HashSet<>();

    public boolean validTree_DFS(int n, int[][] edges) {
       if (edges.length != n-1) return false;
       for (int i = 0; i<n; i++) {
            adj.add(new ArrayList<>());
       }

       for(int[] e: edges) {
        adj.get(e[0]).add(e[1]);
        adj.get(e[1]).add(e[0]);
       }

       dfs(0);
       return seen.size()==n;
    }

    void dfs(int node) {
        if(seen.contains(node)) return;
        seen.add(node);
        for(int neighbor: adj.get(node)) {
            dfs(neighbor);
        }
    }
}

/*
 * For Solution 1
 */
class UnionFind {
    int[] root;
    int[] rank;

    public UnionFind(int n) {
        root = new int[n];
        rank = new int[n];

        for (int i=0; i<n; i++) {
            root[i]=i;
        }
    }

    int find(int x) {
        if(root[x]!=x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    boolean union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if(rx==ry) {
            return false;
        } else {
            if(rank[rx]<rank[ry]) {
                root[rx] = ry;
            } else if(rank[rx]>rank[ry]) {
                root[ry] = rx;
            } else {
                root[ry] = rx;
                rank[rx]++;
            }
        }
        return true;
    }
}
