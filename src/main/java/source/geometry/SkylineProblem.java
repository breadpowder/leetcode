package source.geometry;

import java.awt.*;
import java.util.*;
import java.util.List;

//pls read this here https://www.youtube.com/watch?v=11dq8ux25oE

/*
218
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
Buildings Skyline Contour

The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].


 */


//TODO two data structures:
// a. heap does not support remove in logn time
// b, BST all in lgn time

//TODO read this very concised code, order height in a very nice way

/*

    public List<List<Integer>> getSkyline(int[][] buildings) {


        List<int[]> sorted = new ArrayList<int[]>(buildings.length);

        for(int i=0; i<buildings.length; i++){

            int[] b= buildings[i];
            int start = b[0];
            int end = b[1];
            int height = b[2];

            sorted.add(new int[]{start, -height});
            sorted.add(new int[]{end, height});

        }

        Collections.sort(sorted, (a,b) -> {
            if(a[0] == b[0])
                return a[1] - b[1];

            else return a[0] - b[0];
            });


        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        LinkedList<List<Integer>> result = new LinkedList<>();


        int prev = 0;
        for(int[] b:  sorted){


            if(b[1]<0){
                pq.offer(-b[1]);
            }

            else{
               pq.remove(b[1]);
            }

            int cur = pq.isEmpty() ? 0 : pq.peek();
            if(cur != prev){
                result.add(Arrays.asList(b[0], cur));
                prev = cur;
            }
        }

        return  result;

}
 */


// TODO simplify equals methods using id instead of using x1,x2 and y
public class SkylineProblem {


    /* first try
    class Event {

        public boolean isStart;

        public int x1;

        public int x2;

        public int y;

        public Event(int _x1, int _x2,  int _y, boolean _isStart){

            this.x1 = _x1;

            this.x2 = _x2;

            this.y = _y;

            this.isStart = _isStart;

        }



        public int getX(){
            return  isStart ? x1 : x2;
        }


        public boolean equals(Object that){
            if(that==null || that.getClass() != this.getClass()) return false;

            Event p = (Event) that;

            return this.x1 == p.x1 && this.x2 == p.x2 && this.y == p.y;

        }


        //TODO what's the best hash function hascode in integer
        public int hashCode(){

            return 31 *(this.x1 + this.x2  + this.y);
        }

    }


    public List<List<Integer>> getSkyline(int[][] buildings) {

        //sweep line algorithm
        //(2,9, 10)
        //  (3, 7, 15)
        //  (5, 12, 12)

        //TODO ensure two thins:
        //1.  if a same x, start will be in front of the end, so (1,2) (2,3) will not trigger an empty queue
        //2.  if it is the same x, ensure that highest come first (1, 2, 5), (1,2,6), (1,2,6) comes first
        Comparator<Event> eventComparator = new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getX() - o2.getX() !=0 ? o1.getX() - o2.getX() : (o1.isStart && o2.isStart) ? o2.y - o1.y: o1.isStart ? -1 :1;
            }
        };

        Comparator<Event> heightComparator = new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o2.y - o1.y;
            }
        };


        PriorityQueue<Event> pq = new PriorityQueue<>(eventComparator);

        PriorityQueue<Event> curPq = new PriorityQueue<Event>(heightComparator);



        //step 1: initialize a pq for holding all events
        for(int[]  b: buildings){

            int start = b[0];

            int end = b[1];

            int height = b[2];

            Event s = new Event(start, end, height, true);

            Event e = new Event(start, end, height, false);

            pq.offer(s);

            pq.offer(e);
        }

        //step2:  poll from pq, compare the event and generate result

        LinkedList<List<Integer>> result = new LinkedList<>();

        while(!pq.isEmpty()){

            Event e = pq.poll();

            int cur_x = 0;

            if(e.isStart){
                curPq.offer(e);
                cur_x = e.x1;
            }

            else{
                //once it is remove for example (x 7, h: 15), the next one (x: 5, h:12) should find the x=7

                    curPq.remove(e);
                    cur_x = e.x2;

                    while(!pq.isEmpty() && pq.peek().getX()==e.getX()) {
                        if(!pq.peek().isStart){
                            curPq.remove(pq.peek());
                            pq.remove(pq.peek());
                    }
                }
            }

            Event candidate = curPq.peek();

            //[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
            if(candidate!=null){

                if(!result.isEmpty() ) {
                    List<Integer> l = result.getLast();
                    if(l.get(1) == candidate.y || l.get(0) == e.getX()){
                        continue;
                    }
                }

                ArrayList<Integer> l = new ArrayList<>(Arrays.asList(cur_x, candidate.y));
                result.add(l);
            }

            else{

                ArrayList<Integer> l = new ArrayList<>(Arrays.asList(cur_x, 0));
                result.add(l);
            }
        }

        return result;
    }*/


