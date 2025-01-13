/*
    92. Reverse Linked List 2
    Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.

    Example 1:
        Input: head = [1,2,3,4,5], left = 2, right = 4
        Output: [1,4,3,2,5]

    Example 2:
        Input: head = [5], left = 1, right = 1
        Output: [5]
    
    Constraints:
        The number of nodes in the list is n.
        1 <= n <= 500
        -500 <= Node.val <= 500
        1 <= left <= right <= n
    
    Follow up: Could you do it in one pass?
 */
package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class P92_ToDo_Medium {
    /*
     * Solution: using 4 pointers 
     * 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head==null) return null;
        ListNode answer = new ListNode(501);
        answer.next = head;
        ListNode dummy = answer;
        for(int i=0; i<left-1; i++) {
            dummy = dummy.next;
        }

        ListNode leftnode = dummy.next;
        for(int i=left; i<right; i++) {
            ListNode nextnode = leftnode.next;
            leftnode.next = nextnode.next;
            nextnode.next = dummy.next;
            dummy.next = nextnode;
        }
        return answer.next;
    }

    /*
     * Solution: with extra space
     * 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        Map<Integer, Integer> map = new HashMap<>();
        int i=1;
        ListNode node = head;
        while(node!=null) {
            if( left <= i && i<=right) {
                map.put(i, node.val);
            }
            i++;
            node = node.next;
        }

        node = head;
        i = 1;
        int j = right;
        while(node!=null) {
            if( left <= i && i<=right) {
                node.val = map.get(j);
                j--;
            }
            i++;
            node = node.next;
        }
        return head;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        Map<Integer, Integer> map = new HashMap<>();
        int pos = 1;
        ListNode node = head;
        while(node!=null) {
            map.put(pos, node.val);
            pos++;
            node = node.next;
        }

        node = head;
        pos = 1;
        while(pos < left) {
            node = node.next;
            pos++;
        }

        int target = right;
        while(pos<=right) {
            node.val = map.get(target);
            target--;
            pos++;
            node = node.next;
        }

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