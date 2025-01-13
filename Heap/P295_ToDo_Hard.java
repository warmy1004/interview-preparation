/*
    295. Find median from data stream
    The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
        For example, for arr = [2,3,4], the median is 3.
        For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
    Implement the MedianFinder class:
        MedianFinder() initializes the MedianFinder object.
        void addNum(int num) adds the integer num from the data stream to the data structure.
        double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
    
    Example 1:
    Input
        ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
        [[], [1], [2], [], [3], []]
    Output
        [null, null, null, 1.5, null, 2.0]
        Explanation
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        medianFinder.findMedian(); // return 2.0
    
    Constraints:
        -10^5 <= num <= 10^5
        There will be at least one element in the data structure before calling findMedian.
        At most 5 * 10^4 calls will be made to addNum and findMedian.
    
    Follow up:
        If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
        If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
package Heap;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

public class P295_ToDo_Hard {
    /*
     * Solution: Using two priority queues
     * time complexity: O(logn)
     *      add()/offer(), remove()/poll() take O(logn), peek() takes O(1)
     * space complexity: O(n)
     */
    Queue<Integer> small_maxHeap;
    Queue<Integer> large_minHeap;
    boolean isOdd;
    public MedianFinder() {
        small_maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        //small_maxHeap = new PriorityQueue<>((a,b)-> b-a);
        large_minHeap = new PriorityQueue<>();
        isOdd = false;
    }

    public void addNum(int num) {
        if(isOdd) {
            small_maxHeap.add(num);
            large_minHeap.add(small_maxHeap.remove());
        } else {
            large_minHeap.add(num);
            small_maxHeap.add(large_minHeap.remove());
        }
        isOdd = !isOdd;
    }

    public double findMedian() {
        if(isOdd) {
            return small_maxHeap.peek();
        } else {
            return (small_maxHeap.peek() + large_minHeap.peek())/2.0;
        }
    }
    
    /*
     * Solution: two priorityQueue - v2
     */
    Queue<Integer> maxHeap;
    Queue<Integer> minHeap;
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        if(minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if(minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek())/2.0;
        } else {
            return maxHeap.peek();
        }
    }
}
