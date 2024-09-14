/*
    547. Number of Provinces
    There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
    A province is a group of directly or indirectly connected cities and no other cities outside of the group.
    You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
    Return the total number of provinces.

    Example 1:
        Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
        Output: 2

    Example 2:
        Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
        Output: 3

    Constraints:
        1 <= n <= 200
        n == isConnected.length
        n == isConnected[i].length
        isConnected[i][j] is 1 or 0.
        isConnected[i][i] == 1
        isConnected[i][j] == isConnected[j][i]
 */

package UnionFind_DFS_BFS;

/*
    Similar Questions:
        323. Number of Connected Components in an undirected graph - Medium
        657. robot return to origin - Easy
        734. setence similarity - Easy
        737. setence similarity 2 - medium
        1101. the earlist moment when everyone become friends - Medium
        2101. detonate the maximum bombs - Medium
 */

public class P547_ToDo_Medium {
    /*
     * Soution : DFS
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                provinces++;
                updateVisited_dfs(i, isConnected, visited);
            }
        }
        return provinces;
    }
    private void updateVisited_dfs(int node, int[][] isConnected, boolean[] visited) {
        visited[node] = true;
        for(int i = 0; i<isConnected.length; i++) {
            if(!visited[i] && isConnected[node][i] == 1) {
                updateVisited_dfs(i, isConnected, visited);
            }
        }
    }

    /*
     * Soution : Union-Find
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    public int findCircleNum_UF(int[][] isConnected) {
        int n = isConnected.length;
        UnionFind uf = new UnionFind(n);
        int provinces = n;

        for(int i=0; i<n; i++) {
            for (int j = i+1; j<n; j++) {
                if(isConnected[i][j] == 1 && uf.find(i)!=uf.find(j)) {
                    uf.union(j, j);
                    provinces--;
                }
            }
        }
        return provinces;
    }

    class UnionFind{
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            for(int i= 0; i<n; i++) {
                parent[i] = i;
            }
            rank = new int[n];

        }

        int find(int i) {
            if(parent[i]!=i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if(rootx != rooty) {
                if (rank[rootx]<rank[rooty]) {
                    parent[rootx] = rooty;
                } else if(rank[rootx]> rank[rooty]) {
                    parent[rooty] = rootx;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx]++;
                }
            }
        }
    }

     /*
     * Soution : Union-Find combined
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    int[] root;
    int[] rank;

    public int findCircleNum_combinedUF(int[][] isConnected) {
        int n = isConnected.length;
        root = new int[n];
        rank = new int[n];
        for(int i= 0; i<n; i++) {
            root[i] = i;
        }
        int provinces = n;
        for (int i = 0; i<n; i++) {
            for(int j = i+1; j<n; j++) {
                if(isConnected[i][j]==1) {
                    provinces -= unionNodes(i,j);
                }
            }
        }
        return provinces;
    }

    private int unionNodes(int i, int j) {
        int ri = find(i);
        int rj = find(j);

        if(ri!=rj) {
            if(rank[ri] < rank[rj]) {
                root[ri] = rj;
            } else if(rank[ri]> rank[rj]) {
                root[rj] = ri;
            } else {
                root[rj] = ri;
                rank[ri]++;
            }
        } else { 
            return 0;
        }
        return 1;
    }
    private int find(int i) {
        if(root[i]!=i) {
            root[i] = find(root[i]);
        }
        return root[i];
    }
}




/*
 * Python
 */
 class Solution:
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        n = len(isConnected)
        visited=set()

        def dfs(node, isConnected, visited):
            visited.add(node)
            for i in range(len(isConnected[node])):
                if i not in visited and isConnected[node][i] == 1:
                    dfs(i, isConnected, visited)
        
        count =0
        for i in range(n):
            if i not in visited:
                count+=1
                dfs(i, isConnected, visited)
        return count