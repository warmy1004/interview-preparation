/*
    236. Lowest common ancestor of a binary tree
    Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

    Example 1:
        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        Output: 3
        Explanation: The LCA of nodes 5 and 1 is 3.

    Example 2:
        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        Output: 5
        Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

    Example 3:
        Input: root = [1,2], p = 1, q = 2
        Output: 1

    Constraints:
        The number of nodes in the tree is in the range [2, 10^5].
        -10^9 <= Node.val <= 10^9
        All Node.val are unique.
        p != q
        p and q will exist in the tree.
 */
package LinkedList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P236_ToDo_Medium {
    /*
     * Solution: Iterative 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        Map<TreeNode, TreeNode> parents = new HashMap<>();
        parents.put(root, null);

        while(!parents.containsKey(p) || !parents.containsKey(q)) {
            TreeNode node = stack.pop();
            if(node.left!=null) {
                stack.push(node.left);
                parents.put(node.left, node);
            }
            if(node.right!=null) {
                stack.push(node.right);
                parents.put(node.right, node);
            }
        }

        Set<TreeNode> seen = new HashSet<>();
        // process all ancestors for node p using parent pointers
        while(p!=null) {
            seen.add(p);
            p = parents.get(p);
        }

        while(!seen.contains(q)) {
            q = parents.get(q);
        }
        return q;
    }
    
    /*
     * Solution: recursive 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left!=null && right!=null) return root;
        if(left!=null) return left;
        return right; 
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}