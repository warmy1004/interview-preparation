/*
    149. max points on a line
    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

    Example 1:
        Input: points = [[1,1],[2,2],[3,3]]
        Output: 3

    Example 2:
        Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
        Output: 4
    
    Constraints:
        1 <= points.length <= 300
        points[i].length == 2
        -10^4 <= xi, yi <= 10^4
        All the points are unique.
 */

package DP;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class P149_Must_ToDo_Hard {
    /*
     * Solution: Brute forth
     * time complexity: O(n^3)
     * space complexity: O(1)
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        int answer = 1;
        for(int i=0; i<n; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for(int j=i+1; j<n; j++) {
                int x2 = points[j][0], y2=points[j][1];
                int count = 2;
                for(int k=j+1; k<n; k++) {
                    int x3 = points[k][0], y3 = points[k][1];
                    /*
                     * slope between x1,y1 and x2,y2 => (y2-y1)/(x2-x1)
                     * slope between x2,y2 and x3,y3 => (y3-y2)/(x3-x2)
                     * 
                     * these two need to be same : (y2-y1)/(x2-x1) == (y3-y2)/(x3-x2) -> (y2-y1)*(x3-x2) == (y3-y2)*(x2-x1) for preventing diving by zero
                     */
                    double a = (y2-y1) * (x3-x2);
                    double b = (y3-y2) * (x2-x1);
                    if(a==b) {
                        count++;
                    }
                }
                answer = Math.max(answer, count);
            }    
        }
        return answer;
    }

    /*
     * Solution: using slope
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        if(n<=2) return n;
        int max = 2;
        for(int i=0; i<n; i++) {
            Map<Double, Integer> map = new HashMap<>();
            for(int j=0; j<n; j++) {
                if(i==j) continue;
                int[] p1 = points[i];
                int[] p2 = points[j];
                double slope = Math.atan2(p2[1]-p1[1], p2[0]-p1[0]);
                map.put(slope, map.getOrDefault(slope, 0)+1);
            }
            // +1 because the point, points[i] also lies on the line, so we must include it in the answer
            max = Math.max(max, Collections.max(map.values())+1);
        }
        return max;
    }

    public int maxPoints(int[][] points) {
        int max = 0;
        for(int[] p1: points) {
            Map<Double, Integer> map = new HashMap<>();
            for(int[] p2: points) {
                if(p1 == p2) continue;
                double slope = 0.0;
                if(p1[0] == p2[0]) slope = Double.POSITIVE_INFINITY; // slope = Double.MAX_VALUE;
                else slope = (p2[1]-p1[1])/(double)(p2[0]-p1[0]);
                map.put(slope, map.getOrDefault(slope, 0)+1);
                max = Math.max(max, map.get(slope));
            }
        }
        return max+1;
    }
}
