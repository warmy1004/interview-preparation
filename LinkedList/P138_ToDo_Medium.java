/*
    138. Copy list with random pointer
    A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
    Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
    For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
    Return the head of the copied linked list.
    The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
        val: an integer representing Node.val
        random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
    Your code will only be given the head of the original linked list.

    Example 1:
        Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
        Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]

    Example 2:
        Input: head = [[1,1],[2,1]]
        Output: [[1,1],[2,1]]

    Example 3:
        Input: head = [[3,null],[3,0],[3,null]]
        Output: [[3,null],[3,0],[3,null]]
    
    Constraints:
        0 <= n <= 1000
        -10^4 <= Node.val <= 10^4
        Node.random is null or is pointing to some node in the linked list.
 */
package LinkedList;

import java.util.HashMap;
import java.util.Map;

public class P138_ToDo_Medium {
    /*
     * Solution: Iterative with O(1) space
     * 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public Node copyRandomList(Node head) {
        // Needed to cover head=[]
        if(head==null) {
            return head;
        }
        Node node = head;
        while(node!=null) {
            Node one = new Node(node.val);
            one.next = node.next;
            node.next = one;
            node = one.next;
        }

        node = head;
        while(node!=null) {
            if(node.random!=null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        // Need oldList in order to make head back to the original status
        Node oldList = head;
        Node newList = head.next;
        Node answer = head.next;
        while(oldList!=null) {
            oldList.next = oldList.next.next;
            // null check is needed because newList is started from head.next
            newList.next = newList.next !=null? newList.next.next : null;
            oldList = oldList.next;
            newList = newList.next;
        }
        return answer;
    }

    /*
     * Solution: Iterative with O(n) space
     * 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public Node copyRandomList(Node head) {
        if(head==null) {
            return head;
        }

        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        while(node!=null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        node = head;
        while(node!=null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }
}

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}