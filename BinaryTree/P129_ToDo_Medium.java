/*
    129. Sum root to leaf numbers
    You are given the root of a binary tree containing digits from 0 to 9 only.
    Each root-to-leaf path in the tree represents a number.
        For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
    Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
    A leaf node is a node with no children.

    Example 1:
        Input: root = [1,2,3]
        Output: 25
        Explanation:
        The root-to-leaf path 1->2 represents the number 12.
        The root-to-leaf path 1->3 represents the number 13.
        Therefore, sum = 12 + 13 = 25.

    Example 2:
        Input: root = [4,9,0,5,1]
        Output: 1026
        Explanation:
        The root-to-leaf path 4->9->5 represents the number 495.
        The root-to-leaf path 4->9->1 represents the number 491.
        The root-to-leaf path 4->0 represents the number 40.
        Therefore, sum = 495 + 491 + 40 = 1026.
    
    Constraints:
        The number of nodes in the tree is in the range [1, 1000].
        0 <= Node.val <= 9
        The depth of the tree will not exceed 10.
 */
package LinkedList;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * Approach:
 *      Root-to-left traversal is so-called DFS preorder traversal. To implement it, one has to follow a straightforward strategy Root -> Left -> Right.
 *      Since one has to visit all nodes, the best possible time complexity here is linear. Hence all interest here is to improve the space complexity. 
 *      There are 3 ways to implement preorder traversal : iterative, recursive, and morris
 *          Iterative: the best time, do the job in one pass, need up to O(H) space to keep the stack
 *          recursive: the simplest one to write, do the job in one pass, need up to O(H) space to keep the stack
 *          Morris: constant space, a two-pass approach
 *              The idea of Morris algorithm is to set the temporary link between the node and its predecessor: predecessor.right = root
 *              So, one starts from the node, computes its predecessor, and verifies if the link is present.
 *                  there is no link? set it and go to the left subtree
 *                  there is a link? break it and go to the right subtree
 *                  If there is no left child, then go straight to the right subtree.
 */
public class P129_ToDo_Medium {
    /*
     * Solution: recursion - DFS v1
     * 
     * Pre-order traversal: Root -> Left -> Right
     * 
     * time complexity: O(n)
     * space complexity: up to O(h) to keep the recursion stack, where h is a tree height
     */
    public int sumNumbers(TreeNode root) {
        return calculate(root, 0);
    }

    int calculate(TreeNode node, int sum) {
        if(node==null) return 0;
        sum = sum*10 + node.val;
        if(node.left == null && node.right == null) return sum;
        return calculate(node.left, sum)+calculate(node.right, sum);
    }

    /*
     * Solution: recursion - DFS v2
     * time complexity: O(n)
     * space complexity: O(H)
     */
    int totalSum = 0;
    public int sumNumbers(TreeNode root) {
        preorder(root, 0);
        return totalSum;
    }
    void preorder(TreeNode node, int curr) {
        if(node!=null) {
            curr = curr*10 + node.val;
            if(node.left==null && node.right == null) {
                totalSum += curr;
            }
            preorder(node.left, curr);
            preorder(node.right, curr);
        }
    }

    int sum = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root);
        return sum;
    }

    void dfs(TreeNode node) {
        if(node.left == null && node.right == null) {
            sum+= node.val;
            return;
        }
        if(node.left!=null) {
            node.left.val += node.val*10;
            dfs(node.left);
        }
        if(node.right!=null) {
            node.right.val += node.val*10;
            dfs(node.right);
        }
    }

    /*
     * Solution: iterative with stack
     * time complexity: O(n)
     * space complexity: O(h)
     */
    public int sumNumbers(TreeNode root) {
        int total = 0, curr = 0;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
        stack.push(new Pair(root, 0));
        while(!stack.isEmpty()) {
            Pair<TreeNode, Integer> p = stack.pop();
            TreeNode node = p.getKey();
            curr = p.getValue();

            if(node!=null) {
                curr = curr*10 + node.val;
                if(node.left==null && node.right==null) {
                    total += curr;
                } else {
                    stack.push(new Pair(node.left, curr));
                    stack.push(new Pair(node.right, curr));
                }
            }
        }
        return total;
    }

    public int sumNumbers(TreeNode root) {
        int total = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node.left!=null) {
                node.left.val += node.val*10;
                stack.push(node.left);
            }
            if(node.right!=null) {
                node.right.val += node.val*10;
                stack.push(node.right);
            }
            if(node.left==null && node.right==null) {
                total+= node.val;
            }
        }
        return total;
    }
}
