/*
    1306. Jump Game 3
    Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
    Notice that you can not jump outside of the array at any time.

    Example 1:
        Input: arr = [4,2,3,0,3,1,2], start = 5
        Output: true
        Explanation: 
        All possible ways to reach at index 3 with value 0 are: 
        index 5 -> index 4 -> index 1 -> index 3 
        index 5 -> index 6 -> index 4 -> index 1 -> index 3 

    Example 2:
        Input: arr = [4,2,3,0,3,1,2], start = 0
        Output: true 
        Explanation: 
        One possible way to reach at index 3 with value 0 is: 
        index 0 -> index 4 -> index 1 -> index 3

    Example 3:
        Input: arr = [3,0,2,1,2], start = 2
        Output: false
        Explanation: There is no way to reach at index 1 with value 0.
    
    Constraints:
        1 <= arr.length <= 5 * 10^4
        0 <= arr[i] < arr.length
        0 <= start < arr.length
 */
package DP;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import javax.management.Query;

public class P1306_Medium {
    /*
     * Solution: BFS with stack
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        Set<Integer> visited = new HashSet<>();
        visited.add(start);

        while(!stack.isEmpty()) {
            int currIdx = stack.pop();
            int after = currIdx + arr[currIdx];
            int pre = currIdx - arr[currIdx];

            if(after<n && arr[after]==0 || pre>=0 && arr[pre]==0) {
                return true;
            }

            if(after<n && !visited.contains(after)) {
                stack.push(after);
                visited.add(after);
            }
            if(pre>=0&& !visited.contains(pre)) {
                stack.push(pre);
                visited.add(pre);
            }
        }
        return false;
    }
    
    /*
     * Solution: BFS with queue
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while(!queue.isEmpty()) {
            int idx = queue.poll();
            if(arr[idx]<0) continue;
            if(arr[idx] == 0) return true;
            if(idx - arr[idx]>=0) {
                queue.offer(idx-arr[idx]);
            }
            if(idx+arr[idx] < n) {
                queue.offer(idx+arr[idx]);
            }
            arr[idx] = -arr[idx];
        }
        return false;
    }

    /*
     * Solution: DFS
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public boolean canReach(int[] arr, int start) {
        if(start>=0 && start < arr.length && arr[start]>=0) {
            if(arr[start]==0) return true;
            arr[start] = -arr[start];
            return canReach(arr, start+arr[start]) || canReach(arr, start-arr[start]);
        }
        return false;
    }
}
