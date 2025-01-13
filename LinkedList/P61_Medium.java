/*
    61. Rotate List
    Given the head of a linked list, rotate the list to the right by k places.

Example 1:
    Input: head = [1,2,3,4,5], k = 2
    Output: [4,5,1,2,3]

Example 2:
    Input: head = [0,1,2], k = 4
    Output: [2,0,1]

Constraints:
    The number of nodes in the list is in the range [0, 500].
    -100 <= Node.val <= 100
    0 <= k <= 2 * 10^9
 */
package LinkedList;

public class P61_Medium {
    /*
     * Solution: pointers
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next==null) return head;
        ListNode tail = head;
        int count = 1;
        while(tail.next!=null) {
            tail = tail.next;
            count++;
        }
        tail.next = head;

        k%=count;
        ListNode dummy = head;
        for(int i=0; i<count-k-1; i++) {
            dummy = dummy.next;
        }
        ListNode start = dummy.next;
        dummy.next = null;
        return start;
    }

    /*
     * Solution: one pointer
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null || head.next ==null) {
            return head;
        }
        ListNode node = head;
        int count = 1;
        while(node.next!=null) {
            node = node.next;
            count++;
        }
        node.next = head;
        k%=count;
        for(int i=count-k; i>0; i--) {
            node = node.next;
        }
        head = node.next;
        node.next=null;
        return head;
    }
}
