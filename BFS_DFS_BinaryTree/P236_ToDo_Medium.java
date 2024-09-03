/*
    236. Lowest Common Ancestor of a Binary Tree
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
        The number of nodes in the tree is in the range [2, 105].
        -10^9 <= Node.val <= 10^9
        All Node.val are unique.
        p != q
        p and q will exist in the tree.
 */
package BFS_DFS_BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P236_ToDo_Medium {
    /*
     * Solution: DFS 1
     * time complexity: O(n)
     * space complexity: O(n), because the maximum amount of space utilized by the recursion stack would be N since the height of a skewed binary tree could be N
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left!=null && right!=null ){
            return root;
        } else if (left==null) {
            return right;
        } else {
            return left;
        }
    }

    /*
     * Solution: DFS with boolean 
     */
    private TreeNode answer2;
    public TreeNode lowestCommonAncestor_bool(TreeNode root, TreeNode p, TreeNode q) {
        answer2=null;
        dfs(root, p, q);
        return answer2;
    }
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return false;
        int left_status = dfs(root.left, p, q) ? 1: 0;
        int right_status = dfs(root.right, p, q) ? 1: 0;
        int mid = (root.val == p.val || root.val == q.val) ? 1: 0;

        if(mid+left_status+right_status>=2) {
            answer2 = root;
        }

        return (mid+left_status+right_status>0);
    }

    /*
     * Solution: Iterative with parents pointer 
     */
    public TreeNode lowestCommonAncestor_iterative(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        
        // iterate until both parent nodes of p and q are found
        while(!parent.containsKey(p) || !parent.containsKey(q)) {
            root = stack.pop();
            if(root.left!=null) {
                parent.put(root.left, root);
                stack.push(root.left);
            }
            if(root.right!=null) {
                parent.put(root.right, root);
                stack.push(root.left);
            }
        }

        Set<TreeNode> ancestors = new HashSet<>();
        // check all ancestors of node p
        while(p!=null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        // the first ancestor of q which appears in p's ancestor set is their LCA
        while(!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
}
