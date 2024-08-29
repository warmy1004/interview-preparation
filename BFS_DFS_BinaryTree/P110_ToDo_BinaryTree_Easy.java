/*
    110. Balanced Binary Tree

    Given a binary tree, determine if it is height-balanced.

    Example 1:
        Input: root = [3,9,20,null,null,15,7]
        Output: true

    Example 2:
        Input: root = [1,2,2,3,3,null,null,4,4]
        Output: false

    Example 3:
        Input: root = []
        Output: true
    
    Constraints:
        The number of nodes in the tree is in the range [0, 5000].
        -10^4 <= Node.val <= 10^4
 */

package BFS_DFS_BinaryTree;

import java.util.HashMap;

import javax.swing.tree.TreeNode;

/*
 * height-balanced tree
 *      You can check if a tree is height-balanced using recursion based on the idea that every subtree of the tree will be also be height-balanced. 
 *      To check if a tree is height-balanced perform the following operations:
 *          Use recursion and visit the left subtree and right subtree of each node:
 *              - check the height of the left subtree and right subtree
 *              - if the absolute difference between their heights is at most 1 then that node is height-balanced
 *              - otherwise, that node and the whole tree is not balanced
 */

public class P110_ToDo_BinaryTree_Easy {
    /*
     * Solution 1: Top Down recursion 1
     * 
     * height(p) = 0 if p is null
     *           = -1 if abs(height(p.left)-height(p.right)) > 1, which means it's NOT height balanced
     *           = 1 + max(height(p.left), height(p.right)) otherwise
     */
    public boolean isBalanced(TreeNode root) {
        if (root==null) return true;
        if (dfs_height(root)==-1) return false;
        return true;
    }
    private int dfs_height(TreeNode root) {
        if(root == null) return 0;
        int leftsubtree_height = dfs_height(root.left);
        int rightsubtree_height = dfs_height(root.right);
        if(leftsubtree_height == -1 || rightsubtree_height == -1) return -1;
        if(Math.abs(leftsubtree_height - rightsubtree_height)>1) return -1;
        return 1 + Math.max(leftsubtree_height, rightsubtree_height);
    }
    
    /*
     * Solution 2: Top down resursion 2
     * 
     * height(p) = -1 p is an empty subtree i.e. null
     *           = 1 + max(height(p.left), height(p.right)) otherwise
     * 
     * time complexity: O(nlogn), the height h of a balanced tree is bounded by O(logn). With this bound we can guarantee that dfs_height will be called on each node O(logn) times
     * space complexity: O(n), the recursion stack may contain all nodes if the tree is skewed
     */
    // public boolean isBalanced(TreeNode root) {
    //     // an empty tree satisfies the definition of a balanced tree
    //     if(root==null) return true;

    //     // check if subtrees have height within 1. If they do, check if the subtrees are balanced
    //     return (
    //         Math.abs(dfs_height(root.left)-dfs_height(root.right)) < 2 &&
    //         isBalanced(root.left) &&
    //         isBalanced(root.right)
    //     );
    // }
    // //recursively obtain the height of a tree. An empty tree has -1 height
    // private int dfs_height(TreeNode root) {
    //     if (root==null) return -1;
    //     return 1 + Math.max(dfs_height(root.left), dfs_height(root.right));
    // }
}
