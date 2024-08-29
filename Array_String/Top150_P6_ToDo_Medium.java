/*
    6. Zigzag conversion

    The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
        P   A   H   N
        A P L S I I G
        Y   I   R
    And then read line by line: "PAHNAPLSIIGYIR"
    Write the code that will take a string and make this conversion given a number of rows:
    string convert(string s, int numRows);

    Example 1:
        Input: s = "PAYPALISHIRING", numRows = 3
        Output: "PAHNAPLSIIGYIR"

    Example 2:
        Input: s = "PAYPALISHIRING", numRows = 4
        Output: "PINALSIGYAHRPI"
        Explanation:
                P     I    N
                A   L S  I G
                Y A   H R
                P     I

    Example 3:
        Input: s = "A", numRows = 1
        Output: "A"

    Constraints:
        1 <= s.length <= 1000
        s consists of English letters (lower-case and upper-case), ',' and '.'.
        1 <= numRows <= 1000
 */

package Array_String;

import java.util.Arrays;

public class Top150_P6_ToDo_Medium {
    /*
     * Solution 1: Simulate Zigzag movement
     *      the first task is to determine the size of the matrix as this will allow us to create 2d array where we can simulate writing the input string in a zigzag pattern
     *      For finding the number of columns:
     *          in each section, we will have 2 * numRows -2 characters, (numRow characters in one column and numRow -2 in the diagonal). 
     *          Thus, for a string of n characters, we will require at most ceil(n/(2*numRows -2)) sections, and as each section will have numRows -1 columns. 
     *          We can say we need ceil(n/(2*numRows-2)) * (numRows-1) columns.
     * 
     * time complexity : O(numRows * n)
     * space complexity: O(numRows * n)
     */
    // public String convert(String s, int numRows) {
    //     if(numRows == 1) return s;
    //     int n = s.length();

    //     // to find the total number of columns
    //     int sectionNums =(int) Math.ceil(n / (2*numRows-2.0)); 
    //     // OR, int sectionNums = n/(2*numRows-2) +1; 
    //     int numCols = sectionNums * (numRows-1);
    //     char[][] matrix = new char[numRows][numCols];
    //     for(char[] arr : matrix) {
    //         Arrays.fill(arr, ' ');
    //     }
    //     int row = 0, col = 0;
    //     int index = 0;
    //     while( index<n) {
    //         while(index<n && row < numRows) {
    //             matrix[row][col] = s.charAt(index);
    //             row++;
    //             index++;
    //         }
    //         row-=2;
    //         col++;
    //         while(index<n && row>0 && col<numCols) {
    //             matrix[row][col] = s.charAt(index);
    //             row--;
    //             col++;
    //             index++;
    //         }
    //     }

    //     StringBuilder str = new StringBuilder();
    //     for(int i=0; i<numRows; i++) {
    //         for(int j=0; j<numCols; j++) {
    //             if(matrix[i][j]!=' ') {
    //                 str.append(matrix[i][j]);
    //             }
    //         }
    //     }
    //     return str.toString();
    // }

    /*
     * Solution 2: String Traversal
     * time complexity: O(n)
     * space complexity: O(1)
     */
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        int n = s.length();

        int charNumInSection = 2*numRows -2;
        StringBuilder str = new StringBuilder();
        for(int row = 0; row < numRows; row ++) {
            int index = row;
            while(index < n) {
                str.append(s.charAt(index));
                // jump to the next character in the same section
                if(row !=0 && row != numRows-1) {
                    // if the total characters in a section are charNumInSection and we are in the ith row, 
                    // then the number of characters above the current row will be 2*i, 
                    // and the number of chracters left will be charNumInbetween = charNumInSection -2*i
                    int nextgap = charNumInSection - 2*row; // diagonal difference
                    int nextindex = index + nextgap;
                    if(nextindex < n)
                        str.append(s.charAt(nextindex));
                }
                index+= charNumInSection; // Jump to the next section
            }
        }
        return str.toString();
    }

    /*
     * Solution 3: With StringBuilder array and simulate zigzag patter
     * time complexity: O(n)
     * space comlexity: O(n)
     */
    // public String convert(String s, int numRows) {
    //     if(numRows == 1) return s;

    //     StringBuilder[] arr = new StringBuilder[numRows];
    //     for(int i=0; i<numRows; i++) {
    //         arr[i] = new StringBuilder();
    //     }

    //     int row = 0;
    //     boolean down = false;

    //     for(char ch : s.toCharArray()) {
    //         arr[row].append(ch);
    //         if(row==0 || row==numRows-1) {
    //             down = !down;
    //         }
    //         row += down ? 1 : -1;
    //     }

    //     StringBuilder str = new StringBuilder();
    //     for(StringBuilder sub: arr) {
    //         str.append(sub);
    //     }
    //     return str.toString();
    // }
}
