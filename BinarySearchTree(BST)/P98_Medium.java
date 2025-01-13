/*
    98. Validate Binary Search Tree
    Given the root of a binary tree, determine if it is a valid binary search tree (BST).
    A valid BST is defined as follows:
        The left subtree of a node contains only nodes with keys less than the node's key.
        The right subtree of a node contains only nodes with keys greater than the node's key.
        Both the left and right subtrees must also be binary search trees.
    
    Example 1:
        Input: root = [2,1,3]
        Output: true

    Example 2:
        Input: root = [5,1,4,null,null,3,6]
        Output: false
        Explanation: The root node's value is 5 but its right child's value is 4.
    
    Constraints:
        The number of nodes in the tree is in the range [1, 10^4].
        -2^31 <= Node.val <= 2^31 - 1
 */
package BinaryTree.BinarySearchTree(BST);

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P98_Medium {
    /*
     * Solution: recursive inorder traversal
     * time complexity: O(n)
     * space complexity: O(n)
     */
    TreeNode prev = null;
    public boolean isValidBST(TreeNode root) {
        return inorder(root);  
    }
    boolean inorder(TreeNode node) {
        if(node==null) return true;
        if(!inorder(node.left)) return false;
        if(prev!=null && prev.val >= node.val) {
            return false;
        }
        prev = node;
        return inorder(node.right);
    }

    /*
     * Solution: iterative inorder traversal
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prev = null;
        while(!stack.isEmpty() || root!=null) {
            while(root!=null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if(prev!=null && prev.val >= root.val) {
                return false;
            }
            prev = root;
            root = root.right;
        }
        return true;
    }

    /*
     * Solution: recurisve with a range - v1
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    boolean validate(TreeNode root, long min, long max) {
        if(root == null) return true;
        if(root.val >= max || root.val <= min) return false;
        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
    }

    /*
     * Solution: recurisve with a range - v2
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    boolean validate(TreeNode root, Integer min, Integer max) {
        if(root ==null) return true;
        if((max!=null && root.val >= max) || (min!=null && root.val <= min)) return false;
        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
    }

    /*
     * Solution:
     * time complexity:
     * space complexity:
     */
    public boolean isValidBST(TreeNode root) {
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