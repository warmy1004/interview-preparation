/*
    530. Minimum absolute difference in bst
    Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.

    Example 1:
    Input: root = [4,2,6,1,3]
    Output: 1

    Example 2:
    Input: root = [1,0,48,null,null,12,49]
    Output: 1
    
    Constraints:
    The number of nodes in the tree is in the range [2, 10^4].
    0 <= Node.val <= 10^5
    
    Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
package BFS_DFS_BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P530_Easy {
    /*
     * Solution: In-order traversal without list
     * time complexity: O(n)
     * space complexity: O(n) due to recursive
     */ 
    int minDiff = Integer.MAX_VALUE;
    TreeNode prev;
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        if(prev!=null) {
            minDiff = Math.min(minDiff, root.val - prev.val);
        }
        prev = root;
        inorder(root.right);
    } 

    /*
     * Solution: Inorder traversal with list
     * time complexity:
     * space complexity:
     */
    List<Integer> inorderList = new ArrayList<>();
    public int getMinimumDifference(TreeNode root) {
        inorderTraversal(root);

        int min = Integer.MAX_VALUE;
        for(int i=1; i<inorderList.size(); i++) {
            min = Math.min(min, inorderList.get(i)-inorder(i-1));
        }
        return min;
    }
    private void inorderTraversal(TreeNode node) {
        if(root == null) return;
        inorderTraversal(node.left);
        inorderList.add(node.val);
        inorderTraversal(node.right);
    }

    /*
     * Solution: Iterative with stack
     * 
     * Approach: BTS에서 두 값 간의 최소 차이를 구하려면 in-order travelsal를 활용하여 정렬된 순서로 값을 탐색하면서, 이전 노드와 현재 노드 간의 차이를 비교하면 된다.
     * time complexity: O(n)
     * space complexity: O(h)
     */
    public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        TreeNode prev = null;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while(!stack.isEmpty() || curr!=null) {
            while(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            if(prev!=null) {
                min = Math.min(min, curr.val - prev.val);
            }
            prev = curr;
            curr = curr.right;
        }
        return min;

    }
}
