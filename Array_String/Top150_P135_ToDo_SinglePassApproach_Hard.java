/*
    135. Candy
    There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
    You are giving candies to these children subjected to the following requirements:
    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.
    Return the minimum number of candies you need to have to distribute the candies to the children.

    Example 1:
        Input: ratings = [1,0,2]
        Output: 5
        Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

    Example 2:
        Input: ratings = [1,2,2]
        Output: 4
        Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
        The third child gets 1 candy because it satisfies the above two conditions.
    
    Constraints:
        n == ratings.length
        1 <= n <= 2 * 10^4
        0 <= ratings[i] <= 2 * 10^4
 */

package Array_String;

import java.util.Arrays;

public class Top150_P135_ToDo_SinglePassApproach_Hard {
    /*
        * Solution 1: Greedy - Using one array
        * time complexity: O(n)
        * space complexity: O(n)
    */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        
        for(int i=1; i<n; i++) {
            if(ratings[i]>ratings[i-1]) {
                candies[i] = candies[i-1]+1;
            }
        }

        int result = ratings[n-1];
        for(int i=n-2; i>=0; i--) {
            if(ratings[i]>ratings[i+1]) {
                candies[i] = Math.max(candies[i], candies[i+1]+1);
            }
            result += candies[i];
        }
        return result;
    }

    // Can't understand the logic yet
    /*
     * Solution 2: One pass
     * time complexity : O(n)
     * space complexity: O(1)
     */
    // public int candy(int[] ratings) {
    //     int n = ratings.length;
    //     int sum = 0;
    //     int i=0;

    //     while(i<n) {
    //         if(i<n-1 && ratings[i] == ratings[i+1]) {
    //             sum += 1;
    //             i++;
    //             continue;
    //         }

    //         int left = 0, right = 0;
    //         while(i<n-1&& ratings[i]<ratings[i+1]) {
    //             left++;
    //             i++;
    //         }
    //         while(i<n-1&& ratings[i] > ratings[i+1]) {
    //             right++;
    //             i++;
    //         }
    //         if(left==0 && right==0) {
    //             sum+=1;
    //             break;
    //         }

    //         int max = Math.max(left, right) +1;
    //         int leftsum = left * (left+1) /2;
    //         int rightsum = right*(right+1)/2 -1; // we exclude the last point because the one is including in the next mountain 
    //         sum += max + leftsum + rightsum;
    //     }
    //     return sum;
    // }

     /*
     * Solution 3: One pass - given solution
     * time complexity : O(n)
     * space complexity: O(1)
     */
    // public int candy(int[] ratings) {
    //     if (ratings.length <= 1) {
    //         return ratings.length;
    //     }
    //     int candies = 0;
    //     int up = 0;
    //     int down = 0;
    //     int oldSlope = 0;
    //     for (int i = 1; i < ratings.length; i++) {
    //         int newSlope = (ratings[i] > ratings[i - 1])
    //             ? 1
    //             : (ratings[i] < ratings[i - 1] ? -1 : 0);

    //         if (
    //             (oldSlope > 0 && newSlope == 0) ||
    //             (oldSlope < 0 && newSlope >= 0)
    //         ) {
    //            // because we need to reverse the very last 1 as a new start of next up-slope, 
    //            // so it becomes count(up) + count(down)+ Math.max(up, down) + 1 - 1 -->
    //             candies += count(up) + count(down) + Math.max(up, down); 
    //             up = 0;
    //             down = 0;
    //         }
    //         if (newSlope > 0) {
    //             up++;
    //         } else if (newSlope < 0) {
    //             down++;
    //         } else {
    //             candies++;
    //         }

    //         oldSlope = newSlope;
    //     }
    //     candies += count(up) + count(down) + Math.max(up, down) + 1; // normal case calculating such as 1254321
    //     return candies;
    // }
    // int count(int n) {
    //     return (n * (n + 1)) / 2;
    // }

     /*
     * Solution 4: One pass
     * time complexity : O(n)
     * space complexity: O(1)
     */
    // public int candy(int[] ratings) {
    //     int n = ratings.length; // Get the number of children
    //     int upCount = 0; // to keep track of consecutive increasing ratings
    //     int downCount = 0; // to keep track of consecutive decresing ratings
    //     int peakCount = 0; // to store the number of candies at the peak of increasing ratings
    //     int totalCandies = 1; 
        
    //     for (int i = 1; i < n; i++) {
    //         if (ratings[i - 1] < ratings[i]) {
    //             // If the current child has a higher rating than the previous one
    //             downCount = 0;
    //             upCount += 1;
    //             peakCount = upCount + 1;
    //             totalCandies += peakCount;
    //         } else if (ratings[i - 1] == ratings[i]) {
    //             // If the current child has the same rating as the previous one
    //             downCount = 0;
    //             upCount = 0;
    //             peakCount = 0;
    //             totalCandies += 1;
    //         } else {
    //             // If the current child has a lower rating than the previous one
    //             // the purpose of downcount : giving one candy to the current child and one candy for each child before him in the decreasing subarray to meet the requirements

    //             // what if we have increasing then decreasing subarrays?
    //             // we will have the last child of the first subarray as the first child on the second sub array, 
    //             // and then we give each child in the decreasing subarray one candy except that the child who is our peak until the length of subarray is larger than the peak
    //             downCount += 1;
    //             upCount = 0;
    //             totalCandies += (1 + downCount) - (peakCount > downCount ? 1 : 0);
    //         }
    //     }
        
    //     return totalCandies;
    // }

     /*
     * Solution 5: One pass
     * time complexity : O(n)
     * space complexity: O(1)
     */
    // public int candy(int[] ratings) {
    //     if (ratings == null || ratings.length == 0) return 0;
    //     int total = 1, prev = 1, countDown = 0;
    //     for (int i = 1; i < ratings.length; i++) {
    //         if (ratings[i] >= ratings[i-1]) {
    //             if (countDown > 0) {
    //                 total += countDown*(countDown+1)/2; // arithmetic progression
    //                 if (countDown >= prev) total += countDown - prev + 1;
    //                 countDown = 0;
    //                 prev = 1;
    //             }
    //             prev = ratings[i] == ratings[i-1] ? 1 : prev+1;
    //             total += prev;
    //         } else countDown++;
    //     }
    //     if (countDown > 0) { // if we were descending at the end
    //         total += countDown*(countDown+1)/2;
    //         if (countDown >= prev) total += countDown - prev + 1;
    //     }
    //     return total;
    // }
}
