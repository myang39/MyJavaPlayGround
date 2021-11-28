package newsbreak;

/*
_W_G__W_G_
_G________
___G__W_G_

Given and M * N String maze
W: Wall
_: Space
G: Gate
In place write for each space, the shortest path to a Gate

My approach:
using bfs (started from each gate), in place update the shortest path to a gate for each space
tc: (# of gates) * M*N
sc: Queue -> m * n
No visited array needed, because we can just update in space in the String Maze
optimize$: stop the adding element to the queue, when the previous value is already smaller
than the current value.

Interviewer's optimization:
optimize$ tc to M * N
instead of bfs from each gate one by one, we can bfs from all gates in the same time, enqueue all gates
together in the start
 */

import java.util.*;

public class MazeWithWallsAndGates {
    public static void main(String[] args) {
        String input =
                "_W____W_G_\n" +
                "_G________\n" +
                "___G__W___";
        input = "_W__\n" +
                "_G__\n" +
                "___G";
        String[] sArray = input.split("\n");
        List<String[]> list = new ArrayList<>();
        for (String s : sArray) {
            list.add(s.split(""));
        }
//        System.out.println(list.size());
//        System.out.println(list.get(0).length);
//        System.out.println(list.get(1).length);
//        System.out.println(list.get(2).length);
        String[][] maze = list.toArray(new String[list.size()][]);
        System.out.println("initial maze");
        printMaze(maze);

//        shortestPathForAllSpaces(maze);
        shortestPathForAllSpacesI(maze);
        System.out.println("final answer:");
        printMaze(maze);
    }

    private static void printMaze(String[][] maze) {
        System.out.println();
        System.out.println(
                Arrays.deepToString(maze)
                        .replace("], ", "]\n")
                        .replace("[[", "[")
                        .replace("]]", "]")
        );
    }

    public static void shortestPathForAllSpaces(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j].equals("G")) {
//                    System.out.println("Found a Gate");
                    bfsOneGateAtATime(array, i, j);
                }
            }
        }
    }

    public static void shortestPathForAllSpacesI(String[][] array) {
        List<int[]> gates = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j].equals("G")) {
                    gates.add(new int[]{i, j});
                }
            }
        }
        bfsAllGatesTogether(array, gates);
    }

    private static void bfsOneGateAtATime(String[][] array, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            step++;
//            printMaze(array);
            while (size > 0) {
                size--;
                int[] cur = q.poll();
                List<int[]> neis = getValidNeighbors(array, cur[0], cur[1]);
                for (int[] nei : neis) {
                    int x = nei[0], y = nei[1];
                    String s = array[x][y];
                    int val = Integer.MAX_VALUE;
                    if (!s.equals("_")) { // W and G should be filtered out in getValidNeighbors
                        val = Integer.valueOf(s);
                    }
                    if (step < val) {
                        array[x][y] = String.valueOf(step);
                        q.offer(nei);
                    }
                }
            }
        }
    }

    private static int[][] directions = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
    private static List<int[]> getValidNeighbors(String[][] array, int i, int j) {
        int m = array.length;
        int n = array[0].length;
        List<int[]> ret = new ArrayList<>();
        for (int k = 0; k < directions.length; k++) {
            int ii = i + directions[k][0];
            int jj = j + directions[k][1];
            if (ii >= 0 && ii < m && jj >= 0 && jj < n &&
                    !array[ii][jj].equals("W") && !array[ii][jj].equals("G")) {
                ret.add(new int[]{ii, jj});
            }
        }
        return ret;
    }

    // bfs Start from all gates together
    private static void bfsAllGatesTogether(String[][] array, List<int[]> gates) {
        Queue<int[]> q = new LinkedList<>();
        for (int[] gate : gates) {
            q.offer(gate);
        }
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            step++;
//            printMaze(array);
            while (size > 0) {
                size--;
                int[] cur = q.poll();
                List<int[]> neis = getValidNeighbors(array, cur[0], cur[1]);
                for (int[] nei : neis) {
                    int x = nei[0], y = nei[1];
                    String s = array[x][y];
                    int val = Integer.MAX_VALUE;
                    if (!s.equals("_")) { // W and G should be filtered out in getValidNeighbors
                        val = Integer.valueOf(s);
                    }
                    if (step < val) {
                        array[x][y] = String.valueOf(step);
                        q.offer(nei);
                    }
                }
            }
        }
    }
}
