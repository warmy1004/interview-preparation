/*
    1448. Count good nodes in binary tree
    Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
    Return the number of good nodes in the binary tree.

    Example 1:
        Input: root = [3,1,4,3,null,1,5]
        Output: 4
        Explanation: Nodes in blue are good.
        Root Node (3) is always a good node.
        Node 4 -> (3,4) is the maximum value in the path starting from the root.
        Node 5 -> (3,4,5) is the maximum value in the path
        Node 3 -> (3,1,3) is the maximum value in the path

    Example 2:
        Input: root = [3,3,null,4,2]
        Output: 3
        Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it

    Example 3:
        Input: root = [1]
        Output: 1
        Explanation: Root is considered as good.

    Constraints:
        The number of nodes in the binary tree is in the range [1, 10^5].
        Each node's value is between [-10^4, 10^4]. 
 */
package BinaryTree;

import java.util.ArrayDeque;
import java.util.Queue;

public class P1448_Medium {
    /*
     * Solution: DFS, recursion
     * time complexity: O(n)
     * space complexity: O(n)
     */
    private int count = 0;
    public int goodNodes(TreeNode root) {
        dfs(root, Integer.MIN_VALUE);
        return count;
    }
    private void dfs(TreeNode node, int maxValue) {
        if(node==null) return;
        if(node.val >= maxValue) count++;
        dfs(node.left, Math.max(maxValue, node.val));
        dfs(node.right, Math.max(maxValue, node.val));
    }

    /*
     * Solution: DFS, recursion
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int goodNodes(TreeNode root) {
        return 1 + helper(root.left, root.val) + helper(root.right, root.val);
    }

    private int helper(TreeNode node, int max) {
        int count = 0;
        if(node==null) return 0;
        if(node.val>=max) {
            count ++;
            max = Math.max(max, node.val);
        }
        count += helper(node.left, max);
        count += helper(node.right, max);
        return count;
    }

    /*
     * Solution: DFS, recursion
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int goodNodes(TreeNode root) {
        return dfs1(root, root.val);
    }

    private int dfs1(TreeNode node, int max) {
        if(node==null) return 0;
        int answer = node.val >= max ? 1 : 0;
        max = Math.max(max, node.val);
        answer += dfs1(node.left, max);
        answer += dfs1(node.right, max);
        return answer;
    }

    /*
     * Solution: BFS
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int goodNodes(TreeNode root) {
        int count = 0;
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(root, Integer.MIN_VALUE));
        while(!queue.isEmpty()) {
            Pair curr = queue.poll();
            if(curr.max<=curr.val) count++;
            if(curr.left!=null) {
                queue.add(curr.left, Math.max(curr.max, curr.val));
            }
            if(curr.right!=null) {
                queue.add(curr.right, Math.max(curr.max, curr.val));
            }
        }
        return count;
    }
}

class Pair {
    public TreeNode node;
    public int max;
    public Pair(TreeNode node, int max) {
        this.node = node;
        this.max = max;
    }
}
