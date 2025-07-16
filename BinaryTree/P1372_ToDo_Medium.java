/*
    1372. Longest zigzag path in a binary tree
    You are given the root of a binary tree.
    A ZigZag path for a binary tree is defined as follow:
        Choose any node in the binary tree and a direction (right or left).
        If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
        Change the direction from right to left or from left to right.
        Repeat the second and third steps until you can't move in the tree.
    Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
    Return the longest ZigZag path contained in that tree.

    Example 1:
    Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
    Output: 3
    Explanation: Longest ZigZag path in blue nodes (right -> left -> right).

    Example 2:
    Input: root = [1,1,1,null,1,null,null,1,1,null,1]
    Output: 4
    Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).

    Example 3:
    Input: root = [1]
    Output: 0
    
    Constraints:
        The number of nodes in the tree is in the range [1, 5 * 10^4].
        1 <= Node.val <= 100
 */
package BinaryTree;

public class P1372_ToDo_Medium {
    /*
     * Solution: DFS
     * 
     * Intuition:
     *  isLeftMove = true -> come to the left child of the parent node
     *  isLeftMove = false -> come to the right child of the parent node
     * 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    int count = 0;
    public int longestZigZag(TreeNode root) {
        dfs(root, true, 0);
        return count;
    }

    void dfs(TreeNode node, boolean isLeftMove, int currDepth) {
        if(node == null) return;
        count = Math.max(count, currDepth);
        if(isLeftMove) {
            dfs(node.left, false, currDepth+1); // node.left is the left child of the current node, so isLeftMove is false
            dfs(node.right, true, 1);
        } else {
            dfs(node.left, false, 1); // "false" means the current node(node.left) is a left child of the parent node
            dfs(node.right, true, currDepth+1); // "true" means the current node (node.right) is a right child of the parent node
        }
    }
}
