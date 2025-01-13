/*
    199. Binary tree right side view
    Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

    Example 1:
    Input: root = [1,2,3,null,5,null,4]
    Output: [1,3,4]

    Example 2:
    Input: root = [1,2,3,4,null,null,null,5]
    Output: [1,3,4,5]

    Example 3:
    Input: root = [1,null,3]
    Output: [1,3]

    Example 4:
    Input: root = []
    Output: []

    Constraints:
        The number of nodes in the tree is in the range [0, 100].
        -100 <= Node.val <= 100
 */
package BFS_DFS_BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class P199_Medium {
    /*
     * Solution: iterative
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        if(root == null) return answer;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.add(root);

        while(!stack.isEmpty()) {
            int size = stack.size();
            for(int i=0; i<size; i++) {
                TreeNode node = stack.pop();
                if(i==size-1) {
                    answer.add(node.val);
                }
                if(node.left!=null) stack.add(node.left);
                if(node.right!=null) stack.add(node.right);
            }
        }
        return answer;
    }

    /*
     * Solution: recursive
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        if(root == null) return answer;
        dfs(root, 0, answer);
        return answer;
    }
    void dfs(TreeNode node, int level, List<Integer> answer) {
        if(level == answer.size()) answer.add(node.val);
        if(node.right!=null) dfs(node.right, level+1, answer);
        if(node.left!=null) dfs(node.left, level+1, answer);
    }
    
}
