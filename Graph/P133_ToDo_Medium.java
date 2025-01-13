/*
    133. clone graph
    Given a reference of a node in a connected undirected graph.
    Return a deep copy (clone) of the graph.
    Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
        class Node {
            public int val;
            public List<Node> neighbors;
        }
    
    Test case format:
        For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
        An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
        The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

    Example 1:
        Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
        Output: [[2,4],[1,3],[2,4],[1,3]]
        Explanation: There are 4 nodes in the graph.
            1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
            2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
            3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
            4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

    Example 2:
        Input: adjList = [[]]
        Output: [[]]
        Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.

    Example 3:
        Input: adjList = []
        Output: []
        Explanation: This an empty graph, it does not have any nodes.

    Constraints:
        The number of nodes in the graph is in the range [0, 100].
        1 <= Node.val <= 100
        Node.val is unique for each node.
        There are no repeated edges and no self-loops in the graph.
        The Graph is connected and all nodes can be visited starting from the given node.
 */
package BFS_DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P133_ToDo_Medium {
    /* 
     * Solution : DFS - v1
     * time complexity: O(n+m), where n is a number of nodes and m is a number of edges
     * space complexity: O(n)
     */
    Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        if(visited.containsKey(node)) {
            return visited.get(node);
        }
        Node clone = new Node(node.val, new ArrayList<>());
        visited.put(node, clone);
        for(Node neighbor: node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }

    /* 
     * Solution : DFS - v2
     * time complexity: O(n+m), where n is a number of nodes and m is a number of edges
     * space complexity: O(n)
     */
    public Node cloneGraph(Node node) {
        Map<Node, Node> visited = new HashMap<>();
        return dfs(node, visited);
    }

    Node dfs(Node node, Map<Node, Node> visited) {
        if(node == null) return null;
        if(visited.containsKey(node)) return visited.get(node);
        Node copy = new Node(node.val, new ArrayList<>());
        visited.put(node, copy);
        for(Node neighbor: node.neighbors) {
            copy.neighbors.add(dfs(neighbor, visited));
        }
        return copy;
    }

    /*
     * Solution: BFS
     * time complexity: O(n+m), where n is a number of nodes (vertices) and m is a number of edges
     * space complexity: O(n)
     */
    public Node cloneGraph(Node node) {
        if(node==null) return null;
        Map<Node, Node> visited = new HashMap<>();
        visited.put(node, new Node(node.val, new ArrayList<>()));
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            Node curr = queue.remove();
            for(Node n: curr.neighbors) {
                if(!visited.containsKey(n)) {
                    visited.put(n, new Node(n.val, new ArrayList<>()));
                    queue.add(n);
                }
                visited.get(curr).neighbors.add(visited.get(n));
            }
        }
        return visited.get(node);
    }
}

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}