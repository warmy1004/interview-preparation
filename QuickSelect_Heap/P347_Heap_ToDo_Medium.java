/*
    347. Top K Frequent Elements
    Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

    Example 1:
        Input: nums = [1,1,1,2,2,3], k = 2
        Output: [1,2]

    Example 2:
        Input: nums = [1], k = 1
        Output: [1]
    
    Constraints:
        1 <= nums.length <= 10^5
        -10^4 <= nums[i] <= 10^4
        k is in the range [1, the number of unique elements in the array].
        It is guaranteed that the answer is unique.
        
    Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
package QuickSelect_Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class P347_Heap_ToDo_Medium {
    /*
     * Solution: Min Heap
     * time complexity: O(nlogK)
     * space complexity: O(n+k)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        Queue<Integer> heap = new PriorityQueue<>( (n1, n2) -> map.get(n1)-map.get(n2));
        for(int key: map.keySet()) {
            heap.add(key);
            if(heap.size()>k) {
                heap.remove();
            }
        }
        return heap.stream().mapToInt(Integer:: intValue).toArray();
    }

    /*
     * Solution: Max Heap
     * time complexity: O(nlogk)
     * space complexity: O(n+k)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        Queue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> map.get(n2)-map.get(n1));
        for(int key: map.keySet()) {
            maxHeap.add(key);
        }
        int[] result = new int[k];
        for(int i=0; i<k; i++) {
            result[i] = maxHeap.remove();
        }
        return result;
    }

    /*
     * Solution: hashMap + arrayList
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        List<Integer>[] freq = new ArrayList[nums.length+1];
        for(int i=0; i<=nums.length; i++) {
            freq[i] = new ArrayList<Integer>();
        }
        for(Map.Entry<Integer, Integer> item: map.entrySet()) {
            int frequency = item.getValue();
            freq[frequency].add(item.getKey());
        }

        int[] result = new int[k];
        int point = 0;
        for(int i=freq.length-1; i>=0; i--) {
            for(int idx: freq[i]) {
                result[point++] = idx;
                if(point == k) {
                    return result;
                }
            }
        }
        return new int[0];
    }

    /*
     * Solution: Map + collections.sort
     * time complexity: O(nlogn), because of collections.sort
     * space complexity: O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a,b)-> map.get(b)-map.get(a));
        int[] result = new int[k];
        for(int i=0; i<k; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /*
     * Solution: quick select - ascending order
     * time complexity: O(n),
     *      quickSelect's avg time compelxity is O(n), the worst time complexity is O(n^2)
     * space complexity: O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        int n = map.size();
        int[] elmts = new int[n];
        int i=0;
        for(int key: map.keySet()) {
            elmts[i++] = key;
        }
        quickselect(elmts, 0, n-1, n-k, map);
        
        return Arrays.copyOfRange(elmts, n-k, n);
    }

    void quickselect(int[] elmts, int start, int end, int k, Map<Integer, Integer> map) {
        int pivot = partition(elmts, start, end, map);
        if(pivot < k) {
            quickselect(elmts, pivot+1, end, k, map);
        }
        if(pivot > k) {
            quickselect(elmts, start, pivot-1, k, map);
        }
    }

    int partition(int[] elmts, int start, int end, Map<Integer,Integer> map) {
        int pivot = start;
        for(int i=start; i<end; i++) {
            if(Integer.compare(map.get(elmts[i]), map.get(elmts[end]))<0) {
                swap(elmts, i, pivot);
                pivot++;
            }
        }
        swap(elmts, pivot, end);
        return pivot;
    }

    void swap(int[] elmts, int a, int b) {
        int temp = elmts[a];
        elmts[a] = elmts[b];
        elmts[b] = temp;
    }
}

