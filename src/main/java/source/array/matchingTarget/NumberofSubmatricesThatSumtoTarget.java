package source.array.matchingTarget;

/*
1074. Number of Submatrices That Sum to Target
Hard

Given a matrix, and a target, return the number of non-empty submatrices that sum to target.

A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are
different if they have some coordinate that is different: for example, if x1 != x1'.


Example 1:

Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.

Example 2:

Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.



Note:

    1 <= matrix.length <= 300
    1 <= matrix[0].length <= 300
    -1000 <= matrix[i] <= 1000
    -10^8 <= target <= 10^8

 */

import java.util.HashMap;
import java.util.Map;

//take away: it is an updated version of number of continous array equals target

// map API getOrDefault()
// to initialiate sumCount.put(0,1) and loop order is j and i
public class NumberofSubmatricesThatSumtoTarget {

    public int numSubmatrixSumTarget(int[][] matrix, int target) {

        //row prefixsum [i][j] denotes ith row prefix sum

        int[][] prefix = new int[matrix.length][matrix[0].length];

        for(int i=0; i<matrix.length; i++) {
            int sum =0;
            for(int j=0; j<matrix[0].length; j++)
            {
                sum +=matrix[i][j];
                prefix[i][j] = sum;
            }
        }

        int count =0;

        for(int j=0; j<matrix[0].length; j++)
            for(int k=j; k<matrix[0].length; k++){

                Map<Integer, Integer> sumCount = new HashMap<Integer, Integer>();
                sumCount.put(0,1);

                int sum = 0;
                for(int i=0; i<matrix.length; i++){
                    sum += prefix[i][k] - (j==0 ?0 : prefix[i][j-1]);
                    count += sumCount.getOrDefault(sum-target,0);

                    sumCount.put(sum,sumCount.getOrDefault(sum,0)+1);

                }
            }

        return count;
    }
}
