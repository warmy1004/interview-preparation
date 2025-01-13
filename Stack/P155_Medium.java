/*
    155. Min stack
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    Implement the MinStack class:
        MinStack() initializes the stack object.
        void push(int val) pushes the element val onto the stack.
        void pop() removes the element on the top of the stack.
        int top() gets the top element of the stack.
        int getMin() retrieves the minimum element in the stack.
    You must implement a solution with O(1) time complexity for each function.

    Example 1:
    Input
    ["MinStack","push","push","push","getMin","pop","top","getMin"]
    [[],[-2],[0],[-3],[],[],[],[]]

    Output
    [null,null,null,null,-3,null,0,-2]

    Explanation
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); // return -3
        minStack.pop();
        minStack.top();    // return 0
        minStack.getMin(); // return -2
    
    Constraints:
        -2^31 <= val <= 2^31 - 1
        Methods pop, top and getMin operations will always be called on non-empty stacks.
        At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 */
package Stack;

import java.util.PriorityQueue;
import java.util.Stack;

public class P155_Medium {
    /*
     * Solution: stack and minHeap
     * time complexity: O(1)
     * space complexity: O(n)
     */
    class MinStack {

        Stack<Integer> stack;
        PriorityQueue<Integer> heap;

        public MinStack() {
            stack = new Stack<>();
            heap = new PriorityQueue<>();
        }
        
        public void push(int val) {
            stack.push(val);
            heap.add(val);
        }
        
        public void pop() {
            heap.remove(stack.peek());
            stack.pop();
        }
        
        public int top() {
            return stack.peek();
        }
        
        public int getMin() {
            return heap.peek();
        }
    }
    
    /*
     * Solution: using two stacks
     * time complexity: O(1)
     * space complexity: O(n)
     */
    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            // check "smaller and equal" in order to allow duplication
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            // type is Integer, so use equals instead
            if(minStack.peek().equals(stack.peek())) {
                minStack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /*
     * Solution: using one stack
     * time complexity: O(1)
     * space complexity: O(n)
     */
    class MinStack {
        Stack<Integer> stack;
        int min;

        public MinStack() {
            stack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int val) {
            if(val<=min) {
                stack.push(min);
                min = val;        
            }
            stack.push(val);
        }

        public void pop() {
            if(min == stack.pop()) {
                min = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }
}
