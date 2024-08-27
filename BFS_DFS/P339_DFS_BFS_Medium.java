/*
    339. Nested List Weight Sum

    You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
    The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
    Return the sum of each integer in nestedList multiplied by its depth.

    Example 1:
        Input: nestedList = [[1,1],2,[1,1]]
        Output: 10
        Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.

    Example 2:
        Input: nestedList = [1,[4,[6]]]
        Output: 27
        Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.

    Example 3:
        Input: nestedList = [0]
        Output: 0
    
    Constraints:
        1 <= nestedList.length <= 50
        The values of the integers in the nested list is in the range [-100, 100].
        The maximum depth of any integer is less than or equal to 50.
 */

package BFS_DFS;

import java.util.LinkedList;
import java.util.List;

public class P339_DFS_BFS_Medium {
    /*
     * Solution: DFS
     * time complexity: O(n)
     * space complexity: O(n)
     *      In terms of space, at most O(D) recursive calls are placed on the stack, where D is the maximum level of nesting in the input. 
     *      For example, D=2 for the input [[1,1],2,[1,1]], and D=3 for the input [1,[4,[6]]]
     *      In worst case, D=N, (e.g. the list [[[[[[]]]]]]) so the worst-case space complexity is O(n)
     */
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for(NestedInteger list: nestedList) {
            if(list.isInteger()) {
                sum+= depth*list.getInteger();
            } else {
                sum+= dfs(list.getList(), depth+1);
            }
        }
        return sum;
    }

    /*
     * Solution: BFS
     * time complexity: O(n)
     * space complexity: O(n)
     */
    // public int depthSum(List<NestedInteger> nestedList) {
    //     Queue<NestedInteger> queue = new LinkedList<>();
    //     queue.addAll(nestedList);
    //     int depth=1;
    //     int sum = 0;

    //     while(!queue.isEmpty()) {
    //         int size = queue.size();
    //         for(int i=0; i<size; i++) {
    //             NestedInteger list = queue.remove();
    //             if(list.isInteger()) {
    //                 sum+= depth * list.getInteger();
    //             } else {
    //                 queue.addAll(list.getList());
    //             }
    //         }
    //         depth++;
    //     }
    //     return sum;
    // }
}
