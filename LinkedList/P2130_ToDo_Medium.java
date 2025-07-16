/*
    2130. maximum twin sum of a linked list
    In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
        For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
    The twin sum is defined as the sum of a node and its twin.
    Given the head of a linked list with even length, return the maximum twin sum of the linked list.

    Example 1:
        Input: head = [5,4,2,1]
        Output: 6
        Explanation:
        Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
        There are no other nodes with twins in the linked list.
        Thus, the maximum twin sum of the linked list is 6. 

    Example 2:
        Input: head = [4,2,2,3]
        Output: 7
        Explanation:
        The nodes with twins present in this linked list are:
        - Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
        - Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
        Thus, the maximum twin sum of the linked list is max(7, 4) = 7. 

    Example 3:
        Input: head = [1,100000]
        Output: 100001
        Explanation:
        There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
    
    Constraints:
        The number of nodes in the list is an even integer in the range [2, 10^5].
        1 <= Node.val <= 10^5
 */
package LinkedList;

import java.util.ArrayList;
import java.util.List;

public class P2130_ToDo_Medium {
    /*
     * Solution: using arrayList
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int pairSum(ListNode head) {
        ListNode dummy = head;
        List<Integer> list = new ArrayList<>();
        while(dummy!=null) {
            list.add(dummy.val);
            dummy = dummy.next;
        }
        int i=0, j = list.size()-1;
        int max = 0;
        while(i<j) {
            max = Math.max(list.get(i++)+list.get(j--));
        }
        return max;
    }

    /*
     * Solution: using stack
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int pairSum(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode dummy = head;
        while(dummy!=null) {
            stack.push(dummy.val);
            dummy = dummy.next;
        }
        int size = stack.size(), count = 0;
        int max = 0;
        while(count < size/2) {
            max = Math.max(max, head.val + stack.pop());
            head = head.next;
            count++;
        }
        return max;
    }

    /*
     * Solution: reversed the half of linked list
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public int pairSum(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next !=null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode next, prev = null;
        while(slow!=null) {
            next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        int max = 0;
        while(prev!=null) {
            max = Math.max(max, head.val + prev.val);
            head = head.next;
            prev = prev.next;
        }
        return max;
    }
}

/*
 * Python
 */
class Solution:
    def pairSum(self, head: Optional[ListNode]) -> int:
        slow, fast = head, head
        maxSum = 0

        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next
        
        prev = None
        while slow:
            slow.next, prev, slow = prev, slow, slow.next
        while prev:
            maxSum = max(maxSum, prev.val + head.val)
            prev = prev.next
            head = head.next
        return maxSum

    def pairSum(self, head: Optional[ListNode]) -> int:
        lists = []
        dummy = head
        while dummy:
            lists.append(dummy.val)
            dummy = dummy.next

        i, j = 0, len(lists)-1
        maxSum = 0
        while i<j:
            maxSum = max(maxSum, lists[i]+lists[j])
            i+=1
            j-=1
        return maxSum