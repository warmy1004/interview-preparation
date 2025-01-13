/*
    57. Insert Interval
    You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
    Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
    Return intervals after the insertion.
    Note that you don't need to modify intervals in-place. You can make a new array and return it.

    Example 1:
        Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
        Output: [[1,5],[6,9]]

    Example 2:
        Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        Output: [[1,2],[3,10],[12,16]]
        Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

    Constraints:
        0 <= intervals.length <= 10^4
        intervals[i].length == 2
        0 <= starti <= endi <= 10^5
        intervals is sorted by starti in ascending order.
        newInterval.length == 2
        0 <= start <= end <= 10^5
 */
package Array_String.Array_Sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class P57_Must_ToDo_Medium {
    /*
     * Solution: linear search
     * time complexity: O(n)
     * space complexity: O(n) or O(1); we only use the list array to store output, so this could be considered O(1)
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length; 
        int i= 0;
        List<int[]> list = new ArrayList<>();
        while(i<n && newInterval[0]>intervals[i][1]) {
            list.add(intervals[i++]);
        }

        /*
            Overlapping and merging
            this occurs when the starting point of the current intervals (intervals[i][0]) is less than or equal to the ending point of the newInterval(newInterval[1])
        */
        while(i<n && newInterval[1]>=intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        list.add(newInterval);

        while(i<n) {
            list.add(intervals[i++]);
        }
        return list.toArray(new int[list.size()][]);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int n = intervals.length;

        for(int i=0; i<n; i++) {
            if(intervals[i][1] < newInterval[0]) {
                list.add(intervals[i]);
            } else if( intervals[i][0] > newInterval[1]) {
                list.add(newInterval);
                newInterval = intervals[i];
            } else if(intervals[i][1]>= newInterval[0] || intervals[i][0] <= newInterval[1]) {
                //overlapped
                newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
                newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            }
        }
        list.add(newInterval);
        return list.toArray(new int[list.size()][]);
    }

    /*
     * Solution: binary search
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if(n==0) {
            return new int[][]{newInterval};
        }
        int left = 0, right = n-1;       
        while(left<=right) {
            int mid = (left+right)/2;
            if(intervals[mid][0] < newInterval[0]) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        List<int[]> list = new ArrayList<>();
        for(int i=0; i<left; i++) {
            list.add(intervals[i]);
        }
        list.add(newInterval);
        for(int i=left; i<n; i++) {
            list.add(intervals[i]);
        }

        List<int[]> merged = new ArrayList<>();
        for(int[] inter: list) {
            if(merged.isEmpty() || merged.getLast()[1]<inter[0]) {
                merged.add(inter);
            } else {
                merged.getLast()[0] = Math.min(merged.getLast()[0], inter[0]);
                merged.getLast()[1] = Math.max(merged.getLast()[1], inter[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
