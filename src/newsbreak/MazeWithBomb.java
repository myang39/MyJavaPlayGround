package newsbreak;

/*
0 0 0 1
1 1 1 1
0 0 0 0

0 is open space
1 is a wall

figure out whether there is a path
from top left corner
to bottom right corner

A user has 1 bomb that can be used to
open a wall
 */

public class MazeWithBomb {
    public static void main(String[] args) {
        int[][] array = {
                {0,0,0,1},
                {1,1,1,1},
                {0,0,0,0}
        };
    }

    public boolean hasPath(int[][] array) {
//        return dfs();
        return false;
    }

    private boolean dfs(int[][] array, int i, int j, int[][] visited) {
        return false;
    }
}
