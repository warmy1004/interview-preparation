/*
    119. Pascal's Triangle 2
    Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
    In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

    Example 1:
        Input: rowIndex = 3
        Output: [1,3,3,1]

    Example 2:
        Input: rowIndex = 0
        Output: [1]

    Example 3:
        Input: rowIndex = 1
        Output: [1,1]
    
    Constraints:
     0 <= rowIndex <= 33

    Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 */

package DP;

import java.util.ArrayList;

public class P119_Easy {
    /*
     * Solution 1: brute force DP
     * 
     * time complexity: O(N^2)
     */
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(new ArrayList<Integer>(){
            {
                add(1);
            }
        });
        for(int i=1; i<=rowIndex; i++) {
            List<Integer> prev = dp.get(i-1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for(int j=1; j<i; j++) {
                row.add(prev.get(j-1)+prev.get(j));
            }
            row.add(1);
            dp.add(row);
        }
        return dp.get(rowIndex);
    }

    /*
     * Solution 2: memory efficient DP
     * 
     * time complexity: O(N^2)
     * space complexity: O(k)
     */
    public List<Integer> getRow_dp2(int rowIndex) {
        List<Integer> answer = new ArrayList<>() {
            {
                add(1);
            }
        };

        for(int i=0; i<rowIndex; i++) {
            for(int j=i; j>0; j--) {
                answer.set(j, answer.get(j-1)+answer.get(j));
            }
            answer.add(1);
        }
        return answer;
    }
}
