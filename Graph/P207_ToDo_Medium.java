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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 
     * Intuition:
     *    We can see that we have been given certain courses with some dependecies between them. The dependencies are expressed as pairs, which provides some hints for framing the problem in terms of a graph.
     *    If we regard each course as a node and draw and endge from bi to ai for any prerequisite [ai, bi] (to indicate that course bi should be completed before taking course ai), we get a directed graph.
     *    If there is a cycle in this directed graph, it suggests that we will not be able to finish all of the courses. 
     *    Otherwise, we can perform a topological sort of the graph to determine the order in which all of the courses can be finished. 
     *    As a result, the problem is reduced to determining whether a cycle occurs in a graph. If there is a cycle, we must return false. If not, we return true.
     * 
     * A topological sort or topological ordering of a directed graph is a linear ordering of its vertices such that for every directed edge u->v from vertex u to vertex v, u comes before v in the ordering.
     * In a directed acyclic graph, we can use Kahn's algorithm to get the topological ordering. 
     * Kahn's algorithm works by keeping track of the number of incoming edges into each node (indegree). 
     * It works by repreatedly visiting the nodes with an indgree of zero and deleting all the edges associated with it leading to a decrement of indegree for the nodes whose incoming edges are deleted. 
     * This process continues until no elements with zero indegree can be found. 
     *      The advantage of using Kahn's algorithm is that it also aids in the detection of graph cycles. 
     * 
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
        //List<Integer> track = new ArrayList<>();
        while(!queue.isEmpty()) {
            int next = queue.poll();
            nodecount++;
            //track.add(next);

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

    /*
     * Solution: BFS with using hash map
     * time complexity: O(n+m), n be the number of courses(numCourses) and m be the size of prerequisite pairs.
     * space complexity: O(n+m), queue and beingPrecourses array store O(n) and linked map stores at most m (prerequisite pairs)
     */
    public boolean canFinish_withoutQueue(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courseCharts = new HashMap<>();
        int[] preCourses = new int[numCourses];
        for(int[] pair: prerequisites) {
            int target = pair[0];
            int pre = pair[1];
            courseCharts.putIfAbsent(target, new ArrayList<>());
            courseCharts.get(target).add(pre);
            preCourses[pre]++;
        }

        Deque<Integer> targetList = new ArrayDeque<>();
        for(int i=0; i<numCourses; i++) {
            if(preCourses[i]==0) {
                targetList.add(i);
            }
        }
        while(!targetList.isEmpty()) {
            int target = targetList.remove();
            for(int pre: courseCharts.getOrDefault(target, new ArrayList<>())) {
                preCourses[pre]--;
                if(preCourses[pre] == 0) {
                    targetList.add(pre);
                }
            }
            numCourses--;
        }
        return numCourses == 0;
    }
}
