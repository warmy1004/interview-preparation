/* 
    876. Middle of the Linked List
    Given the head of a singly linked list, return the middle node of the linked list.
    If there are two middle nodes, return the second middle node.

    Example 1:
        Input: head = [1,2,3,4,5]
        Output: [3,4,5]
        Explanation: The middle node of the list is node 3.

    Example 2:
        Input: head = [1,2,3,4,5,6]
        Output: [4,5,6]
        Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
    

    Constraints:
        The number of nodes in the list is in the range [1, 100].
        1 <= Node.val <= 100 
*/

package LinkedList;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Problem876 {
    public ListNode middleNode(ListNode head) {
        //two pointer solution
        ListNode fast = head.next;
        while(fast!=null) {
            head = head.next;
            fast = fast.next!=null ? fast.next.next : fast.next;
        }

        /*
            ListNode fast = head, slow = head;
            while(fast!=null && fast.next!=null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
         */

        /* 
            //Brute Force Solution
            ListNode node = head;
            int count = 0;
            while(node!=null) {
                count++;
                node=node.next;
            }
            for(int i=0; i<count/2; i++) {
                head=head.next;
            }
        */
        return head;
    } 
}

/* 
* time complexity: O(n), where N is the number of nodes in the given list
* space complexity: O(1), the space used by head and fast
*/