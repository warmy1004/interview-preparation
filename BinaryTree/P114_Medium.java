/*
    114. Flatten binary tree to linked list
    Given the root of a binary tree, flatten the tree into a "linked list":
        The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
        The "linked list" should be in the same order as a pre-order traversal of the binary tree.
    
    Example 1:

    Input: root = [1,2,5,3,4,null,6]
    Output: [1,null,2,null,3,null,4,null,5,null,6]

    Example 2:
    Input: root = []
    Output: []

    Example 3:
    Input: root = [0]
    Output: [0]
    
    Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -100 <= Node.val <= 100
 

Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */
package LinkedList;

import java.util.Stack;

public class P114_Medium {
    /*
     * Solution: using stack
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public void flatten(TreeNode root) {
        if(root==null) return ;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        if(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.right!=null) {
                stack.push(node.right);
            }
            if(node.left!=null) {
                stack.push(node.left);
            }
            node.left =null;
            node.right = stack.isEmpty() ? null : stack.peek();
        }
    }

    /*
     * Solution: using pointer 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public void flatten(TreeNode root) {
        if(root==null) return;
        TreeNode node = root;
        while(node!=null) {
            if(node.left!=null) {
                TreeNode rightmost = node.left;
                while(rightmost.right!=null) {
                    rightmost = rightmost.right;
                }
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }

    /*
     * Solution: recursion 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public void flatten(TreeNode root) {
        if(root==null) return;
        TreeNode left = root.left;
        //save the current right for concatenation
        TreeNode right = root.right;
        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode cur = root;

        // move to the end of newly added flatten subtree
        while(cur.right!=null) cur = cur.right;
        // concatenate the left flatten subtree to the flatten right subtree
        cur.right = right;
    }

    public void flatten(TreeNode root) {
        if(root==null) return;
        flatten(root.left);
        flatten(root.right);
        TreeNode right = root.right;
        if(root.left!=null) {
            root.right = root.left;
            root.left = null;
            while(root.right!=null) {
                root = root.right;
            }
            root.right = right;
        }
    }

    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root==null) return;
        // to set root.right as prev of root.left, root.right needs to flatten first
        flatten(root.right); 
        flatten(root.left);
        root.left = null;
        root.right = prev;
        prev = root;
    }
}

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}