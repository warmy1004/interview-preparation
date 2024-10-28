/*
    1010. Pairs of songs with total durations divisible by 60
    You are given a list of songs where the ith song has a duration of time[i] seconds.
    Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

    Example 1:
        Input: time = [30,20,150,100,40]
        Output: 3
        Explanation: Three pairs have a total duration divisible by 60:
        (time[0] = 30, time[2] = 150): total duration 180
        (time[1] = 20, time[3] = 100): total duration 120
        (time[1] = 20, time[4] = 40): total duration 60

    Example 2:
        Input: time = [60,60,60]
        Output: 3
        Explanation: All three pairs have a total duration of 120, which is divisible by 60.
    
    Constraints:
        1 <= time.length <= 6 * 10^4
        1 <= time[i] <= 500
 */

package twoPointer.subarraySum_prefixSum;

import java.util.HashMap;
import java.util.Map;

public class P1010_ToDo_Medium {
    /*
     * Solution : HashMap
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int t: time) {
            int remainder = t%60;
            if(remainder == 0) {
                count+= map.getOrDefault(remainder, 0);
            } else {
                count += map.getOrDefault(60-remainder, 0);
            }
            map.put(remainder, map.getOrDefault(remainder, 0)+1);
        }
        return count;
    }
    
    /*
     * Solution : Array
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int numPairsDivisibleBy60(int[] time) {
        int[] remainders = new int[60];
        int count = 0;
        for(int t: time) {
            int remainder = t%60;
            if(remainder == 0) {
                count += remainders[remainder];
            } else {
                count+= remainders[60-remainder];
            }
            remainders[remainder]++;
        }
        return count;
    }

    public int numPairsDivisibleBy60(int[] time) {
        int[] remainderCounts = new int[60];
        for(int t: time) {
            int remainder = t % 60;
            remainderCounts[remainder]++;
        }

        /*
         * Count valid pairs:
         *  For each remainder r, find its complementary remainder (60-r) that, when added to r, results in a total that is divisible by 60.
         *  speicial handle:
         *      - Pairs of songs with both durations having a remainder of 0
         *      - pairs of songs with both durations having a remainder of 30
         *      - for other remainders r, pair r with (60-r)
         */
        int pairCount = 0;
        pairCount += (remainderCounts[0] * (remainderCounts[0]-1))/2;
        pairCount += (remainderCounts[30] * (remainderCounts[30]-1))/2;
        for(int r=1; r<30; r++) {
            pairCount+= remainderCounts[r] * remainderCounts[60-r];
        }
        return pairCount;
    }
}
