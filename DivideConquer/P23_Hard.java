/*
    23. Merge k sorted lists
    You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
    Merge all the linked-lists into one sorted linked-list and return it.

    Example 1:
        Input: lists = [[1,4,5],[1,3,4],[2,6]]
        Output: [1,1,2,3,4,4,5,6]
        Explanation: The linked-lists are:
            [
            1->4->5,
            1->3->4,
            2->6
            ]
            merging them into one sorted list:
            1->1->2->3->4->4->5->6

    Example 2:
        Input: lists = []
        Output: []

    Example 3:
        Input: lists = [[]]
        Output: []
    
    Constraints:
        k == lists.length
        0 <= k <= 10^4
        0 <= lists[i].length <= 500
        -10^4 <= lists[i][j] <= 10^4
        lists[i] is sorted in ascending order.
        The sum of lists[i].length will not exceed 10^4.
 */
package DivideConquer;

import java.lang.classfile.components.ClassPrinter;
import java.util.PriorityQueue;

public class P23_Hard {
    /*
     * Solution: merge sort
     * time complexity: O(nlogK) where k is the number of linked lists
     * space complexity: O(1)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int window = 1;
        int n = lists.length;
        while(window < n) {
            for(int i=0; i<n-window; i+=window*2) {
                lists[i] = merge(lists[i], lists[i+window]);
            }
            window *=2;
        }
        return n>0? lists[0] : null;
    }

    ListNode merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode root = head;
        while(list1!=null && list2!=null) {
            if(list1.val < list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        if(list1==null) {
            head.next = list2;
        }
        if(list2 == null) {
            head.next = list1;
        }
        return root.next;
    }

    /*
     * Solution: merge sort v2
     * time complexity: O(nlogn)
     * space complexity: O(1)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return partition(lists, 0, lists.length-1);
    }

    ListNode partition(ListNode[] lists, int start, int end) {
        if(start == end) return  lists[start];
        if(start < end) {
            int mid = (start+end)/2;
            ListNode left = partition(lists, start, mid);
            ListNode right = partition(lists, mid+1, end);
            return merge(left, right);
        }
        return null;
    }

    ListNode merge(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        if(l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
    
    /*
     * Solution: with priority queue
     * time complexity: O(nlogK)
     * space complexity: O(k)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b)-> a.val-b.val);
        for(ListNode list: lists) {
            if(list!=null) pq.add(list);
        }

        ListNode answer = new ListNode(-1);
        ListNode dummy = answer;
        while(!pq.isEmpty()) {
            dummy.next = pq.poll();
            dummy = dummy.next;
            if(dummy.next!=null) {
                pq.add(dummy.next);
            }
        }
        return answer.next;
    }
}

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}