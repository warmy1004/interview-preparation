/*
    105. Construct binary tree from preorder and inorder traversal
    Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

    Example 1:
        Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        Output: [3,9,20,null,null,15,7]

    Example 2:
        Input: preorder = [-1], inorder = [-1]
        Output: [-1]
    
    Constraints:
        1 <= preorder.length <= 3000
        inorder.length == preorder.length
        -3000 <= preorder[i], inorder[i] <= 3000
        preorder and inorder consist of unique values.
        Each value of inorder also appears in preorder.
        preorder is guaranteed to be the preorder traversal of the tree.
        inorder is guaranteed to be the inorder traversal of the tree.
 */
package BFS_DFS_BinaryTree;

import java.util.HashMap;
import java.util.Map;

public class P105_Medium {
    /*
     * Solution: Recursion with hashMap
     * time complexity: O(n)
     * space complexity: O(n)
     */
    private int preorderIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return setTree(preorder, 0, preorder.length-1, inorderMap);
    }

    private TreeNode setTree(int[] preorder, int start, int end, Map<Integer, Integer> inorderMap) {
        if(start>end) return null;
        int val = preorder[preorderIndex++];
        int rootPos = inorderMap.get(val);
        TreeNode root = new TreeNode(val);
        root.left = setTree(preorder, start, rootPos-1, inorderMap);
        root.right = setTree(preorder, rootPos+1, end, inorderMap);
        return root;
    }

    /*
     * Solution: recursion without hashmap - v1
     */
    private int preorderLoc = 0;
    private int inorderLoc = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if(inorderLoc < inorder.length && inorder[inorderLoc] != stop) {
            TreeNode root = new TreeNode(preorder[preorderLoc++]);
            root.left = build(preorder, inorder, root.val);
            inorderLoc++;
            root.right = build(preorder, inorder, stop);
            return root;
        }
        return null;
    }

    /*
     * Solution: recursion without hashmap - v2
     */
    private int pIdx = 0;
    private int iIdx = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if(pIdx >= preorder.length) return null;
        if(inorder[iIdx] == stop) {
            iIdx++;
            return null;
        }
        TreeNode node = new TreeNode(preorder[pIdx++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);
        return node;
    }
}
