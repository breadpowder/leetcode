package source.tree.TreeMap;

import java.util.*;
/*

315
You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

 */

//ArrayList<Integer> a= new ArrayList<Integer>(size) a.set(size-1,v) won't workd

//Learn from:

// Array.asList(int[]) will fail since int is not an object
// way to use

// 1. edge case in BST,  when update < length, and count x = i-1
// 2. Anomyous function to create a comparator Comparator<int[]> cmp = new Comparator<int[]>(){
//      public int compare(int[] o1, int[] o2) {}
// }
public class CountofSmallerNumbersAfterSelf {

    //solution 1 (time of boundary):  TreeMap, reverse the order. out of time if revered ordered (4,3,2,1), o(n) time

    public List<Integer> reversePairsOne(int[] nums) {

        // see [5,6,1,3] -> [3,1,6,5]
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();

        Integer[] result = new Integer[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {

            int num = nums[i];
            if (treeMap.containsKey(num)) {
                treeMap.put(num, treeMap.get(num) + 1);
            } else treeMap.put(num, 1);

            Map<Integer, Integer> sub = treeMap.subMap(Integer.MIN_VALUE, true, num - 1, true);
            int count = 0;
            for (int value : sub.values()) {
                count += value;
            }

            result[i] = count;
        }

        return Arrays.asList(result);

    }

    //solution 2: BIT

    public class BIT {

        private int[] rank;

        public BIT(int size) {
            this.rank = new int[size];
        }

        public void update(int idx) {

            for (; idx < rank.length; idx += (idx & -idx)) {
                rank[idx] += 1;
            }
        }

        public int count(int idx) {
            int result = 0;
            for (; idx > 0; idx -= (idx & -idx)) {
                result += rank[idx];
            }
            return result;
        }
    }


    public List<Integer> countSmaller(int[] nums) {

        if (nums.length == 0) return new ArrayList<Integer>();
        // nums [5,6,1,2] ->        // sorted [1,2,5,6]  ->  aux_rank[3,4,1,2]

        int[][] numsWithPos = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {
            numsWithPos[i][0] = nums[i];
            numsWithPos[i][1] = i;
        }
        // use lambda expression is slow ?
        //Arrays.sort(numsWithPos,(a,b) -> a[0] - b[0]);

        Comparator<int[]> cmp = new Comparator<int[]>() {

            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        };

        Arrays.sort(numsWithPos, cmp);

        int[] rank = new int[nums.length];

        int id = 0;
        rank[numsWithPos[0][1]] = ++id;

        for (int i = 1; i < nums.length; i++) {
            if (numsWithPos[i][0] == numsWithPos[i - 1][0]) {
                rank[numsWithPos[i][1]] = id;
            } else {
                rank[numsWithPos[i][1]] = ++id;
            }
        }

        BIT bits = new BIT(id + 1);
        Integer[] result = new Integer[nums.length];
        for (int i = rank.length - 1; i >= 0; i--) {
            result[i] = bits.count(rank[i] - 1);
            bits.update(rank[i]);
        }

        return Arrays.asList(result);
    }
}