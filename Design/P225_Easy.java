/*
    225. Implement stack using queues
    Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
    Implement the MyStack class:
        void push(int x) Pushes element x to the top of the stack.
        int pop() Removes the element on the top of the stack and returns it.
        int top() Returns the element on the top of the stack.
        boolean empty() Returns true if the stack is empty, false otherwise.
    Notes:
        You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
        Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
    
    Example 1:
    Input
    ["MyStack", "push", "push", "top", "pop", "empty"]
    [[], [1], [2], [], [], []]
    Output
    [null, null, null, 2, 2, false]
    Explanation
    MyStack myStack = new MyStack();
    myStack.push(1);
    myStack.push(2);
    myStack.top(); // return 2
    myStack.pop(); // return 2
    myStack.empty(); // return False
 
Constraints:
    1 <= x <= 9
    At most 100 calls will be made to push, pop, top, and empty.
    All the calls to pop and top are valid.
 
Follow-up: Can you implement the stack using only one queue?
 */
package Design;

import java.util.ArrayDeque;
import java.util.Queue;

public class P225_Easy {
    /*
     * Solution: with one Queue
     */
    private Queue<Integer> queue;
    public MyStack() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        queue.add(x);
        int n = queue.size();
        for(int i=0;i<n-1; i++) {
            queue.add(queue.remove());
        }
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    /*
     * Solution: with two queues
     */
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;
    public MyStack() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    public void push(int x) {
        queue2.add(x);
        while(!queue1.isEmpty()) {
            queue2.add(queue1.remove());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    public int pop() {
        return queue1.remove();
    }

    public int top() {
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
