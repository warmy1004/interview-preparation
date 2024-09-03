/*
    124. Binary Tree Maximum Path Sum
    A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
    The path sum of a path is the sum of the node's values in the path.
    Given the root of a binary tree, return the maximum path sum of any non-empty path.

    Example 1:
        Input: root = [1,2,3]
        Output: 6
        Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

    Example 2:
        Input: root = [-10,9,20,null,null,15,7]
        Output: 42
        Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

    Constraints:
        The number of nodes in the tree is in the range [1, 3 * 10^4].
        -1000 <= Node.val <= 1000
 */

package BFS_DFS_BinaryTree;

public class P124_Hard {
    /*
     * Solution: postorder DFS
     * time complexity: O(n)
     * space complexity: O(n)
     */
    int maxPath;
    public int maxPathSum(TreeNode root) {
        maxPath = Integer.MIN_VALUE;
        dfs(root);
        return maxPath;
    }

    private int dfs(TreeNode root) {
        if(root==null) return 0;
        int leftPath = dfs(root.left);
        int rightPath = dfs(root.right);
        maxPath = Math.max(maxPath, root.val + leftPath + rightPath);
        return Math.max(0, root.val+ Math.max(leftPath, rightPath));
    }
}
