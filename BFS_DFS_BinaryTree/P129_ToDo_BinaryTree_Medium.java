/*
    129. Sum Root to Leaf Numbers
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

package BFS_DFS_BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;
import javafx.util.Pair; 

import javax.swing.tree.TreeNode;

public class P129_ToDo_BinaryTree_Medium {
    /*
     * Solution : DFS (Recursive preorder traversal)
     * Pre-order traversal: Root -> Left -> Right
     * 
     * time complexity: O(N) since one has to visit each node
     * space complexity: Up to O(H) to keep the recursion stack, where H is a tree height
     */
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        dfs_preorder(root, 0);
        return sum;
    }
    private void dfs_preorder(TreeNode root, int cur_sum) {
        if(root!=null) {
            cur_sum = cur_sum * 10 + root.val;
            if(root.left == null && root.right == null) {
                sum += cur_sum;
            }
            if(root.left!=null)
                dfs_preorder(root.left, cur_sum);
            if(root.right!=null)
                dfs_preorder(root.right, cur_sum);
        }
    }

    /*
     * Solution: DFS
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    private int dfs(TreeNote root, int num) {
        if( root == null) return 0;
        num = num*10 + root.val;
        if(root.left == null && root.right == null) {
            return num;
        }
        return dfs(root.left, num) + dfs(root.right, num);
    }

    /*
     * Soution: Iterative preorder traversal with Stack
     * To implement stack, java recommends ArrayDeque instead of Stack.
     * 
     * time complexity: O(N) since one has to visit each node
     * space complexity: Up to O(H) to keep the recursion stack, where H is a tree height
     */
    // public int sumNumbers(TreeNode root) {
    //     int sum = 0;
    //     int curr_sum = 0;
    //     Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque<>();
    //     //Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
    //     stack.push(new Pair<>(root, 0));

    //     while(!stack.isEmpty()) {
    //         Pair<TreeNode, Integer> node = stack.pop();
    //         root = node.getKey();
    //         curr_sum = node.getValue();

    //         if(root!=null) {
    //             curr_sum *=10 + root.val;
    //             if(root.left==null && root.right==null) {
    //                 sum+= curr_sum;
    //             } else {
    //                 stack.push(new Pair<>(root.left, cur_sum));
    //                 stack.push(new Pair<>(root.right, cur_sum));
    //             }
    //         }
    //     }
    //     return sum;
    // }
    
    /*
     * Solution : Morris Preorder traversal
     * The idea of Morris algorithm is to set the temporary link between the node and its predecessor: predecessor.right = root. 
     * So, one starts from the node, computes its predecessor, and verifies if the link is present
     *      there is no link? Set it and go to the left subtree
     *      there is a link? break it and go to the right subtree.
     * There is one small issue to deal with what if there is no left child, i.e. there is no left subtree? Then, go straight to the right subtree.
     * 
     * time complexity: O(n)
     * space complexity: O(1)
     */
    // public int sumNumbers(TreeNode root) {
    //     int total_sum = 0;
    //     int curr_sum = 0;
    //     int steps;
    //     TreeNode predecessor;

    //     while(root!=null) {
    //         // if there is a left child, then compute the predecessor
    //         // if there is no link predecessor.right = root --> then set it
    //         // if there is a link predecessor.right = root --> break it
    //         if(root.left!=null) {

    //             // predecessor node is one step to the left, and then to the right till you can
    //             predecessor = root.left;
    //             steps=1;
    //             while(predecessor.right!=null && predecessor.right!=null) {
    //                 predecessor = predecessor.right;
    //                 steps++;
    //             }

    //             // set link predecessor.right = root, and go to explore the left subtree
    //             if(predecessor.right == null) {
    //                 curr_sum = curr_sum * 10 + root.val;
    //                 predecessor.right = root;
    //                 root = root.left;
    //             } else {
    //                 // break the link predecessor.right= root. Once the link is broken, it's time to change subtree and go to the right

    //                 // if you're on the leaf, update the sum
    //                 if(predecessor.left == null) {
    //                     total_sum+= curr_sum;
    //                 }

    //                 for(int i=0; i<steps; i++) {
    //                     curr_sum/=10;
    //                 }
    //                 predecessor.right=null;
    //                 root=root.right;
    //             }
    //         } else {
    //             // if there is no left child, then just go to right

    //             curr_sum = curr_sum*10 + val;
    //             if(root.right==null) {
    //                 total_sum+= curr_sum;
    //             }
    //             root = root.right;
    //         }
    //     }
    //     return total_sum;
    // }
}
