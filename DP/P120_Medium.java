/*
    120. Triangle
    Given a triangle array, return the minimum path sum from top to bottom.
    For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

    Example 1:
    Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
    Output: 11
    Explanation: The triangle looks like:
    2
    3 4
    6 5 7
    4 1 8 3
    The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).

    Example 2:
    Input: triangle = [[-10]]
    Output: -10
    
    Constraints:
        1 <= triangle.length <= 200
        triangle[0].length == 1
        triangle[i].length == triangle[i - 1].length + 1
        -10^4 <= triangle[i][j] <= 10^4
    
    Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */
package DP;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P120_Medium {
    /*
     * Solution: Bottom-up DP
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> prev = triangle.get(0);
        for(int i=1; i<triangle.size(); i++) {
            List<Integer> curr = triangle.get(i);
            for(int j=0; j<=i; j++) {
                int smallestAbove = Integer.MAX_VALUE;
                if(j>0) {
                    smallestAbove = prev.get(j-1);
                } 
                if(j<i) {
                    smallestAbove = Math.min(smallestAbove, prev.get(j));
                }
                curr.add(smallestAbove + triangle.get(i).get(j));
            }
            prev = curr;
        }
        return Collections.min(prev);
    }

    /*
     * Solution: bottom-up dp - upside down
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        for(int i = triangle.size()-2; i>=0; i--) {
            for(int j=0; j<=i; j++) {
                int smallest = Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1));
                triangle.get(i).set(j, smallest + triangle.get(i).get(j));
            }
        }
        return  triangle.get(0).get(0);
    }

     /*
     * Solution: Bottom-up DP - initial approach
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> prev = triangle.get(0);
        for(int i=1; i<triangle.size(); i++) {
            List<Integer> curr = triangle.get(i);
            for(int j=0; j<=i; j++) {
                int smallest = Integer.MAX_VALUE;
                if(j>0) {
                    smallest = prev.get(j-1);
                }
                if(j<i) {
                    smallest = Math.min(smallest, prev.get(j));
                }
                curr.set(j, curr.get(j)+smallest);
            }
            prev = curr;
        }
        return Collections.min(prev);
    }
    
    /*
     * Solution: top-down dp - memoization
     * time complexity: O(n^2)
     * space complexity: O(n^2)
     *      There is O(n) space on the run-time stack. 
     *      Each time a subproblem is solved (a call to minPath), its result is stored in a memoization table. We determined above that there are O(n^2) such subproblems, giving a total space complexity of O(n^2) for the memoization table.
     */
    private Map<String, Integer> memo;
    private List<List<Integer>> triangle;
    public int minimumTotal(List<List<Integer>> triangle) {
        this.triangle = triangle;
        memo = new HashMap<>();
        return minPath(0, 0);
    }

    int minPath(int i, int j) {
        String param = i+":"+j;
        if(memo.containsKey(param)) return memo.get(param);
        int path = this.triangle.get(i).get(j);
        if(i<triangle.size()-1) {
            path+= Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1));
        }
        memo.put(param, path);
        return path;
    }
}
