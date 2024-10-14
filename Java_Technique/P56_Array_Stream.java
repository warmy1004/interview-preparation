
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P56_Array_Stream {
    /*
     * Solution: Sorting
     * time complexity: O(nlogn)
     * space complexity: O(logn)
     *      if we can sort intervals in place, we do not need more than constant additional space, although the sorting itself takes O(logn) space. 
     *      Otherwise, we must allocate linear space to store a copy of intervals and sort that.
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
        List<int[]> answer = new ArrayList<>();
        answer.add(intervals[0]);
        for(int[] pair: intervals) {
            int[] prev = answer.getLast();
            if(prev[1] >= pair[0]) {
                prev[1] = Math.max(prev[1], pair[1]);
            } else {
                answer.add(pair);
            }
        }
        return answer.toArray(new int[answer.size()][]);
    }
}
