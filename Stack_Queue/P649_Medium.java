/*
    649. Dota2 Senate
    In the world of Dota2, there are two parties: the Radiant and the Dire.
    The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:
        Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
        Announce the victory: If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.
    Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.
    The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.
    Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".

    Example 1:
        Input: senate = "RD"
        Output: "Radiant"
        Explanation: 
        The first senator comes from Radiant and he can just ban the next senator's right in round 1. 
        And the second senator can't exercise any rights anymore since his right has been banned. 
        And in round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.

    Example 2:
        Input: senate = "RDD"
        Output: "Dire"
        Explanation: 
        The first senator comes from Radiant and he can just ban the next senator's right in round 1. 
        And the second senator can't exercise any rights anymore since his right has been banned. 
        And the third senator comes from Dire and he can ban the first senator's right in round 1. 
        And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.
    
    Constraints:
        n == senate.length
        1 <= n <= 10^4
        senate[i] is either 'R' or 'D'.
 */

package Stack_Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class P649_Medium {
    /*
     * Solution: two queues
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public String predictPartyVictory(String senate) {
        Deque<Integer> dQueue = new ArrayDeque<>();
        Deque<Integer> rQueue = new ArrayDeque<>();
        for(int i=0; i<senate.length(); i++) {
            char ch = senate.charAt(i);
            if(ch == 'R') {
                rQueue.add(i);
            } else {
                dQueue.add(i);
            }
        }

        while(!dQueue.isEmpty() && !rQueue.isEmpty()) {
            int dPos = dQueue.poll();
            int rPos = rQueue.poll();
            if(dPos < rPos) {
                dQueue.add(dPos+senate.length());
            } else {
                rQueue.add(rPos+senate.length());
            }
        }

        return dQueue.isEmpty()? "Radiant" : "Dire";
    }

    /*
     * Solution: single queue
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public String predictPartyVictory(String senate) {
        int rCnt = 0, dCnt = 0;
        int rBan = 0, dBan = 0;
        Queue<Character> queue = new LinkedList<>();
        for (char ch : senate.toCharArray()) {
            queue.add(ch);
            if (ch == 'R')
                rCnt++;
            else
                dCnt++;
        }

        while (rCnt > 0 && dCnt > 0) {
            char curr = queue.poll();
            if (curr == 'D') {
                // dBan>0 means, one of previous R already banned the current D, so it can't ban one of R. So, just decrease dBan count and decrease dCnt too. This is for 'Skipped'
                if (dBan > 0) {
                    dBan--;
                    dCnt--;
                } else {
                    rBan++;
                    queue.add(curr);
                }
            } else {
                if (rBan > 0) {
                    rBan--;
                    rCnt--;
                } else {
                    dBan++;
                    queue.add(curr);
                }
            }
        }
        return rCnt > 0 ? "Radiant" : "Dire";
    }
}

/*
 * JavaScript
 */
var predictPartyVictory = function(senate) {
    let dQueue = [];
    let rQueue = [];
    for(let i=0; i<senate.length; i++) {
        if(senate[i] == 'D') dQueue.push(i);
        else rQueue.push(i); 
    }

    while(dQueue.length>0 && rQueue.length>0) {
        let dNext = dQueue.shift();
        let rNext = rQueue.shift();

        if(dNext<rNext) {
            dQueue.push(dNext+senate.length);
        } else {
            rQueue.push(rNext+senate.length);
        }
    }
    return dQueue.length > 0? "Dire": "Radiant";
};

/*
 * Python
 */
class Solution:
    def predictPartyVictory(self, senate: str) -> str:
        n = len(senate)

        rQueue = deque()
        dQueue = deque()

        for i, s in enumerate(senate):
            if s == 'R':
                rQueue.append(i)
            else :
                dQueue.append(i)


        while rQueue and dQueue:
            r1 = rQueue.popleft()
            d1 = dQueue.popleft()

            if r1<d1:
                rQueue.append(r1+n)
            else :
                dQueue.append(d1+n)
        
        return "Radiant" if rQueue else "Dire"