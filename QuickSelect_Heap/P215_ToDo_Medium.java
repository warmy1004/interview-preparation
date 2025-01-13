/*
    215. Kth Largest Element in an array
    Given an integer array nums and an integer k, return the kth largest element in the array.
    Note that it is the kth largest element in the sorted order, not the kth distinct element.
    Can you solve it without sorting?

    Example 1:
        Input: nums = [3,2,1,5,6,4], k = 2
        Output: 5       

    Example 2:
        Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
        Output: 4
    
    Constraints:
        1 <= k <= nums.length <= 10^5
        -10^4 <= nums[i] <= 10^4
 */
package QuickSelect_Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class P215_ToDo_Medium {
    /*
     * Solution: Min-Heap
     * time complexity: O(nlogk)
     *      Operations on a heap cost logarithmic time to its size. Because our heap is limited to a size of k, operations cost at most O(logk).
     *      We iterate over nums, performing one or two heap operations at each iteration. We iterate n times, performing up to logk work at each iteration, 
     *      giving us a time complexity of O(nlogk)
     * space complexity: O(k)
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int n : nums) {
            heap.add(n);
            if(heap.size()>k) {
                heap.remove();
            }
        }
        return heap.peek();
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
        for(int num: nums) {
            maxHeap.add(num);
        }
        while(k>1) {
            maxHeap.remove();
            k--;
        }
        return maxHeap.peek();
    }

    /*
     * Solution: Counting Sort
     * time complexity: O(n+m)
     *      we first find max and min, which costs O(n). We initialize couting, which cost m. We populate counting which costs O(n)
     * space complexity: O(m)
     */
    public int findKthLargest(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        // To avoid accessing the index with a negative number
        int min = Integer.MAX_VALUE;
        for(int n: nums) {
            max = Math.max(max, n);
            min = Math.min(min, n);
        }

        int[] counting = new int[max-min+1];

        for(int n: nums) {
            counting[n-min]++;
        }

        for(int i=counting.length-1; i>=0; i--) {
            k-=counting[i];
            if(k<=0) {
                return i+min;
            }
        }
        return 0;
    }

    /*
     * Solution: QuickSelect 1 - Passed without TLE
     * 
     * Approach:
     *      Quickselect, also known as Hoare's selection algorithm, is an algorithm for finding the kth smallest element in an unordered list. It is significant because it has an average runtime of O(n).
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for(int n: nums) {
            list.add(n);
        }
        return quickSelect(list, k);
    }

    int quickSelect(List<Integer> nums, int k) {
        // int pivotindex = nums.size()/2;
        // int pivot = nums.get(pivotindex);
        int pivot = nums.getLast();

        // left holds larger numbers than pivot
        List<Integer> left = new ArrayList<>();
        // right holds smaller numbers than pivot
        List<Integer> right = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();

        for(int n: nums) {
            if (n < pivot) {
                right.add(n);
            } else if(n>pivot) {
                left.add(n);
            } else {
                mid.add(n);
            }
        }

        if(left.size() >= k) {
            return quickSelect(left, k);
        }

        if(left.size()+mid.size() < k) {
            return quickSelect(right, k-mid.size()-left.size());
        }
        return pivot;
    }

     /*
     * Solution: QuickSelect 2 - TLE for the largest dataset
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length-1, k);
    }

    int quickSelect(int[] nums, int start, int end, int k) {
        int pivot = end;
        int left = start;
        int right = end-1;
        while(left<=right) {
            if(nums[left]<nums[pivot]) {
                swap(nums, left, right);
                right--;
            } else {
                left++;
            }
        }
        swap(nums, left, pivot);
        if(left == k) return nums[left];
        else if(left < k) return quickSelect(nums, left+1, end, k);
        else return quickSelect(nums, start, left-1, k);
    }

    void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /*
     * Solution: QuickSelect 3
     */
    public int findKthLargest(int[] nums, int k) {
        if(nums.length==1) return nums[0];
        k = nums.length - k;
        return quickSelect(nums, 0, nums.length-1, k);
    }

    int quickSelect(int[] nums, int start, int end, int k) {
        int pivot = partition(nums, start, end); 
        if(k < pivot) {
            return quickSelect(nums, start, pivot-1, k);
        }
        if( k > pivot) {
            return quickSelect(nums, pivot+1, end, k);
        }
        return nums[pivot];
    }

    int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start;
        for(int j = start; j<end; j++) {
            if(nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, end);
        return i;
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /*
     * Solution: QuickSelect with Random pivot
     */
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length-1;
        // Kth largest is (nums.length-k)th in zero-based index
        k = nums.length-k;
        while(true) {
            int pivotIdx = partition(nums, left, right);
            if(pivotIdx == k) {
                return nums[pivotIdx];
            } else if (pivotIdx > k) {
                right = pivotIdx-1;
            } else {
                left = pivotIdx+1;
            }
        }
    }
    int partition(int[] nums, int left, int right) {
        Random rand = new Random();
        // To prevent index overflow - choose a rando pivot index in the range [left, right]
        int pivot = left + rand.nextInt(right-left+1);
        // Need to save the pivot value, because a value on pivot index is changed
        int pivotValue = nums[pivot];
        // move pivot to the end
        swap(nums, pivot, right);
        int leftmost = left;
        for(int i=left; i<right; i++) {
            if(nums[i] < pivotValue) {
                swap(nums, i, leftmost);
                leftmost++;
            }
        }
        swap(nums, leftmost, right);
        return leftmost;
    }
    void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
