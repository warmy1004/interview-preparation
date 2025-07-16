/*
    735. Asteroid Collision
    We are given an array asteroids of integers representing asteroids in a row. The indices of the asteriod in the array represent their relative position in space.
    For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
    Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

    Example 1:
        Input: asteroids = [5,10,-5]
        Output: [5,10]
        Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.

    Example 2:
        Input: asteroids = [8,-8]
        Output: []
        Explanation: The 8 and -8 collide exploding each other.

    Example 3:
        Input: asteroids = [10,2,-5]
        Output: [10]
        Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
    
    Constraints:
        2 <= asteroids.length <= 10^4
        -1000 <= asteroids[i] <= 1000
        asteroids[i] != 0
 */
package Stack;

import java.util.Stack;
import javax.naming.spi.DirStateFactory;

public class P735_Medium {
    /*
     * Solution: Using Stack
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int star : asteroids) {
            boolean flag = true;
            while(!stack.isEmpty() && stack.peek()>0 && star<0) {
                int diff = stack.peek() + star;
                if(diff < 0) {
                    stack.pop();
                    continue;
                }
                if(diff == 0) {
                    stack.pop();
                }
                flag = false;
                break;
            }
            if(flag) {
                stack.push(star);
            }
        }

        int[] answer = new int[stack.size()];
        int i=answer.length-1;
        while(!stack.isEmpty()) {
            answer[i--] = stack.pop();
        }
        return answer;
    }

    /*
     * Solution: no stack
     * time complexity:
     * space complexity: 
     */
    public int[] asteroidCollision(int[] asteroids) {
        int j = 0;
        for(int i=0; i<asteroids.length; i++) {
            int star = asteroids[i];
            while(j>0 && asteroids[j-1]>0 && star<0 && asteroids[j-1]+star < 0) {
                j--;
            }

            if(j==0 || star>0 || asteroids[j-1]<0) {
                asteroids[j++] = star;
            } else if(asteroids[j-1] +star == 0) {
                j--;
            }
        }
        return Arrays.copyOf(asteroids, j);
    }
}

/*
 * JavaScript
 */
var asteroidCollision = function(asteroids) {
    const answer = [];
    for(let i=0; i<asteroids.length; i++) {
        let star = asteroids[i];
        let prev = answer[answer.length-1];
        if(answer.length == 0 || prev < 0 || star > 0) {
            answer.push(star);
        } else if ( prev + star == 0) {
            answer.pop();
        } else if ( prev + star < 0) {
            answer.pop();
            i--;
        }
    }
    return answer;
};
