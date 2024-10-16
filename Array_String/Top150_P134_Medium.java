
/*
    134. Gas Station
    There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
    You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
    Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique

    Example 1:
        Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
        Output: 3
        Explanation:
        Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
        Travel to station 4. Your tank = 4 - 1 + 5 = 8
        Travel to station 0. Your tank = 8 - 2 + 1 = 7
        Travel to station 1. Your tank = 7 - 3 + 2 = 6
        Travel to station 2. Your tank = 6 - 4 + 3 = 5
        Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
        Therefore, return 3 as the starting index.

    Example 2:
        Input: gas = [2,3,4], cost = [3,4,3]
        Output: -1
        Explanation:
        You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
        Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
        Travel to station 0. Your tank = 4 - 3 + 2 = 3
        Travel to station 1. Your tank = 3 - 3 + 3 = 3
        You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
        Therefore, you can't travel around the circuit once no matter where you start.
    
    Constraints:
        n == gas.length == cost.length
        1 <= n <= 10^5
        0 <= gas[i], cost[i] <= 10^4
 */
package Array_String;

public class Top150_P134_Medium {

    /*
     * Solution 1 - Greedy 1
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gas_sum = 0, cost_sum = 0, total_tank = 0, result_index = 0;

        for(int i=0; i<gas.length; i++) {
            gas_sum+=gas[i];
            cost_sum+=cost[i];
        }
        if(gas_sum < cost_sum) return -1;

        for(int i=0; i<gas.length; i++) {
            total_tank += gas[i]-cost[i];
            if(total_tank < 0) {
                total_tank = 0;
                result_index = i+1;
            }
        }
        return result_index;
    }

    /*
     * Solution 2 - Greedy 2
     */
    // public int canCompleteCircuit(int[] gas, int[] cost) {
    //     int result_index = 0, total_tank = 0, actual_tank;

    //     // Assume i is the current candidate valid station
    //     if(int i=0; i<gas.length; i++) {
    //         total_tank += gas[i]-cost[i];
    //         actual_tank += gas[i]-cost[i];
    
    //         // if actual_tank is smaller than 0, it test if gas station i+1 is valid starting station by setting result_index as i+1 and by resetting actual_tank as 0
    //         if(actual_tank <0) {
    //             actual_tank = 0;
    //             result_index = i+1;
    //         }
    //     }
    //     return total_tank >=0 ? result_index : -1;
    // }
}

/*
 * time complexity: O(n)
 * space complexity: O(1)
 */