/*
    117. Populating next right pointers in each node 2
    Given a binary tree
        struct Node {
        int val;
        Node *left;
        Node *right;
        Node *next;
        }
    Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
    Initially, all next pointers are set to NULL.

    Example 1:
    Input: root = [1,2,3,4,5,null,7]
    Output: [1,#,2,3,#,4,5,7,#]
    Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.

    Example 2:
    Input: root = []
    Output: []
    
    Constraints:
    The number of nodes in the tree is in the range [0, 6000].
    -100 <= Node.val <= 100
    
    Follow-up:
        You may only use constant extra space.
        The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
package LinkedList;

import java.lang.module.FindException;
import java.util.ArrayDeque;
import java.util.Deque;

public class P117_ToDo_Medium {
    /*
     * Solution: Level order traversal using queue
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public Node connect(Node root) {
        if(root == null) return root;
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                Node node = queue.pop();
                if(i<size-1) {
                    node.next = queue.peek();
                }
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    /*
     * Solution: Using previously established next pointers
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public Node connect(Node root) {
        Node leftmost = new Node(0);
        Node prev = leftmost; // prev and leftmost will be updated at the same time, because it's referencing the same object
        Node curr = root;
        while(curr!=null) {
            if(curr.left!=null) {
                prev.next = curr.left;
                prev = prev.next;
            }
            if(curr.right!=null) {
                prev.next = curr.right;
                prev = prev.next;
            }
            curr = curr.next;
            if(curr==null) {
                // move to the next level's first node
                curr = leftmost.next;

                // back to the original state    
                prev = leftmost;          
                leftmost.next = null;
            }
        }
        return root;
    }

    public Node connect(Node root) {
        if(root==null) return root;
        Node curr = root;
        while(curr!=null) {
            Node leftmost = new Node(-1);
            Node prev = leftmost;

            while(curr!=null) {
                if(curr.left!=null) {
                    prev.next = curr.left;
                    prev = prev.next;
                }
                if(curr.right!=null) {
                    prev.next = curr.right;
                    prev = prev.next;
                }
                curr = curr.next;
            }
            curr = leftmost.next;
        }
        return root;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};