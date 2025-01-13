/*
    106. Construct binary tree from inorder and postorder traversal
    Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

    Example 1:
        Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
        Output: [3,9,20,null,null,15,7]

    Example 2:
        Input: inorder = [-1], postorder = [-1]
        Output: [-1]
    
    Constraints:
        1 <= inorder.length <= 3000
        postorder.length == inorder.length
        -3000 <= inorder[i], postorder[i] <= 3000
        inorder and postorder consist of unique values.
        Each value of postorder also appears in inorder.
        inorder is guaranteed to be the inorder traversal of the tree.
        postorder is guaranteed to be the postorder traversal of the tree.
 */
package BFS_DFS_BinaryTree;

import java.util.HashMap;
import java.util.Map;

public class P106_Medium {
    /*
     * Solution: recursion with hashmap
     * time complexity: O(n)
     * space complexity: O(n)
     */
    private int postIdx;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length-1;
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return setTree(postorder, 0, postorder.length-1, inorderMap, inorder);
    }

    private TreeNode setTree(int[] postorder, int iStart, int iEnd, Map<Integer, Integer> inorderMap, int[] inorder) {
        if(iStart > iEnd) return null;
        int rootVal = postorder[postIdx--];
        TreeNode root = new TreeNode(rootVal);
        int inorderPos = inorderMap.get(rootVal);

        // Following the postorder logic, proceed recursively first to construct the right subtree 
        // and then to construct the left subtree
        root.right = setTree(postorder, inorderPos+1, iEnd, inorderMap, inorder);
        root.left = setTree(postorder, iStart, inorderPos-1, inorderMap, inorder);
        return root;
    }

    /*
     * Solution: recursion without hashmap
     * time complexity: O(n)
     * space complexity: O(n)
     */
    private int pIdx;
    private int iIdx;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        pIdx = postorder.length-1;
        iIdx = inorder.length-1;
        return setTree(inorder, postorder, Integer.MAX_VALUE);
    }
    private TreeNode setTree(int[] inorder, int[] postorder, int stop) {
        if(pIdx<0) return null;
        if(inorder[iIdx] == stop) {
            iIdx--;
            return null;
        }
        TreeNode root = new TreeNode(postorder[pIdx--]);
        root.right = setTree(inorder, postorder, root.val);
        root.left = setTree(inorder, postorder, stop);
        return root;
    }
}
