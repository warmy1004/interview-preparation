package BFS_DFS_BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class P144_P94_P145_BinaryTree_order_Iterative_Easy {
    /*
     * Preorder Iterative Traversal
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(root!=null || !stack.isEmpty()) {
            if(root!=null) {
                answer.add(root.val);
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }
        return answer;
    }

     /*
     * Inorder Iterative Traversal
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
       Deque<TreeNode> stack = new ArrayDeque<>();
       while(root!=null || !stack.isEmpty()) {
        if(root!=null) {
            stack.push(root);
            root = root.left;
        } else {
            root = stack.pop();
            answer.add(root.val);
            root = root.right;
        }
       } 
       return answer;
    }

     /*
     * postorder Iterative Traversal 1
     */
    public List<Integer> postorderTraversal_reverse(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(root!=null || !stack.isEmpty()) {
            if(root!=null) {
                answer.add(root.val);
                stack.push(root);
                root = root.right;
            } else {
                root = stack.pop();
                root = root.left;
            }
        }
        Collections.reverse(answer);
        return answer;
    }

         /*
     * postorder Iterative Traversal 2
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(root!=null || !stack.isEmpty()) {
            if(root!=null) {
                answer.addFirst(root.val);
                stack.push(root);
                root = root.right;
            } else {
                root = stack.pop();
                root = root.left;
            }
        }
        return answer;
    }
}
