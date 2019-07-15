package source.dp;

import java.util.Arrays;

/*
1024. Video Stitching
Medium

155

15

Favorite

Share
You are given a series of video clips from a sporting event that lasted T seconds.  These video clips can be overlapping with each other and have varied lengths.

Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].  We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].

Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).  If the task is impossible, return -1.



Example 1:

Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
Output: 3
Explanation:
We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
Then, we can reconstruct the sporting event as follows:
We cut [1,9] into segments [1,2] + [2,8] + [8,9].
Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
Example 2:

Input: clips = [[0,1],[1,2]], T = 5
Output: -1
Explanation:
We can't cover [0,5] with only [0,1] and [0,2].
Example 3:

Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
Output: 3
Explanation:
We can take clips [0,4], [4,7], and [6,9].
Example 4:

Input: clips = [[0,4],[2,8]], T = 5
Output: 2
Explanation:
Notice you can have extra video after the event ends.

 */

//Method 1: greedy algorithm: find the segment with the most latest end time and covers a previous end time
//Methdd 2: review dp method https://www.youtube.com/watch?v=tdrPFN9d1y4&list=PLLuMmzMTgVK7vEbeHBDD42pqqG36jhuOr&index=5
public class VideoStitching {

    public class Segment implements Comparable<Segment>{

        public int s;

        public int e;

        public Segment (int _s, int _e){

            this.s = _s;
            this.e = _e;

        }


        @Override
        public int compareTo(Segment o) {
            return this.s -  o.s == 0 ? this.e - o.e : this.s - o.s;
        }
    }

    public int videoStitching(int[][] clips, int T) {

        Segment[] segments = new Segment[clips.length+1];

        int idx = 0;
        for(int[] clip: clips){
            segments[idx] = new Segment(clip[0], clip[1]);
            idx++;
        }

        segments[idx] = new Segment(0,0);

        Arrays.sort(segments);

        int pre = 0;
        int next = 0;
        int count =0;

        while(segments[next].e<T) {
           next = searchNext(pre, segments);

           if(next == pre) return -1;
           pre = next;
           count++;
        }

        return count;
    }


    public int searchNext(int p_end, Segment[] segments){


        int forward =0 ;

        int idx = p_end;
        for(int i =  p_end; i<segments.length; i++){

            if(segments[i].s >= segments[p_end].s && segments[i].s <= segments[p_end].e && segments[i].e - segments[p_end].e >= forward){

                idx = i;
                forward =  segments[i].e - segments[p_end].e;
            }

            if(segments[i].s > segments[p_end].e){
                break;
            }

        }

        return idx;
    }


    public static void main(String[] args){

        int[][] input = new int[][]{new int[]{0,2},new int[]{4,6},new int[]{8,10},new int[]{1,9},new int[]{1,5},new int[]{5,9}};
        VideoStitching testee  = new VideoStitching();

       // System.out.println(testee.videoStitching(input,10));

        input =  new int[][]{new int[]{0,28},new int[]{28,48},new int[]{47,90},new int[]{24,86}};
        System.out.println(testee.videoStitching(input,72));

    }
}
