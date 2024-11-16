/*
    56. Merge Intervals
    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

    Example 1:
        Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
        Output: [[1,6],[8,10],[15,18]]
        Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

    Example 2:
        Input: intervals = [[1,4],[4,5]]
        Output: [[1,5]]
        Explanation: Intervals [1,4] and [4,5] are considered overlapping.
    

    Constraints:
        1 <= intervals.length <= 10^4
        intervals[i].length == 2
        0 <= starti <= endi <= 10^4
 */

package Array_String.Array_Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class P56_Medium {
    /*
     * Solution: Sorting
     * 
     * time compelxity: O(nLogN)
     * space complexity: O(n)
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> result = new ArrayList<>();
        int[] curr = intervals[0];
        result.add(curr);

        for (int[] pair: intervals) {
            int curr_end = curr[1];
            int next_start = pair[0];
            int next_end = pair[1];

            if(curr_end >= next_start) {
                curr[1] = Math.max(curr_end, next_end);
            } else {
                curr = pair;
                result.add(pair);
            }
        }
    
        return result.toArray(new int[result.size()][]);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        int[] prev = intervals[0];
        for(int i=1; i<intervals.length; i++) {
            int[] curr = intervals[i];

            if( prev[1] >= curr[0]) {
                prev[1] = Math.max(prev[1], curr[1]);
            } else {
                list.add(prev);
                prev = curr;
            }
        }
        list.add(prev);
        return list.toArray(new int[list.size()][]);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        for(int i=1; i<intervals.length; i++) {
            if(list.getLast()[1]>= intervals[i][0]) {
                list.getLast()[1] = Math.max(list.getLast()[1], intervals[i][1]);
            } else {
                list.add(intervals[i]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    /*
     * Solution: using linked list
     * time complexity: O(nlogn)
     * space complexity: O(n)
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0], b[0]));
        LinkedList<int[]> list = new LinkedList<>();
        for(int[] inter: intervals) {
            if(list.isEmpty() || list.getLast()[1] < inter[0]) {
                list.add(inter);
            } else {
                list.getLast()[1] = Math.max(inter[1], list.getLast()[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
