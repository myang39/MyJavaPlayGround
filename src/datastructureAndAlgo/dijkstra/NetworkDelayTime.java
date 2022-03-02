package datastructureAndAlgo.dijkstra;

import java.util.*;

/**
 * Dijkstra time: O(E log(V)),
 * E = number of edges, V is number of vertexes
 *
 * but can be O(E log(E)) from labuladong
 */
public class NetworkDelayTime {
    // times is for edge weight, n vertexes, k is the start vertex
    // calculate how long it takes to pass info to all vertexes starting from node k
    int networkDelayTime(int[][] times, int n, int k) {
        // node naming starting from 1, so create an n + 1 adjacency list
        List<int[]>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        // create graph
        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            // from from -> List<(to, weight)>
            graph[from].add(new int[]{to, weight});
        }

        // Invoke dijkstra, calculate the shortest path to all nodes stating from node k
        int[] distTo = dijkstra(k, graph);

        // find the longest path among all shortest paths
        int res = 0;
        for (int i = 0; i < distTo.length; i++) {
            if (distTo[i] == Integer.MAX_VALUE) {
                // there is a node unreachable
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }
        return res;
    }


    class State {
        // id for the node in graph
        int id;
        // the shortest path from start to this node
        int distFromStart;

        State(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }

    // Start from node k, get the shortest path to all the other nodes
    int[] dijkstra(int start, List<int[]>[] graph) {
        // distTo[i] = weight of the shortest path from start to node i
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        // base case, start to start's shortest path is 0
        distTo[start] = 0;
        Queue<State> pq = new PriorityQueue<>((a, b) ->
        {
            return a.distFromStart - b.distFromStart;
        });

        pq.offer(new State(start, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int curId = cur.id;
            int curDist = cur.distFromStart;

            if (curDist > distTo[curId]) {
                continue;
            }

            for (int[] nei : graph[curId]) {
                int nextId = nei[0];
                int nextDist = distTo[curId] + nei[1];

                // update dp table
                if (distTo[nextId] > nextDist) {
                    distTo[nextId] = nextDist;
                    pq.offer(new State(nextId, nextDist));
                }
            }
        }
        return distTo;
    }
}
