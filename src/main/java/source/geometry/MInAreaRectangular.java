package source.geometry;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*

939. Minimum Area Rectangle

Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4

Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2



Note:

    1 <= points.length <= 500
    0 <= points[i][0] <= 40000
    0 <= points[i][1] <= 40000
    All points are distinct.


 */

//TODO version n^2 too slow
public class MInAreaRectangular {


    class Line implements Comparable<Line> {

        public int x;

        public int y_l;

        public int y_h;

        public Line(int _x, int _y_l, int _y_h) {
            this.x = _x;
            this.y_l = _y_l;
            this.y_h = _y_h;
        }


        public int compareTo(Line that) {

            return this.x - that.x;

        }

        public int hashCode() {
            return String.valueOf(y_l).hashCode() + String.valueOf(y_h).hashCode();
        }


        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) return false;

            Line l = (Line) o;

            return (this.y_l == l.y_l && this.y_h == l.y_h);
        }
    }


    public int minAreaRect(int[][] points) {


        //line sweeping, (x1, y1), (x2, y2)
        // if x1 ==  x2 look at y1

        /*Point[] ps =  new Point[points.length];


        for(int i=0 ; i< points.length; i++){

           int[] p =  points[i];

           ps[i] = new Point(p[0], p[1]);
        }

        Arrays.sort(ps);

        int areas = Integer.MAX_VALUE;
        for(int i=0; i< points.length; i++){

            if(i<points.length)
            ps[i]

        }*/


        List<Line> lines = new LinkedList<Line>();


        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++) {

                int[] p1 = points[i];
                int[] p2 = points[j];

                if (p1[0] == p2[0]) {
                    int x = p1[0];
                    int y_l = Math.min(p1[1], p2[1]);
                    int y_h = Math.max(p1[1], p2[1]);

                    lines.add(new Line(x, y_l, y_h));
                }

            }


        Collections.sort(lines);

        HashMap<Line, Integer> lineMap = new HashMap<Line, Integer>();

        int areas = Integer.MAX_VALUE;

        for (Line line : lines) {

            if (lineMap.containsKey(line)) {

                int x = lineMap.get(line);
                areas = Math.min(areas, (line.y_h - line.y_l) * (line.x - x));

                lineMap.remove(line);

            }

            lineMap.put(line, Integer.valueOf(line.x));
        }

        return (areas == Integer.MAX_VALUE) ? 0 : areas;
    }


    public int minAreaRect2(int[][] points) {


        List<Integer>[] ps = new LinkedList[40001];

        List<Line>[]  lines = new LinkedList[40001];


        //List<Integer> index = new ArrayList<Integer>(points.length);


        for(int i=0 ; i< points.length; i++){
            int[] p = points[i];

            int x = p[0];

            int y1 = p[1];


            if(ps[x] == null){
                ps[x] = new LinkedList<Integer>();
            }

            if(ps[x].size()!=0){


                for(Integer y2: ps[x]){


                    int y_l = Math.min(y1, y2);
                    int y_h = Math.max(y1, y2);

                    if(lines[x]==null) lines[x] = new LinkedList<Line>();
                    lines[x].add(new Line(x, y_l, y_h));

                }

            }

            ps[x].add(Integer.valueOf(p[1]));


        }


        HashMap<Line, Integer> lineMap = new HashMap<Line, Integer>();

        int areas = Integer.MAX_VALUE;


        for (List<Line> list : lines) {


            if (list!=null){

                for(Line line: list){
                    if(lineMap.containsKey(line)) {

                        int x = lineMap.get(line);
                        areas = Math.min(areas, (line.y_h - line.y_l) * (line.x - x));

                        lineMap.remove(line);

                    }

                    lineMap.put(line, Integer.valueOf(line.x));
                }
            }
        }

        return (areas == Integer.MAX_VALUE) ? 0 : areas;


    }

}
