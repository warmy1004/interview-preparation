/*
    19. Remove nth node from end of list
    Given the head of a linked list, remove the nth node from the end of the list and return its head.
    
    Example 1:
        Input: head = [1,2,3,4,5], n = 2
        Output: [1,2,3,5]

    Example 2:
        Input: head = [1], n = 1
        Output: []

    Example 3:
        Input: head = [1,2], n = 1
        Output: [1]
    
    Constraints:
        The number of nodes in the list is sz.
        1 <= sz <= 30
        0 <= Node.val <= 100
        1 <= n <= sz
    
    Follow up: Could you do this in one pass?
 */
package LinkedList;

public class P19_Medium {
    /*
     * Solution: two pass with one pointer
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // there is a case to remove the first node of head, so making a new ListNode answer with a dummy node
        ListNode answer = new ListNode(-1);
        answer.next = head;
        
        int count = 0;
        ListNode dummy = head;
        while(dummy!=null) {
            count++;
            dummy = dummy.next;
        }
        count -= n;

        // to prevent a null point exception when the first node of head needs to be removed
        dummy = answer;
        while(count>0) {
            dummy = dummy.next;
            count--;
        }
        dummy.next = dummy.next.next;
        return answer.next;
    }
    
    /*
     * Solution: one pass with two pointers
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode answer = new ListNode(-1);
        answer.next = head;
        ListNode first = answer;
        ListNode second = answer;
        for(int i=0; i<n+1; i++) {
            first = first.next;
        }

        while(first!=null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return answer.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        for(int i=0; i<n; i++) {
            fast = fast.next;
        }
        if(fast==null) return head.next;
        while(fast.next!=null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}