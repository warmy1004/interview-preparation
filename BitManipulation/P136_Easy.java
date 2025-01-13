/*
    136. single number
    Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
    You must implement a solution with a linear runtime complexity and use only constant extra space.

    Example 1:
        Input: nums = [2,2,1]
        Output: 1

    Example 2:
        Input: nums = [4,1,2,1,2]
        Output: 4

    Example 3:
        Input: nums = [1]
        Output: 1
    
    Constraints:
        1 <= nums.length <= 3 * 10^4
        -3 * 10^4 <= nums[i] <= 3 * 10^4
        Each element in the array appears twice except for one element which appears only once.
 */
package BitManipulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P136_Easy {
    /*
     * Solution: using list
     * time complexity: O(n^2)
     *      we iterate through nums, taking O(n) time. We search the whole list to find whether there is duplicate number, taking O(n) time. because search is in the for loop, so we have O(n^2).
     * space complexity: O(n)
     */
    public int singleNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int n: nums) {
            if(list.contains(n)) {
                /*
                 * list.remove(n)으로 하면, IndexOutOfBoundsException 발생. 이는, ArrayList의 remove 메소드가 overloading 됐기 때문이다. 
                 *      1. remove(int index): 리스트에서 주어진 '인덱스'의 요소를 제거
                 *      2. remove(Object o): 리스트에서 주어진 '객체'를 제거
                 *  n은 int 타입이므로, list.remove(n)을 호출하면, remove(int index)가 호출되고, 즉 n을 값으로 처리하는 대신에, 리스트에서 n번째 인덱스의 요소를 제거하려 함.
                 *  이때, n이 리스트의 크기보다 크거나 음수인 경우에, IndexOutOfBoundsException이 발생. 
                 * 
                 *  So, list.remove(Object o)를 호출해야함. n을 명시적으로 Integer 객체로 변환하여 호출하면 됨. 
                 */
                list.remove(new Integer(n));
                // list.remove(Integer.valueOf(n)); --> 권장

                /*
                 * new Integer(n) : 새로운 객체를 생성, 매번 생성하기 때문에, 메모리 할당과 해제가 필요함. 
                 * Integer.valueOf(n) : 캐싱된 객체를 반환. Integer 클래스는 값이 -128에서 127 범위인 경우, 동일한 Integer 객체를 재사용한다. 이것을 Integer caching이라고 한다. 
                 *      캐싱 범위를 벗어난 경우에도, 객체 풀을 우선 사용하려고 시도하며, 필요한 경우에만 새 객체를 생성한다. 
                 * 결과적으로, 둘은 기능적으로는 같으나, 효율성에서 Integer.valueOf(n)을 더 권장한다. 
                 */
            } else {
                list.add(n);
            }
        }
        return list.get(0);
    }

    /*
     * Solution: hash table
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        for(int i:nums) {
            if(map.get(i) == 1) return i;
        }
        return 0;
    }

    /*
     * Solution: math
     * time complexity: O(n)
     * space complexity: O(n) 
     */
    public int singleNumber(int[] nums) {
        int setSum = 0, numsSum = 0;
        Set<Integer> set = new HashSet<>();
        for(int n: nums) {
            if(!set.contains(n)) {
                set.add(n);
                setSum += n;
            }
            numsSum += n;
        }
        return 2*setSum - numsSum;
    }

    /*
     * Solution: bit manipulation
     * 
     * Approach:
     *      if we take XOR of zero and some bit, it will return that bit. a ^ 0 = a
     *      if we take XOR of two same bits, it will return 0. a ^ a = 0
     *      a ^ b ^ a = 0 ^ b = b
     * 
     * time complexity: O(n)
     * space complexity: O(1) 
     */
    public int singleNumber(int[] nums) {
        int a = 0;
        for(int n: nums) {
            a ^= n;
        }
        return a;
    }
}

