/*
    2845. Count of interesting subarrays
    You are given a 0-indexed integer array nums, an integer modulo, and an integer k.
    Your task is to find the count of subarrays that are interesting.
    A subarray nums[l..r] is interesting if the following condition holds:
        Let cnt be the number of indices i in the range [l, r] such that nums[i] % modulo == k. Then, cnt % modulo == k.
        Return an integer denoting the count of interesting subarrays.
    Note: A subarray is a contiguous non-empty sequence of elements within an array.

    Example 1:
        Input: nums = [3,2,4], modulo = 2, k = 1
        Output: 3
        Explanation: In this example the interesting subarrays are: 
        The subarray nums[0..0] which is [3]. 
        - There is only one index, i = 0, in the range [0, 0] that satisfies nums[i] % modulo == k. 
        - Hence, cnt = 1 and cnt % modulo == k.  
        The subarray nums[0..1] which is [3,2].
        - There is only one index, i = 0, in the range [0, 1] that satisfies nums[i] % modulo == k.  
        - Hence, cnt = 1 and cnt % modulo == k.
        The subarray nums[0..2] which is [3,2,4]. 
        - There is only one index, i = 0, in the range [0, 2] that satisfies nums[i] % modulo == k. 
        - Hence, cnt = 1 and cnt % modulo == k. 
        It can be shown that there are no other interesting subarrays. So, the answer is 3.

    Example 2:
        Input: nums = [3,1,9,6], modulo = 3, k = 0
        Output: 2
        Explanation: In this example the interesting subarrays are: 
        The subarray nums[0..3] which is [3,1,9,6]. 
        - There are three indices, i = 0, 2, 3, in the range [0, 3] that satisfy nums[i] % modulo == k. 
        - Hence, cnt = 3 and cnt % modulo == k. 
        The subarray nums[1..1] which is [1]. 
        - There is no index, i, in the range [1, 1] that satisfies nums[i] % modulo == k. 
        - Hence, cnt = 0 and cnt % modulo == k. 
        It can be shown that there are no other interesting subarrays. So, the answer is 2

    Constraints:
        1 <= nums.length <= 10^5 
        1 <= nums[i] <= 10^9
        1 <= modulo <= 10^9
        0 <= k < modulo
 */

package twoPointer.subarraySum_prefixSum;

import java.util.HashMap;
import java.util.Map;

public class P2845_ToDo_Medium {
    /*
     * Approach: 
     *      1. 조건분석: 각 부분 배열에 대한 cnt는 조건을 만족해야 함. 즉, cnt % modulo == k
     *      2. prefix sum AND hashMap 사용: cnt 값을 기록하고, 해시맵을 사용해 해당 조건을 만족하는 경우의 수를 계산
     */
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        /*
        *  Solution: Prefix Sum with HashMap
        *  time complexity: O(n)
        *  space complexity: O(n) 
        */
        public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
            int count = 0;
            Long interestingCount = 0L;

            // To keep track of how many times each count (modulo Module) has occurred so far.
            // Whenever we find a count that satisfies the condition cnt%modulo == k, we can determine how many interesting subarrays end at the current index
            Map<Long, Integer> freq = new HashMap<>();
            // Initialization for cnt%modulo  == 0인 경우
            freq.put(0L, 1); 
            
            for(int num: nums) {
                if(num % modulo == k) {
                    count++;
                }
                // this is what we are looking for, and modCount needs to be k
                long modCount = count % modulo;

                // 현재의 modCount에서 k를 빼고, modulo를 더하여 나머지를 계산합니다. 이는 cnt % modulo == k를 만족하는 조건입니다.
                // modCount는 현재까지의 count를 modulo로 나눈 나머지. 우리가 찾아야 하는 조건은, cnt % modulo == k --> So, modCount에서 k를 빼면, cnt가 k를 만족하는 이전 상태를 나타내게 됨. 
                // (modCount - k + modulo )% module는 다음을 보장한다:
                //      modCount - k가 음수가 될 수 있기 때문에, 이를 방지하기 위해 +modulo를 함. 이렇게 하면 나머지를 구할 때, 음수가 되지 않는다.
                //      이 식은, modCount가 k 일때 그 이전에 cnt % modulo가 얼마나 떨어져 있었는지를 나타낸다. 
                interestingCount += freq.getOrDefault( (modCount-k+modulo)%modulo, 0);
                freq.put(modCount, freq.getOrDefault(modCount, 0)+1);
            }
            return interestingCount;
        }

        public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
            Map<Integer, Integer> freq = new HashMap<>();
            freq.put(0, 1);
            long answer = 0;
            int n = nums.size();
            int prefixCount = 0;

            // prefix[r]% modulo - prefix[l-1]%modulo = k
            // prefix[r]%module - k = prefix[l-1]
            for(int i=0; i<n; i++) {
                if(nums.get(i)%modulo == k) {
                    prefixCount ++;
                }
                //prefixCount = prefixCount + (nums.get(i)%modulo == k? 1: 0);

                int targetPrefix = (prefixCount%modulo - k +modulo) % modulo;
                answer += freq.getOrDefault(targetPrefix, 0);
                freq.put( prefixCount % modulo, freq.getOrDefault(prefixCount % modulo, 0)+ 1);
            }
            return answer;
        }
    }
    /*
     * (modCount - k + modulo) % modulo is used to ensure that we correctly find the required count for interesting subarrays, especially in the context of modular arithmetic.
     * Propose of the expression:
     *      We want to find how many times we have seen a particular count (cnt) modulo modulo that satisfies: cnt % modulo == k
     *      This means, that if we have a running count of numbers satisfying nums[i] % modulo == k, we want to check it the previous counts would allow us to form a valid interesting subarray.
     * Breakdown of the expression:
     *      1. current count modulo: we compute modCount as modCound = count % modulo
     *      2. required count:
     *          For the current subarray to be interesting, we need to find: cnt%modulo == k
     *          this means: modCount == k
     *      3. finding the previous count:
     *          To find how many times we've seen a count such that: previous count % modulo = k
     *              we need to satisfy: cnt%modulo == k
     *              in terms of the previous and current counts, this can be expressed as: (count - previousCount) % modulo == k
     *              rearranging this gives: count%modulo - previousCount%modulo == k
     *              if we let, modCount = count%modulo AND previousCount = previousCount % modulo,
     *              then the equation simplifies to: modCount - previousCount == k
     *              we can rearrange this to isolate previousCount: 
     *                                      previousCount = modCount - k
     *          we can rearrange this to find the required previous count: previous count = modCount - k
     *             
     *      4. normalization:
     *          since counts can be negative when subtracting k from modCount, we normalize it by adding modulo before taking the modulo again: (modCount - k + modulo) % modulo
     *          This ensures that the result always non-negative and falls within the range of [0, modulo-1]
     */
}
