/*
    1094. car pooling
    There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
    You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
    Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.

    Example 1:
        Input: trips = [[2,1,5],[3,3,7]], capacity = 4
        Output: false

    Example 2:
        Input: trips = [[2,1,5],[3,3,7]], capacity = 5
        Output: true
    
    Constraints:
        1 <= trips.length <= 1000
        trips[i].length == 3
        1 <= numPassengersi <= 100
        0 <= fromi < toi <= 1000
        1 <= capacity <= 10^5
 */
package Array_String.Array_Sorting;

import java.util.HashMap;
import java.util.Map;

public class P1094_Medium {

    /*
     * Solution: bucket sorting
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] passengers = new int[1001];
        for(int[] trip: trips) {
            passengers[trip[1]] += trip[0];
            passengers[trip[2]] -= trip[0];
        }

        int currCapacity = 0;
        for(int passenger : passengers) {
            currCapacity += passenger;
            if(currCapacity>capacity) return false;
        }
        return true;
    }

    /*
     * Solution: Time stamp
     * time complexity:
     * space complexity:
     */
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> timestamp = new HashMap<>();
        for(int[] trip: trips) {
            int takeOnNumber = timestamp.getOrDefault(trip[1], 0) + trip[0];
            timestamp.put(trip[1], startPassengers);

            int takeOffNumber = timestamp.getOrDefault(trip[2], 0) - trip[0];
            timestamp.put(trip[2], takeOffNumber);
        }

        int currCapacity = 0;
        for(int numberChange : timestamp.values()) {
            currCapacity += numberChange;
            if(currCapacity>capacity) return false;
        }
        return true;
    }
}
