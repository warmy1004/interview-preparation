/*
    25. Reverse nodes in k-group
    Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
    k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
    You may not alter the values in the list's nodes, only nodes themselves may be changed.

    Example 1:
    Input: head = [1,2,3,4,5], k = 2
    Output: [2,1,4,3,5]

    Example 2:
    Input: head = [1,2,3,4,5], k = 3
    Output: [3,2,1,4,5]
    
    Constraints:
    The number of nodes in the list is n.
    1 <= k <= n <= 5000
    0 <= Node.val <= 1000

    Follow-up: Can you solve the problem in O(1) extra memory space?
 */
package LinkedList;

public class P25_Must_ToDo_Hard {
    /*
     * Solution: recursive
     * time complexity: O(n)
     * space complexity: O(n/k)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode curr = head;
        while(count<k && curr!=null) {
            curr = curr.next;
            count++;
        }

        if(count == k) {
            curr = reverseKGroup(curr, k);
            while(count>0) {
                ListNode next = head.next;
                head.next = curr;
                curr = head;
                head = next;
                count--;
            }
            head = curr;
        }
        return head;
    }

    /*
     * Solution: recursion
     * time complexity: O(n)
     * space complexity: O(n/k)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode ptr = head;
        while(count<k && ptr!=null) {
            ptr = ptr.next;
            count++;
        }

        if(count == k) {
            ListNode reversedHead = reverse(head, k);
            head.next = reverseKGroup(ptr, k);
            return reversedHead;
        }
        return head;
    }

    private ListNode reverse(ListNode head, int k) {
        ListNode newHead = null;
        ListNode ptr = head;
        while(k>0) {
            ListNode nextNode = ptr.next;
            ptr.next = newHead;
            newHead = ptr;
            ptr = nextNode;
            k--;
        }
        return newHead;
    }

    /*
     * Solution : recursive
     * time compelexity: O(n)
     * space complexity: O(1)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode dummy = head;
        while(dummy!=null && count < k) {
            dummy = dummy.next;
            count++;
        }
        if(count < k) return head;
        
        ListNode tail = head;
        ListNode front = reverse(head, count);
        tail.next = reverseKGroup(dummy, k);
        return front;
    }

    ListNode reverse(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        while(k-->0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /*
     * Solution : iterative o(1) space
     * time compelexity: O(n)
     * space complexity: O(1)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode ptr = head;
        ListNode ktail = null;
        ListNode newhead = null;
        while(ptr!=null) {
            int count = 0;
            ptr = head;
            while(count<k && ptr!=null) {
                ptr = ptr.next;
                count++;
            }

            if(count == k) {
                ListNode reversedhead = reverseLinkedList(head, k);
                if(newhead == null) {
                    newhead = reversedhead;
                }
                if(ktail!=null) ktail.next = reversedhead;
                ktail = head;
                head = ptr;
            }
        }
        if(ktail!=null) ktail.next = head;
        return newhead == null? head : newhead;
    }

    private ListNode reverseLinkedList(ListNode head, int k) {
        ListNode newhead = null;
        ListNode ptr = head;
        while(k>0) {
            ListNode nextnode = ptr.next;
            ptr.next = newhead;
            newhead = ptr;
            ptr = nextnode;
            k--;
        }
        return newhead;
    }

    /*
     * Solution : iterative o(1) space
     * time compelexity: O(n)
     * space complexity: O(1)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pretail = null;
        ListNode curhead = head;
        ListNode curtail = head;
        ListNode nexthead = null;

        while(curhead!=null) {
            int count = 1;
            while(count<k && curtail.next != null) {
                curtail = curtail.next;
                count++;
            }
            if(count==k) {
                nexthead = curtail.next;
                curtail.next = null;
                if(pretail!= null) {
                    pretail.next = null;
                }
                curtail = reverseHeadTail(curhead);
                if(pretail!=null) {
                    pretail.next = curtail;
                } else {
                    head = curtail;
                }
                curhead.next = nexthead;
                pretail = curhead;
                curhead = nexthead;
                curtail = nexthead;
            }
        }
        return head;
    }

    private ListNode reverseHeadTail(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        ListNode next = node;
        while(cur!=null) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*
     * Solution : recursive
     * time compelexity: O(n)
     * space complexity: O(1)
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode main = head;
        //places holder of head
        // reverse linked list
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode newhead = dummy;
        while(main!=null) {
            ListNode curr = main;

            // get the next group's first node
            int i = 0;
            while(i < k && curr!=null) {
                curr= curr.next;
                i++;
            }
            ListNode nextGroupFirstNode = curr;

            // if available node is K, then reverse and add in newhead list
            // otherwise, just append remaining node
            if(i == k) {
                newhead.next = getReverseFirstKNodes(main, k);
                newhead = main;
            } else {
                newhead.next = main;
            }

            // move to the next group
            main = nextGroupFirstNode;
        }
        return dummy.next;
    }

    ListNode getReverseFirstKNodes(ListNode main, int k) {
        ListNode prev = null;
        ListNode curr = main;
        while(curr!=null && k-->0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
