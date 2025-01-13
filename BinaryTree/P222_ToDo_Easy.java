/*
    222. Count complete tree nodes
    Given the root of a complete binary tree, return the number of the nodes in the tree.
    According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
    Design an algorithm that runs in less than O(n) time complexity.

    Example 1:
        Input: root = [1,2,3,4,5,6]
        Output: 6

    Example 2:
        Input: root = []
        Output: 0

    Example 3:
        Input: root = [1]
        Output: 1

    Constraints:
        The number of nodes in the tree is in the range [0, 5 * 10^4].
        0 <= Node.val <= 5 * 10^4
        The tree is guaranteed to be complete.
 */
package BFS_DFS_BinaryTree;

import java.util.ArrayList;

public class P222_ToDo_Easy {
    /*
     * Solution: Linear time
     * time complexity: O(n)
     * space complexity: O(logN) for recursion stack
     */
    public int countNodes(TreeNode root) {
        if(root==null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /*
     * Solution: with arrayList
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int countNodes(TreeNode root) {
        ArrayList<Integer> nodes = new ArrayList<>();
        if(root == null) return 0;
        travelNodes(root, nodes);
        return nodes.size();
    }

    void travelNodes(TreeNode node, ArrayList<Integer> nodes) {
        if(node == null) return ;
        nodes.add(node.val);
        travelNodes(node.left, nodes);
        travelNodes(node.right, nodes);
    }

    /*
     * Solution: recursive
     * time complexity: O(n)
     * space complexity: O(1)
     */
    int count = 0;
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return helper(root);
    }

    private int helper(TreeNode node) {
        if(node == null) return count;
        if(node.left!=null && node.right!=null) {
            count ++;
        } else if(node.left == null && node.right == null) {
            count ++;
        } else if(node.left!=null && node.right == null) {
            count ++;
        }
        helper(node.left);
        helper(node.right);
        return count;
    }

    /*
     * Solution: recursive
     * 
     * Approach: 
     *      1 << h 는 h 정수만큼 왼쪽으로 shift 하라는 말로, 2^h == Math.pow(2, h)와 같다.
     *      The height of a tree can be found by just going left side. 
     *      Check whether the height of the right subtree is just one less than that of the whole tree, meaning left and right subtree have the same height
     *          1. If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of height h-1. 
     *              So, we take the 2^(h-1) nodes of the left subtree + 1 root node + recursively the number of nodes in the right subtree
     *          2. If no, then the last node on the last tree row is in the left subtree and the right subtree is a full tree of height h-2.
     *              So, we take the 2^((h-1)-1) nodes of the right subtree + 1 root node + recursively the number of nodes in the left subtree.
     *          
     * time complexity: O(logN^2)
     * space complexity: O(1)
     */
    public int countNodes(TreeNode root) {
        int h = height(root);
        return h<0? 
                0: 
                height(root.right)==h-1 ? (1<<h) + countNodes(root.right) : (1 << h-1) + countNodes(root.left);
    } 

    int height(TreeNode node) {
        return node == null ? -1 : 1 + height(node.left);
    }

    /*
     * Solution: recursive
     * time complexity: O(logN)
     * space complexity: O(1)
     */
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int left = 0, right = 0;
        TreeNode dummy = root;
        while(dummy.left !=null) {
            dummy = dummy.left;
            left++;
        }

        dummy=root;
        while(dummy.right !=null) {
            dummy = dummy.right;
            right++;
        }

        if(left == right) {
            return 2* ((int)Math.pow(2, left)-1) +1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
}
