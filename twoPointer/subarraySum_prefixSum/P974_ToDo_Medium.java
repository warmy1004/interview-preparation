/*
    974. Subarray sums divisible by k
    Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
    A subarray is a contiguous part of an array.

    Example 1:
        Input: nums = [4,5,0,-2,-3,1], k = 5
        Output: 7
        Explanation: There are 7 subarrays with a sum divisible by k = 5:
        [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]

    Example 2:
        Input: nums = [5], k = 9
        Output: 0
    
    Constraints:
        1 <= nums.length <= 3 * 10^4
        -10^4 <= nums[i] <= 10^4
        2 <= k <= 10^4
 */

package twoPointer.subarraySum_prefixSum;

import java.util.HashMap;
import java.util.Map;

public class P974_ToDo_Medium {
    /*
     * Solution: Prefix Sums with hash map
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int subarraysDivByK(int[] nums, int k) {
        // This hashmap = to keep track of the remainders when the prefix sums are divided by k
        // This helps to determine how many times we have seen a particular remainder before.
        Map<Integer, Integer> map = new HashMap<>();
        // initialization - to cover when the cumulative sum's remainder is 0, which means the cumulative sum is k * X or 0. 
        // 누적합이 0일 때 (즉, 배열의 시작 부분부터 시작하는 부분 배열이 k로 나누어 떨어질 때)의 경우를 세기 위해 필수적
        map.put(0, 1);
        int prefixSum = 0;
        int count = 0;
        for(int i=0; i<nums.length; i++) {
            // prefix sum up to index i
            prefixSum += nums[i];
            // (prefixSum[j] - prefixSum[i-1])%k == 0 이면, prefixSum[j]%k == prefixSum[i-1]%k 이다. 즉, 두 누적합의 나머지가 같다는 것은 두 누적합의 차이가 k 배수임을 의미함.
            // For each prefix sum, compute its remainder when divided by k. If two prefix sums have the same remainder, the subarray sum between those two indices is divisible by k.
            int remainder = prefixSum % k;
            // if remainder is less than 0, modifying it as positivie value by adding k
            // we always guarantee that a remainder is always a positive value. 
            if (remainder < 0) remainder += k;
            if(map.containsKey(remainder)) {
                // 해당 나머지를 가진 누적합의 개수만큼 카운트 증가시킨다.
                // 중요한 점: 현재 나머지와 같은 나머지를 가진 이전의 누적합이 존재할 경우, 그 수만큼 새로운 부분 배열이 추가된다는 것. 
                // 예를 들어, remainder가 3이고 i index 이전에 3이라는 나머지를 가진 누적합이 5번 등장했다면, 새로운 부분 배열이 5개 추가된다는 의미
                // For each prefix sum's remainder, if it has been seen before, it means there are as many valid subarrays as the count of that remainder
                count += map.get(remainder);
            }
            map.put(remainder, map.getOrDefault(remainder, 0)+1);
        }
        return count;
    }

    /*
     * Solution: Prefix Sums with array
     * time complexity: O(n)
     * space complexity: O(k)
     */
    public int subarraysDivByK(int[] nums, int k) {
        int sum = 0;
        int count = 0;
        int[] mod = new int[k];
        mod[0] = 1;
        for(int x: nums) {
            sum += x;
            int remainder = sum %k;
            if(remainder<0) remainder += k;
            count += mod[remainder];
            mod[remainder]++;
        }
        return count;
    }
}
