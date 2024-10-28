/*
    653. Two Sum IV - Input is a BST
    Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.

    Example 1:
        Input: root = [5,3,6,2,4,null,7], k = 9
        Output: true

    Example 2:
        Input: root = [5,3,6,2,4,null,7], k = 28
        Output: false
    

    Constraints:
        The number of nodes in the tree is in the range [1, 10^4].
        -10^4 <= Node.val <= 10^4
        root is guaranteed to be a valid binary search tree.
        -10^5 <= k <= 10^5
 */
package twoPointer.Sum;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class P653_Easy {
    /*
     * Solution: HashSet
     * time complexity:O(n)
     * space complexity:O(n)
     */
    public boolean findTarget(TreeNode root, int k) {
        return find(root, k, new HashSet<Integer>());
    }
    private boolean find(TreeNode root, int k, Set<Integer> seen) {
        if(root == null) return false;
        if(seen.contains(k-root.val)) return true;
        seen.add(root.val);
        return find(root.left, k, seen) || find(root.right, k, seen);
    }

    /*
     * Solution: HashSet and BFS
     * time complexity:O(n)
     * space complexity:O(n)
     */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> seen = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(seen.contains(k-node.val)) {
                return true;
            }
            seen.add(node.val);
            if(node.left!=null) queue.add(node.left);
            if(node.right!=null) queue.add(node.right);
        }
        return false;
    }
}
