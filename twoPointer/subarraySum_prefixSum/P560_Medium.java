/*
    560. Subarray sum equals K
    Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
    A subarray is a contiguous non-empty sequence of elements within an array.

    Example 1:
    Input: nums = [1,1,1], k = 2
    Output: 2

    Example 2:
    Input: nums = [1,2,3], k = 3
    Output: 2

    Constraints:
    1 <= nums.length <= 2 * 10^4
    -1000 <= nums[i] <= 1000
    -10^7 <= k <= 10^7
 */
package twoPointer.subarraySum_prefixSum;

import java.util.HashMap;
import java.util.Map;

public class P560_Medium {
    /*
     * Solution: Brute force
     * time complexity: O(n^3)
     * space complexity: O(1)
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                int sum = 0;
                for(int m = i; m < j; m++) {
                    sum += nums[m];
                }

                if(sum == k) count++;
            }
        }
        return count;
    }

    /*
     * Solution: Using cumulataive sum
     * time complexity: O(n^2)
     * space complexity: O(n)
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length+1];
        sum[0] = 0;
        for(int i=1; i<=nums.length; i++) {
            sum[i] = sum[i-1]+nums[i-1];
        }
        for(int start = 0; start<nums.length; start++) {
            for(int end = start+1; end<=nums.length; end++) {
                if(sum[end] - sum[start] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * Solution: Without space
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for(int i=0; i<nums.length; i++) {
            int sum = 0;
            for(int j = i; j<nums.length; j++) {
                sum += nums[j];
                if(sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * Solution: Using HashMap
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // 누적합이 정확히 k인 서브 배열을 찾기 위한 초기값 설정
        // sum이 k와 같다면, sum-k는 0이 되고, 이는 map에서 0의 카운트가 1이기 때문에, 서브 배열의 개수를 증가시킬 수 있다. 
        map.put(0, 1);
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];

            // sum-k인 이유: 특정 시점의 누적합 (sum)에서 k를 빼면, 그 시점까지의 누적합이 이전에 k를 더한 지점과 같아지는 서브 배열을 찾았다는 의미. 
            // 즉, sum-k가 map에 존재한다는 것은, 이전에 k만큼 더한 누적합이 있었다는 뜻
            // 반대로, k-sum을 사용하지 않는 이유는, 누적합이 어떻게 변화하는지를 추적하는 방식과 맞지 않기 때문. 
            // k-sum을 사용하면, 서브배열의 누적합이 특정한 조건을 충족하는지 확인하는 것이 아니라, 현재의 누적합에서 k를 뺀 값이 어떤 특정한 값을 찾아야 하므로, 문제와 맞지 않다.
            // 즉, sum-k는 이전의 특정 누적합을 기준으로 현재까지의 누적합을 비교하기 위한 방법이고, k-sum은 논리적으로 맞지 않다. 

            // 현재 누적합(prefixSum == sum) 에서 k를 뺀 값. 즉, 우리가 찾고자 하는 부분 배열의 합이 k와 같으려면, 이전의 누적합이 sum - k여야 한다. 
            if(map.containsKey(sum - k)) {
                count+= map.get(sum-k);
            }
            // 현재까지의 누적합을 map에 저장, 이 저장된 현재까지의 누적합은 추후에 current_sum - k의 값으로 (이전에 계산된 값으로) 사용됨.
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return count;
    }
}
