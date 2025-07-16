/*
    739. Daily Temperatures
    Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

    Example 1:
        Input: temperatures = [73,74,75,71,69,72,76,73]
        Output: [1,1,4,2,1,1,0,0]

    Example 2:
        Input: temperatures = [30,40,50,60]
        Output: [1,1,1,0]

    Example 3:
        Input: temperatures = [30,60,90]
        Output: [1,1,0]
    
    Constraints:
        1 <= temperatures.length <= 10^5
        30 <= temperatures[i] <= 100
 */
package Stack_Queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class P739_Medium {
    /*
     * Solution: using Stack - forward
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            int curr = temperatures[i];
            while(!stack.isEmpty() && temperatures[stack.peek()] < curr) {
                int prev = stack.pop();
                answer[prev] = i-prev;
            }
            stack.push(i);
        }
        return answer;
    }

    /*
     * Solution: using Stack - bakcward
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=n-1; i>=0; i--) {
            while(!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            if(!stack.isEmpty()) {
                answer[i] = stack.peek()-i;
            }
            stack.push(i);
        }
        return answer;
    }

    /*
     * Solution: using array
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        int max = 0;

        for(int i=n-1; i>=0; i--) {
            int curr = temperatures[i];
            if(curr >= max) {
                max = curr;
                continue;
            }
            int days = 1;
            while(temperatures[i+days]<=curr) {
                days += answer[i+days];
                //days++; -> generally works, but TLE with the long array of temperatures
            }
            answer[i] = days;
        }
        return answer;
    }
}
