/*
    1202. Smallest String With Swaps
    You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
    You can swap the characters at any pair of indices in the given pairs any number of times.
    Return the lexicographically smallest string that s can be changed to after using the swaps.

    Example 1:
        Input: s = "dcab", pairs = [[0,3],[1,2]]
        Output: "bacd"
        Explaination: 
        Swap s[0] and s[3], s = "bcad"
        Swap s[1] and s[2], s = "bacd"

    Example 2:
        Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
        Output: "abcd"
        Explaination: 
        Swap s[0] and s[3], s = "bcad"
        Swap s[0] and s[2], s = "acbd"
        Swap s[1] and s[2], s = "abcd"

    Example 3:
        Input: s = "cba", pairs = [[0,1],[1,2]]
        Output: "abc"
        Explaination: 
        Swap s[0] and s[1], s = "bca"
        Swap s[1] and s[2], s = "bac"
        Swap s[0] and s[1], s = "abc"

    Constraints:
        1 <= s.length <= 10^5
        0 <= pairs.length <= 10^5
        0 <= pairs[i][0], pairs[i][1] < s.length
        s only contains lower case English letters.
 */
package Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class P1202_ToDo_Medium {
    
    /*
     * Solution 1: DFS
     * time complexity: O(E+VlogV)
     *      O(E) for building the adjacnecy list
     *      During the DFS traversal, each vertex will only be visited once. This is because we mark each vertex as visited as soon as we see it, and then we only visit vertices that are not marked as visited.
     *      When we iterate over the edge list of each vertex, we look at each edge once. This has a total cost of O(V+E)
     *      Additionally, we also sort lists. In the worst case, all of the vertices in the graph belong to the same component. In that case, sorting two lists of V elements will take O(VlogV).
     * space complexity: O(E+V)
     *      adjacency list will take O(E) space. visited[], adjLoc and adjChar can take O(V) space in the worst case. Also, the run time stack for DFS will use O(V)
     */
    List<List<Integer>> adj = new ArrayList<>();
    boolean[] visited;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        visited= new boolean[n];
        for(int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }
        for(List<Integer> p:pairs) {
            adj.get(p.get(0)).add(p.get(1));
            adj.get(p.get(1)).add(p.get(0));
        }

        char[] answer = new char[n];
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                List<Character> charpair = new ArrayList<>();
                List<Integer> idxpair = new ArrayList<>();

                dfs(i, charpair, idxpair, s);

                Collections.sort(charpair);
                Collections.sort(idxpair);

                for(int j=0; j<charpair.size(); j++) {
                    answer[idxpair.get(j)] = charpair.get(j);
                }
            }
        }
        return new String(answer);
    }

    private void dfs(int node, List<Character> charpair, List<Integer> idxpair, String s) {
        visited[node] = true;
        charpair.add(s.charAt(node));
        idxpair.add(node);

        for(int i=0; i<adj.get(node).size(); i++) {
            int next = adj.get(node).get(i);
            if(!visited[next]) {
                dfs(next, charpair, idxpair, s);
            }
        }
    } 

    /*
     * Solution 2: UnionFind
     * 
     * time complexity: O((E+V)⋅α(V)+VlogV)
     * space complexity: O(V)
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        UnionFind uf = new UnionFind(n);
        for(List<Integer> p: pairs) {
            uf.union(p.get(0), p.get(1));
        }

        Map<Integer, List<Integer>> components = new HashMap<>();
        for(int i=0; i<n; i++) {
            int root = uf.find(i);
            components.putIfAbsent(root, new ArrayList<>());
            components.get(root).add(i);
        }

        char[] answer = new char[n];
        for(List<Integer> comp : components.values()) {
            List<Character> listChar = new ArrayList<>();
            for(int ch : comp) {
                listChar.add(s.charAt(ch));
            }
            Collections.sort(listChar);

            for(int i=0; i<comp.size(); i++) {
                answer[comp.get(i)] = listChar.get(i);
            }
        }
        return new String(answer);
    }
}

class UnionFind {
    int[] rank;
    int[] root;

    public UnionFind(int size) {
        rank = new int[size];
        root = new int[size];
        for(int i=0; i<size; i++) {
            root[i] = i;
        }
    }

    public int find(int x) {
        if(root[x]!=x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    public void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if(rx!=ry) {
            if(rank[rx]<rank[ry]) {
                root[rx] = ry;
            } else if(rank[rx]>rank[ry]) {
                root[ry] = rx;
            } else {
                root[ry] = rx;
                rank[rx]++;
            }
        }
    }
}