    class Event {

        public int id;

        //flag  = 0 is start
        public boolean isStart;

        public int x;

        public int x_end;

        public int y;

        public Event(int id, int x, int x_end, int y,boolean isStart) {
            this.id = id;
            this.isStart = isStart;
            this.x = x;

            this.x_end = x_end;
            this.y = y;
        }

        public int getX() {

            if(isStart) return x;  else return x_end;
        }

        @Override
        public boolean equals(Object that) {
            if (that == null || that.getClass() != this.getClass()) return false;

            Event o = (Event) that;

            return this.id == o.id;
        }

        @Override
        public int hashCode() {
            return this.id;
        }

    }

    public List<List<Integer>> getSkyline(int[][] buildings) {

        Comparator<Event> eventComparator = new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getX()- o2.getX() !=0 ? o1.getX()- o2.getX():  o1.y - o2.y;
            }
        };

        Comparator<Integer> heightComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        };



        PriorityQueue<Event> pq = new PriorityQueue<>(eventComparator);

        for(int i=0; i<buildings.length; i++){

            int[] b= buildings[i];
            int start = b[0];

            int end = b[1];

            int height = b[2];

            Event s = new Event(i, start, end,  -height, true);

            Event e = new Event(i, start, end, height, false);

            pq.offer(s);

            pq.offer(e);
        }

        //TODO TreeMap
        TreeMap<Integer, Event> curBST =  new TreeMap<Integer, Event>(heightComparator);
        LinkedList<List<Integer>> result = new LinkedList<>();

        while(!pq.isEmpty()){
            Event e = pq.poll();

            if(e.isStart){
                int cur_y = -e.y;

                if(curBST.isEmpty() || cur_y > curBST.firstKey()){
                    result.add(Arrays.asList(e.x, cur_y));
                }

                Event prev = curBST.get(cur_y);

                if(prev == null || e.x_end >= prev.x_end) {
                    curBST.put(cur_y, e);
                }
            }

            else{
                int pre_y = curBST.isEmpty() ? 0 : curBST.firstKey();
                if(curBST.get(e.y).id == e.id) {
                    curBST.remove(e.y);
                }


                //TODO need to remove all end with x_end
                if(curBST.isEmpty() || curBST.firstKey() < pre_y)
                    result.add(Arrays.asList(e.x_end, curBST.isEmpty()? 0: curBST.firstKey()));
                }
        }

        return  result;

    }





     public static void main(String[] args) {

        //How to initialize a 2-dimension array
          int[][] data1 = {{2,9,10}, {3,7,15},{5,12,12}, {15,20,10},{19,24,8}};
          int[][] data2 = {{1,2,1},{1,2,2}, {1,2,3}};

          int[][] data3 = {{2,4,7},{2,4,5},{2,4,6}};

          int[][] data4 = {{3,7,8},{3,8,7},{3,9,6},{3,10,5},{3,11,4},{3,12,3},{3,13,2},{3,14,1}};

          int[][] data5 = {{0,2,3},{2,5,3}};

       //bad test case [[0,2,3],[2,5,3]], ordering ?
       SkylineProblem sp = new SkylineProblem();

       sp.print(data1);
       sp.print(data2);
       sp.print(data3);
       sp.print(data4);
       sp.print(data5);


     }


    public void print(int[][] data ){
        List<List<Integer>> a = getSkyline(data);

        for(List<Integer> l: a){
            System.out.print("[" + l.get(0) + "," + l.get(1) + "],");
        }

        System.out.println();
    }
}
