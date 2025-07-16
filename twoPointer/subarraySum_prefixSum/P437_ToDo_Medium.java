/*
    437. path sum 3
    Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
    The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

    Example 1:
    Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
    Output: 3
    Explanation: The paths that sum to 8 are shown.

    Example 2:
    Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
    Output: 3
    
    Constraints:
        The number of nodes in the tree is in the range [0, 1000].
        -10^9 <= Node.val <= 10^9
        -1000 <= targetSum <= 1000
 */
package BinaryTree;

import java.util.HashMap;

public class P437_ToDo_Medium {
    /*
     * Solution: recursive
     * time complexity: O(n)
     * space complexity: O(n)
     */
    private int count = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        dfs(root, targetSum, 0);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return count;
    }
    void dfs(TreeNode node, int target, long curr) {
        if(node == null) return;
        curr += node.val;
        if(curr == target) count++;
        dfs(node.left, target, curr);
        dfs(node.right, target, curr);
    }

    /*
     * Solution:
     * time complexity:
     * space complexity:
     */
    public int pathSum(TreeNode root, int targetSum) {
        if(root==null) return 0;
        int count = helper1(root, targetSum);
        return count + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    private int helper1(TreeNode node, long targetSum) {
        if(node == null) return 0;
        int count = 0;
        if(node.val == targetSum) count++;
        return count + helper1(node.left, targetSum-node.val) + helper1(node.right, targetSum-node.val);
    }

    /*
     * Solution: Prefix sum with hashmap as preorder traversal
     * time complexity:O(n), where n is a number of nodes. during preorder traversal, each node is visited once.
     * space complexity:O(n), up to O(n) to keep the hashmap of prefix sums, where N is a number of nodes
     */
    int total = 0;
    Map<Long, Integer> map = new HashMap<>();
    int target;
    public int pathSum(TreeNode root, int targetSum) {
        target = targetSum;
        helper(root, 0);
        return total;
    }

    private void helper(TreeNode node, long currSum) {
        if(node==null) return;

        currSum += node.val;
        if(currSum == target) total++;

        total+= map.getOrDefault(currSum-target, 0);
        map.put(currSum, map.getOrDefault(currSum, 0)+1);
        helper(node.left, currSum);
        helper(node.right, currSum);
        //backtracking
        map.put(currSum, map.get(currSum)-1);
    }
}
