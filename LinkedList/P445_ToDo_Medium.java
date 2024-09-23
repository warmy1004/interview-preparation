/*
    445. Add two numbers 2
    You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example 1:
        Input: l1 = [7,2,4,3], l2 = [5,6,4]
        Output: [7,8,0,7]

    Example 2:
        Input: l1 = [2,4,3], l2 = [5,6,4]
        Output: [8,0,7]

    Example 3:
        Input: l1 = [0], l2 = [0]
        Output: [0]
        
    Constraints:
        The number of nodes in each linked list is in the range [1, 100].
        0 <= Node.val <= 9
        It is guaranteed that the list represents a number that does not have leading zeros.
    
        Follow up: Could you solve it without reversing the input lists?
    */
package LinkedList;

public class P445_ToDo_Medium {
    /*
     * Solution 1: Reverse
     * time complexity: O(max(n, m))
     * space complexity: O(1)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r1 = reverseList(l1);
        ListNode r2 = reverseList(l2);

        ListNode head = new ListNode(-1);
        ListNode answer = head;
        int carry = 0;

        while( r1!=null || r2!=null || carry!=0) {
            int sum = carry;
            if(r1!=null) {
                sum+= r1.val;
                r1 = r1.next;
            }
            if(r2!=null) {
                sum+= r2.val;
                r2= r2.next;
            }
            head.next = new ListNode(sum%10);
            carry = sum / 10;
            head = head.next;
        }
        return reverseList(answer.next);
    }

    ListNode reverseList(ListNode head) {
        ListNode temp, prev = null; 
        while(head!=null) {
            temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

    /*
     * Solution 2: Stack
     * time complexity: O(n+m)
     * space complexity: O(m+n)
     */
    public ListNode addTwoNumbers_stack(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while(l1!=null) {
            s1.add(l1.val);
            l1 = l1.next;
        }
        while(l2!=null) {
            s2.add(l2.val);
            l2= l2.next;
        }

        int carry = 0;
        ListNode dummy = null;
        while(!s1.isEmpty() || !s2.isEmpty() || carry!=0) {
            int x = s1.isEmpty() ? 0: s1.pop();
            int y = s2.isEmpty() ? 0: s2.pop();
            int total = x + y + carry;
            carry = total / 10;

            ListNode next = new ListNode(total%10);
            next.next = dummy;
            dummy = next;
        }
        return dummy;
    }
}
