/*
    605. can place flowers
    You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
    Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.

    Example 1:
        Input: flowerbed = [1,0,0,0,1], n = 1
        Output: true

    Example 2:
        Input: flowerbed = [1,0,0,0,1], n = 2
        Output: false
    
    Constraints:
        1 <= flowerbed.length <= 2 * 10^4
        flowerbed[i] is 0 or 1.
        There are no two adjacent flowers in flowerbed.
        0 <= n <= flowerbed.length
 */
package Array_String;

public class P605_Easy {

    /*
     * Solution: one pass
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for(int i=0; i<flowerbed.length; i++) {
            if(flowerbed[i]==1) continue;
            boolean emptyLeft = i==0 || flowerbed[i-1]==0;
            boolean emptyRight = i==flowerbed.length-1 || flowerbed[i+1] == 0;

            if(emptyLeft && emptyRight) {
                count++;
                flowerbed[i] = 1;
                if(count >= n) {
                    return true;
                }
            }
        }
        return count>=n;
    }
    
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int i=0;
        while(i<len && n>0) {
            if(flowerbed[i]==1) i+=2;
            else if(i==len-1 || flowerbed[i+1] == 0)  {
                n--;
                i+=2;
            }
            else i+=3;
        }
        return n<=0;
    }
}
