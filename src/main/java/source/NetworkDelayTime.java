package source;


import java.util.*;

//take away if priority queue is there, i.e. ordered, chnange the object value won't work

public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int N, int K) {

        if (times.length==0) return -1;


        List<List<Tuple>> graph = new ArrayList<>(N+1);

        //TODO after initial it is empty? so graph.get(index) throw indexOutOfBound e
        // adj list save a lof of time compared with hashMap from 90ms ->30 ms
        for(int i=0; i<N+1; i++)
        {

            graph.add(new LinkedList<Tuple>());
        }


        for(int i=0; i< times.length; i++)
            for(int j=0; j<3; j++){
                int source = times[i][0];

                final int target =  times[i][1];

                final int dist =  times[i][2];

                graph.get(source).add(new Tuple(target, dist));

               /*                 //TODO effective final in lambda expression

               graph.computeIfPresent(source, (k,v) -> {
                            if (v.containsKey(target) && v.get(target) > dist) {
                                v.put(target, dist);
                            }
                            else{
                                v.put(target, dist);

                            }
                            return v;
                        }
                );*/
            }
        return delayTimeRecOptimized(graph, new boolean[N+1],K,N);

    }


    public int delayTimeRec(Map<Integer, Map<Integer, Integer>> graph, boolean[] visited, int K, int N) {

        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        //initiate, set all to MAX except K
        pq.add(new Vertex(K, 0));
        for(int i=1; i<=N; i++) {
            if (i != K) {
                pq.add(new Vertex(i,Integer.MAX_VALUE));
            }

        }

        int visitedCount = 0;

        while (!pq.isEmpty()) {

            //extract min.
            Vertex v = pq.poll();
            visited[v.nodeId] = true;

            if(v.distance == Integer.MAX_VALUE){
                return -1;
            }
            visitedCount++;

            if(visitedCount == N){
                return v.distance;
            }

            // relax each vertex in the queue, through v.distance + edge > toRelax.distance
            // TODO optimize should be for each vertex in the adjancent of v, relax, instead of checking one by one
            Map<Integer, Integer> edges = graph.get(v.nodeId);

            List<Vertex> tmp = new LinkedList<>();
            while (!pq.isEmpty()) {
                Vertex toRelax = pq.poll();
                    if (!visited[toRelax.nodeId] && edges!=null && edges.containsKey(toRelax.nodeId)) {
                            int newDistance =  v.distance + edges.get(toRelax.nodeId) < toRelax.distance ?  v.distance + edges.get(toRelax.nodeId): toRelax.distance;
                                tmp.add(new Vertex(toRelax.nodeId,newDistance));
                        }
                        else {
                            tmp.add(toRelax);
                        }

            }

            pq.addAll(tmp);
        }
        return -1;
    }


    public int delayTimeRecOptimized(List<List<Tuple>> graph, boolean[] visited, int K, int N) {

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        Set<Integer> visitedSet = new HashSet<Integer>();

        int[] distance = new int[N+1];

        Arrays.fill(distance, Integer.MAX_VALUE);

        Vertex v = new Vertex(K, 0);
        pq.add(v);

/*
               //initiate, set all to MAX except K
for(int i=1; i<=N; i++) {
            if (i != K) {
                v= new Vertex(i,Integer.MAX_VALUE);
                pq.add(v);
                allVertex.put(i, v);
            }

        }*/

        // do not use vistite count, you can use visitedSet
        //int visitedCount = 0;

        while (visitedSet.size()!= N && !pq.isEmpty()) {

            //extract min.
            v = pq.poll();


            //TODO can remove if contains line, because dijstra gurantee, no duplicates
            if (!visitedSet.contains(v.nodeId)) {
                visitedSet.add(v.nodeId);

                distance[v.nodeId] = v.distance;

                if (v.distance == Integer.MAX_VALUE) {
                    return -1;
                }

                if(visitedSet.size() == N)
                {
                    return v.distance;
                }

                // relax each vertex in the queue, through v.distance + edge > toRelax.distance
                List<Tuple> edges = graph.get(v.nodeId);

                for (Tuple t : edges) {

                    int nodeId = t._1;

                    int dist = t._2;

                    // pull the vertex
                    if(!visitedSet.contains(nodeId) &&  v.distance + dist < distance[nodeId])
                    {
                        pq.add(new Vertex(nodeId, v.distance + dist));
                    }
                }
            }
        }
        return -1;
    }


    public static class Tuple {

        public int _1;
        public int _2;


        public Tuple(int _1, int _2) {
            this._1 = _1;
            this._2 = _2;
        }
    }

    public static class Vertex implements Comparable<Vertex> {

        public int distance;

        public int nodeId;


        public Vertex(int nodeId, int distance) {
            this.distance = distance;
            this.nodeId = nodeId;
        }


        @Override
        public int compareTo(Vertex o) {
            return distance - o.distance;
        }

    /*    @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Vertex))
                return  false;

            else{
               return ((Vertex) obj).nodeId == this.nodeId;
            }
        }

        @Override
        public int hashCode() {
            return nodeId;
        }*/

    }

    public int bellmanFord_networkDelayTime(int[][] times, int N, int K) {

        int[] dist = new int[N];

        int MAX = 101 * 100;


        //STEP1 initiate with 0 and max
        Arrays.fill(dist, MAX);
        dist[K-1] = 0;

        //STEP 2 relax edge in (V-1) * E time
        for(int i=1; i<N; i++)
            for(int[] time: times)
            {
                int source = time[0] -1 ;
                int target = time[1] -1;
                int edge = time[2];

                dist[target] = dist[target] > dist[source] + edge ? dist[source] + edge : dist[target];
            }

        //Step 3, here compute the max
        int result = Arrays.stream(dist).max().getAsInt();

        if(result == MAX)
            return -1;

        return result;
    }


    public static void main(String[] args){

       NetworkDelayTime testee = new NetworkDelayTime();

        int result =0;

       result = testee.networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}},4,2 );

       System.out.println(result);

       result = testee.networkDelayTime(new int[][]{{1,2,1},{2,3,2},{1,3,2}},3,1 );

        System.out.println(result);

    }
}
