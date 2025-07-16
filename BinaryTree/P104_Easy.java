/*
    104. maximum depth of binary tree
    Given the root of a binary tree, return its maximum depth.
    A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: 3

    Example 2:
    Input: root = [1,null,2]
    Output: 2

    Constraints:
        The number of nodes in the tree is in the range [0, 10^4].
        -100 <= Node.val <= 100
 */
package BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

public class P104_Easy {
    /*
     * Solution: recursive
     * time complexity: O(n)
     * space complexity: O(logN)
     */
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        else {
            int left = maxDepth(root.left);
            int right = maxDepth(root.right);
            return Math.max(left, right)+1;
        }
    }

    /*
     * Solution: Iterative
     * time complexity: O(n)
     * space complexity: O(logN)
     */
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        int count = 0;
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                TreeNode node = queue.poll();
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            count++;
        }
        return count;
    }
}

/*
 * python3
 */
class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        if root == None:
            return 0
        queue = deque([root])
        count = 0
        while queue:
            n = len(queue)
            for i in range(n):
                node = queue.popleft()
                if node.left: queue.append(node.left)
                if node.right: queue.append(node.right)
            count+=1
        return count