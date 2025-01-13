/*
    103. Binary tree zigzag level order traversal
    Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
    Example 1:
        Input: root = [3,9,20,null,null,15,7]
        Output: [[3],[20,9],[15,7]]

    Example 2:
        Input: root = [1]
        Output: [[1]]

    Example 3:
        Input: root = []
        Output: []
    
    Constraints:
        The number of nodes in the tree is in the range [0, 2000].
        -100 <= Node.val <= 100
 */
package BFS_DFS_BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P103_Medium {
    /*
     * Solution : iterative with double stacks 
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if(root==null) return answer;
        Deque<TreeNode> s1 = new ArrayDeque<>();
        s1.push(root);
        Deque<TreeNode> s2 = new ArrayDeque<>();
        while(!s1.isEmpty() || !s2.isEmpty()) {
            TreeNode node;
            List<Integer> list = new ArrayList<>();
            while(!s1.isEmpty()) {
                node = s1.pop();
                list.add(node.val);
                if(node.left!=null) s2.push(node.left);
                if(node.right!=null) s2.push(node.right);
            }
            answer.add(list);

            list = new ArrayList<>();
            while(!s2.isEmpty()) {
                node = s2.pop();
                list.add(node.val);
                if(node.right!=null) s1.push(node.right);
                if(node.left!=null) s1.push(node.left);
            }
            if(!list.isEmpty()) answer.add(list);
        }
        return answer;
    }

    /*
     * Solution : recursive -DFS
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if(root!=null) helper(root, 0, answer);
        return answer;
    }
    void helper(TreeNode root, int level, List<List<Integer>> answer) {
        if(root==null) return;
        if(answer.size() == level) {
            answer.add(new ArrayList<>());
        }
        
        if(level%2==0) {
            answer.get(level).add(root.val);
        } else {
            answer.get(level).add(0, root.val);
        }

        if(root.left!=null) helper(root.left, level+1, answer);
        if(root.right!=null) helper(root.right, level+1, answer);
    }
}
