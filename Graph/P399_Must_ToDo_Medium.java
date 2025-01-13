/*
    399. Evaluate Division
    You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
    You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
    Return the answers to all queries. If a single answer cannot be determined, return -1.0.
    Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
    Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

    Example 1:
        Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
        Explanation: 
            Given: a / b = 2.0, b / c = 3.0
            queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
            return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
            note: x is undefined => -1.0

    Example 2:
        Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
        Output: [3.75000,0.40000,5.00000,0.20000]

    Example 3:
        Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
        Output: [0.50000,2.00000,-1.00000,-1.00000]
    
    Constraints:
        1 <= equations.length <= 20
        equations[i].length == 2
        1 <= Ai.length, Bi.length <= 5
        values.length == equations.length
        0.0 < values[i] <= 20.0
        1 <= queries.length <= 20
        queries[i].length == 2
        1 <= Cj.length, Dj.length <= 5
        Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
package BFS_DFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P399_Must_ToDo_Medium {
    /*
     * Solution: DFS && BFS
     * time complexity: O(n*m)
     * space complexity: O(n)
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = buildMap(equations, values);
        double[] answer = new double[queries.size()];
        for(int i=0; i<queries.size(); i++) {
            List<String> query = queries.get(i);
            String a = query.get(0), b = query.get(1);
            Set<String> visited = new HashSet<>();
            answer[i] = dfs(a, b, map, visited);
            // answer[i] = bfs(a, b, map);
        }
        return answer;
    }

    double dfs(String src, String dst, Map<String, Map<String, Double>> map, Set<String> visited) {
        if(!map.containsKey(src) || !map.containsKey(dst)) {
            return -1.0;
        }
        if(src.equals(dst)) {
            return 1.0;
        }
        visited.add(src);
        Map<String, Double> neighbors = map.get(src);
        for(Map.Entry<String, Double> nb : neighbors.entrySet()) {
            String nextNode = nb.getKey();
            if(!visited.contains(nextNode)) {
                double nextValue = dfs(nextNode, dst, map, visited);
                if(nextValue != -1.0) {
                    return nextValue * nb.getValue();
                }
            }
        }
        return -1.0;
    }

    double bfs(String src, String dst, Map<String, Map<String, Double>> map) {
        if(!map.containsKey(src) || !map.containsKey(dst)) return -1.0;
        if(src.equals(dst)) return 1.0;

        Deque<Pair<String, Double>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(src, 1.0));
        Set<String> visited = new HashSet<>();
        while(!queue.isEmpty()) {
            Pair<String, Double> pair = queue.remove();
            String node = pair.getKey();
            Double value = pair.getValue();

            // to find [a,c], a->b->c
            if(node.equals(dst)) return value;

            visited.add(node);
            for(Map.Entry<String, Double> neighbor: map.get(node).entrySet()) {
                String connectedNode = neighbor.getKey();
                Double connectedValue = neighbor.getValue();
                if(!visited.contains(connectedNode)) {
                    queue.add(new Pair<>(connectedNode, value*connectedValue));
                }
            }
        }
        return -1.0;

    }

    Map<String, Map<String, Double>> buildMap(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for(int i=0; i<equations.size(); i++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0), b=equation.get(1);
            map.putIfAbsent(a, new HashMap<>());
            map.get(a).put(b, values[i]);
            map.putIfAbsent(b, new HashMap<>());
            map.get(b).put(a, 1.0/values[i]);
        }
        return map;
    }
}
