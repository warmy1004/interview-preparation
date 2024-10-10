/*
    337. House Robber 2
    The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
    Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
    Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.

    Example 1:
        Input: root = [3,2,3,null,3,null,1]
        Output: 7
        Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

    Example 2:
        Input: root = [3,4,5,1,3,null,1]
        Output: 9
        Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
        

    Constraints:
        The number of nodes in the tree is in the range [1, 10^4].
        0 <= Node.val <= 10^4
 */
package DP;

import java.util.HashMap;

public class P337_Robber_ToDo_Medium {
    /*
     * Solution: Recursion - top down DP
     * time complexity: O(n)
     * space complexity: O(n)
     *      Since we need stacks to do recursion, and the maximum dpeth of the recursion is the height of the tree, which is O(N) in the worst case and O(logN) in the best case.
     */
    public int rob(TreeNode root) {
        // dp[0] : selecting the current node --> ROB
        // dp[1] : ignoring the current node --> NOT Rob
        int[] dp = dfs(root);
        return Math.max(dp[0], dp[1]);
    }

    public int[] dfs(TreeNode node) {
        if(node==null) {
            return new int[2];
        }

        int[] leftchild = dfs(node.left);
        int[] rightchild = dfs(node.right);

        int rob = node.val + leftchild[1] + rightchild[1];
        int notrob = Math.max(leftchild[0], leftchild[1]) + Math.max(rightchild[0], rightchild[1]);
        return new int[]{rob, notrob};
    }

    /*
     * Solution: Recursion with hashMap
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> dp = new HashMap<>();
        return dfs(root, dp);
    }
    private int dfs(TreeNode node, Map<TreeNode, Integer> dp) {
        if(node==null) {
            return 0;
        }
        if(dp.containsKey(node)){
            return dp.get(node);
        }

        int rob = node.val;
        if(node.left!=null) {
            rob+= dfs(node.left.left, dp) + dfs(node.left.right, dp);
        }
        if(node.right!=null) {
            rob+=dfs(node.right.left, dp) + dfs(node.right.right, dp);
        }

        int notrob = dfs(node.left, dp) + dfs(node.right, dp);

        int result = Math.max(rob, notrob);
        dp.put(node, result);
        return result;
    }
}
