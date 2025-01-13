/*
    82. Remove Duplicates from sorted list 2
    Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.

    Example 1:
        Input: head = [1,2,3,3,4,4,5]
        Output: [1,2,5]

    Example 2:
        Input: head = [1,1,1,2,3]
        Output: [2,3]
    
    Constraints:
        The number of nodes in the list is in the range [0, 300].
        -100 <= Node.val <= 100
        The list is guaranteed to be sorted in ascending order.
 */
package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class P82_ToDo_Medium {
    /*
     * Solution: two pointers
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode fast = head, slow = dummy;
        slow.next = fast;
        while(fast!=null) {
            while(fast.next!=null && fast.val == fast.next.val) {
                fast = fast.next;
            }
            
            if(slow.next != fast) {
                // skip all duplicates
                slow.next = fast.next;
            } else {
                // no dups
                slow = slow.next;
            }
            fast = fast.next;
        }
        return dummy.next;
    }

    /*
     * Solution: recursive
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        if(head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            return head;
        }
        while(head.next!=null && head.val==head.next.val) {
            head = head.next;
        }
        return deleteDuplicates(head.next);
    }
    
    /*
     * Solution: using map
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public ListNode deleteDuplicates(ListNode head) {
        Map<Integer, Integer> map = new HashMap<>();
        ListNode dummy = head;
        while(dummy!=null) {
            map.put(dummy.val, map.getOrDefault(dummy.val, 0)+1);
            dummy = dummy.next;
        }
        dummy = head;
        ListNode answer = new ListNode(-1);
        ListNode node = answer;
        while(dummy!=null) {
            if(map.get(dummy.val)==1) {
                node.next = dummy;
                node = node.next;
            }
            dummy = dummy.next;
        }
        node.next = null;
        return answer.next;
    }
}
