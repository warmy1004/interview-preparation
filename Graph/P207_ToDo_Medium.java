/*
    207. Course Schedule
    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return true if you can finish all courses. Otherwise, return false.

    Example 1:
        Input: numCourses = 2, prerequisites = [[1,0]]
        Output: true
        Explanation: There are a total of 2 courses to take. 
        To take course 1 you should have finished course 0. So it is possible.

    Example 2:
        Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
        Output: false
        Explanation: There are a total of 2 courses to take. 
        To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

    Constraints:
        1 <= numCourses <= 2000
        0 <= prerequisites.length <= 5000
        prerequisites[i].length == 2
        0 <= ai, bi < numCourses
        All the pairs prerequisites[i] are unique.
 */
package Graph;

import java.util.ArrayList;

public class P207_ToDo_Medium {

    /*
     * Solution : DFS
     * 
     * time complexity: O(n+m), n be the number of courses and m be the size of prerequisites.
     * space complexity: O(n+m), 
     *      adj list takes O(m) space. 
     *      visited and inroute arrays take O(n) space each.
     *      dfs recursion call stack can have no more than n elements in the worst case. It would take up O(n) space.
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] p: prerequisites) {
            adj.get(p[1]).add(p[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] inroute = new boolean[numCourses];
        for(int i=0; i<numCourses; i++) {
            if(dfs_existcycle(i, visited, inroute, adj)) {
                return false;
            }
        }
        return true;
    }
    private boolean dfs_existcycle(int node, boolean[] visited, boolean[] inroute, List<List<Integer>> adj) {
        if(inroute[node]) return true;
        if(visited[node]) return false;
        inroute[node] = true;
        visited[node] = true;
        for(int x: adj.get(node)) {
            if(dfs_existcycle(x, visited, inroute, adj)) {
                return true;
            }
        }
        // remove the node from the stack, because we checked there is no cycle.
        inroute[node] = false;
        return false;
    }
 
    /*
     * Solution 2: Topological sort using Kahn's algorithm with Queue + print the node
     * time complexity: O(V+E)
     * space complexity: O(V+E)
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0; i<numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] p: prerequisites) {
            adj.get(p[1]).add(p[0]);
            indegree[p[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<numCourses; i++) {
            if(indegree[i]==0) {
                queue.add(i);
            }
        }

        int nodecount = 0;
        List<Integer> track = new ArrayList<>();
        while(!queue.isEmpty()) {
            int next = queue.poll();
            nodecount++;
            track.add(next);

            for(int x: adj.get(next)) {
                indegree[x]--;
                if(indegree[x]==0) {
                    queue.add(x);
                }
            }
        }
        return nodecount == numCourses;

        // return nodecount == numCourses? track.stream().mapToInt(Integer::intValue).toArray() : new int[0];
    }

    /*
     * Solution: Without Queue
     */
    public boolean canFinish_withoutQueue(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int p = prerequisites.length;
        for(int[] pair: prerequisites) {
            indegree[pair[1]]++;
        }

        boolean[] visited = new boolean[p];
        boolean found = true;

        while (found) {
            found = false;
            for(int i=0; i<p; i++) {
                if(!visited[i] && indegree[prerequisites[i][0]]==0) {
                    visited[i] = true;
                    indegree[prerequisites[i][1]]--;
                    found = true;
                }
            }
        }

        for (int elmt: indegree) {
            if(elmt!=0) {
                return false;
            }
        }
        return true;
    }
}
