/*
    148. Sort List
    Given the head of a linked list, return the list after sorting it in ascending order.
    Example 1:
        Input: head = [4,2,1,3]
        Output: [1,2,3,4]

    Example 2:
        Input: head = [-1,5,3,4,0]
        Output: [-1,0,3,4,5]

    Example 3:
        Input: head = []
        Output: []
    
    Constraints:
        The number of nodes in the list is in the range [0, 5 * 10^4].
        -10^5 <= Node.val <= 10^5
    
    Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
package DivideConquer;

public class P148_ToDo_Medium {
    /*
     * Solution: Divide and Conquer with two pointers
     * time complexity: O(nlogn)
     * space complexity: O(logn) because of the recursive stack calls
     */
    public ListNode sortList(ListNode head) {
        // head.next prevents NPE in findMid() when head has only one ListNode
        if(head==null || head.next == null) return head;
        ListNode mid = findMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    ListNode findMid(ListNode head) {
        ListNode fast=head, mid = head, prev = null;
        while(fast!=null && fast.next!=null) {
            prev = mid;
            mid = mid.next;
            fast = fast.next.next;
        }
        prev.next = null;
        return mid;
    }

    ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1), root = dummy;
        while(left!=null && right!=null) {
            if (left.val < right.val) {
                dummy.next = left;
                left = left.next;
                dummy = dummy.next;
            } else {
                dummy.next = right;
                right = right.next;
                dummy = dummy.next;
            }
        }
        if(left == null) dummy.next = right;
        else if(right == null) dummy.next = left;
        return root.next;
    }
}

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}