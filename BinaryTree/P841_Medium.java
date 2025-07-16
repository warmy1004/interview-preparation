/*
    841. Keys and Rooms
    There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
    When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
    Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.

    Example 1:
        Input: rooms = [[1],[2],[3],[]]
        Output: true
        Explanation: 
        We visit room 0 and pick up key 1.
        We then visit room 1 and pick up key 2.
        We then visit room 2 and pick up key 3.
        We then visit room 3.
        Since we were able to visit every room, we return true.

    Example 2:
        Input: rooms = [[1,3],[3,0,1],[2],[0]]
        Output: false
        Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
    
    Constraints:
        n == rooms.length
        2 <= n <= 1000
        0 <= rooms[i].length <= 1000
        1 <= sum(rooms[i].length) <= 3000
        0 <= rooms[i][j] < n
        All the values of rooms[i] are unique.
 */
package BinaryTree;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class P841_Medium {
    /*
     * Solution: DFS, Stack + array
     * 
     * Intuition: When visiting a room for the first time, look at all the keys in that room. For any key that hasn't been used yet, add it to the stack for it to be used.
     * 
     * time complexity: O(n+e), where n is the number of rooms and e is the total number of keys
     * space complexity: O(n)
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while(!stack.isEmpty()) {
            int curr = stack.pop();
            for(int key: rooms.get(curr)) {
                if(!visited[key]) {
                    visited[key] = true;
                    stack.push(key);
                }
            }
        }

        for(boolean i : visited) {
            if(!i) return false;
        }
        return true;
    }

    /*
     * Solution: DFS, Queue + hashSet
     * time complexity: O(n+e)
     * space complexity: O(n)
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int key: rooms.get(curr)) {
                if(!visited.contains(key)) {
                    visited.add(key);
                    queue.offer(key);
                }
            }
        }
        return visited.size() == rooms.size();
    }

    /*
     * Solution: DFS
     * time complexity:
     * space complexity:
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(rooms, visited, 0);
        for(boolean val: visited) {
            if(val == false) return false;
        }
        return true;
    }
    private void dfs(List<List<Integer>> rooms, boolean[] visited, int curr) {
        visited[curr] = true;
        for(int i: rooms.get(curr)) {
            if(visited[i]==false){
                dfs(rooms, visited, i);
            }
        }
    }
}
