package BFS_DFS_BinaryTree;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class P144_P94_P145_BinaryTree_order_DFS_Easy {
    /*
     * PRE-order traversal
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder_answer = new ArrayList<>();
        dfs_preorder(root, preorder_answer);
        return preorder_answer;
    }
    private void dfs_preorder(TreeNode root, List<Integer> preorder_answer) {
        if(root!=null) {
            preorder_answer.add(root.val);
            dfs_preorder(root.left, preorder_answer);
            dfs_preorder(root.right, preorder_answer);
        }
    }

    /*
     * IN-order traversal
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder_answer = new ArrayList<>();
        dfs_inorder(root, inorder_answer);
        return inorder_answer;
    }
    private void dfs_inorder(TreeNode root, List<Integer> inorder_answer) {
        if(root!=null) {
            dfs_inorder(root.left, inorder_answer);
            inorder_answer.add(root.val);
            dfs_inorder(root.right, inorder_answer);
        }
    }

    /*
     * POST-order traversal
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder_answer = new ArrayList<>();
        dfs_postorder(root, postorder_answer);
        return postorder_answer;
    }
    private void dfs_postorder(TreeNode root, List<Integer> postorder_answer) {
        if(root!=null) {
            dfs_postorder(root.left, postorder_answer);
            dfs_postorder(root.right, postorder_answer);
            postorder_answer.add(root.val);
        }
    }
}
