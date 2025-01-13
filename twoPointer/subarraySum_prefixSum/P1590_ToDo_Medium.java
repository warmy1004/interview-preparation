/*
    1590. Make sum divisible by P
    Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
    Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
    A subarray is defined as a contiguous block of elements in the array.

    Example 1:
        Input: nums = [3,1,4,2], p = 6
        Output: 1
        Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.

    Example 2:
        Input: nums = [6,3,5,2], p = 9
        Output: 2
        Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.

    Example 3:
        Input: nums = [1,2,3], p = 3
        Output: 0
        Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
    
    Constraints:
        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^9
        1 <= p <= 10^9
 */
package twoPointer.subarraySum_prefixSum;

import java.util.HashMap;
import java.util.Map;

public class P1590_ToDo_Medium {
    /*
     * Solution: Prefix Sum Modulo
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int minSubarray(int[] nums, int p) {
        long totalSum = 0;
        // Step 1: calculate total sum and target remainder
        /*
         * target을 구할 때, p로 나누는 이유는, 전체 합을 p로 나눈 나머지를 사용하기 위해서이다. 
         * 1. 문제의 목표: 주어진 배열에서 일부를 지워서 나머지 배열의 합이 p로 나누어 떨어지도록 하는 것. 즉, 남은 배열의 합이 전체 배열의 합(target)의 나머지(target%p)를 상쇄해야 한다.
         * 2. target을 p로 나눈 나머지 계산:
         *      - 중간 결과의 제한: 만약 배열의 값이 크거나, 배열의 길이가 길다면, target이 매우 커질 수 있다. 이를 p로 나누어 나머지만을 남김으로써, 불필요하게 큰 숫자를 다루지 않도록 함.
         *      - 문제의 요구: 최종적으로 우리가 확인해야 하는 것은, 남은 배열의 합이 p로 나누어 떨어지는가. 따라서, target이 p로 나누어 떨어지는 경우를 체크하기 위해, target % p를 사용하는 것이 중요
         * 즉, (1) 배열의 합이 커질 수 있는 것을 방지하고, (2) 문제의 요구사항인 남은 배열의 합이 p로 나누어 떨어지게 만들기 위해 필요한 나머지를 확인하기 위함.
         */
        for(int num: nums) {
            totalSum += num;
        }

        long targetRemainder = totalSum % p;
        if (targetRemainder == 0) {
            return 0;
        }

        // Step 2: Use a hash map to track prefix sum mod p
        int minLen = nums.length;
        long prefixSum = 0;
        Map<Long, Integer> prefixMod = new HashMap<>();
        prefixMod.put(0L, -1); // To handle the case where the whole prefix is the answer.

        for(int i=0; i< nums.length; i++) {
            prefixSum += nums[i];

            /*
             * curr_mod를 사용하는 이유:
             * 1. 나머지의 성질: 두 수의 차이가 p로 나누어 떨어지면, 그 두 수는 동일한 나머지를 가진다. 즉, sum1 % p == sum2% p 라는 조건이 만족하면 두 수의 차이는 p의 배수.
             *                  따라서, curr_mod를 사용하여 현재까지의 누적 합의 나머지를 계산하면, 지워야 할 부분 배열의 합을 간접적으로 찾는 것이 가능해진다. 
             *                  (a-b) % p = 0 --> a%p - b%p = 0 --> a%p = b%p
             * 2. 효율성: 배열을 순회하면서 curr_sum 을 계속 업데이트하고, 그 값이 target과 같아지는 경우를 찾는다면, 지우려는 부분의 인덱스를 정확히 추적하기 힘들다. 
             *            대신, curr_mod를 사용하면, 해시맵에 이미 저장된 나머지 값과 비교함으로써, 그 시점에서 지워야 할 부분 배열의 시작점을 쉽게 찾을 수 있다.
             * 3. 복잡한 경우 처리: 여러 개의 부분 배열이 존재할 수 있는 경우, curr_sum만으로는 적절한 시작점을 찾기 어려울 수 있다. curr_mod를 활용하면 각 인덱스에서의 나머지를
             *                     기준으로 비교할 수 있어 더 정밀하게 처리 가능
             */
            long currentRemainder = prefixSum  %p;

            /*
             * This is how we construct the formula for the smallest subarray removal:
             *  We need to remove the smallest subarray such that the sum of the remaining elements is divisible by p. 
             *  If s%p = 0, no removal needed. Otherwise, we want to remove a subarray whose sum (mod p) equals the remainder:
             *      target = s%p;
             *  we need to find the smallest subarray between indices j and i, where the difference between their prefix sums (mod p) satisfies:
             *      (currentSum_i - currentSum_j) % p = target
             *  This implies:
             *      currentSum_j = (currentSum_i-target) %p
             *  Now, substracting target can lead to negative values. To ensure a non-negative remainder, we add p before taking modulo p.
             *  Thus, the correct expression is:
             *      needed = (currSum - target+ p) %p
             *  This way, we can ensure that needed is a valid positive remainder, and we can efficiently track subarrays using a hash map of previously seen prefix sum.
             *  The final expression:
             *      (currentSum - target + p ) %p
             *  guarantees the remainder stays non-negtive and helps us find the smallest subarray whose removal makes the total sum divisible by p
             */
            /*
             * curr_target은 전체 합을 p로 나눈 나머지를 상쇄하기 위해 필요한 나머지를 찾는데 사용된다. 
             * 1. 목표 나머지 계산: curr_target은 우리가 원하는 목표. 즉, 누적합의 나머지(curr_mod) 에서 이 값을 빼면, 전체 합이 p로 나누어 떨어지는 결과를 얻을 수 있다.
             *                     target은 전체 배열의 합을 p로 나눈 나머지. 이 계산을 통해, 현재 누적합의 나머지와 비교할 목표 나머지를 설정. 
             * 2. 부분 배열을 지우기 위한 조건: curr_target은 특정 인덱스에서 이전에 예산된 curr_mod과 비교함으로써, 전체 합을 p로 나누어 떨어지게 만들기 위해 지워야 할 부분을 찾는데 필요
             *                                즉, 누적합에서 curr_target이 나오는경우, 그 시점까지의 구간을 지우면 전체 합이 p로 나누어 떨어지게 됨. 
             * curr_target은 우리가 남기고 싶은 나머지. 만약 이전에 계산된 curr_mod가 curr_target과 일치하면, 우리는 그 구간의 합을 지움으로써 전체 합이 p로 나누어 떨어지게 만들 수 있다. 
             */
            long requiredRemainder = (currentRemainder - targetRemainder + p) % p;

            if(prefixMod.containsKey(requiredRemainder)) {
                int subarrayStartIndex = prefixMod.get(requiredRemainder);
                int lengthToRemove = i-subarrayStartIndex;
                minLen = Math.min(minLen, lengthToRemove);
            }
            prefixMod.put(currentRemainder, i);
        }
        return minLen == nums.length ? -1 : minLen;
    }
}
