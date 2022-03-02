package google;

import java.util.*;

public class Q0_BabylonGameSolver {

    List<int[]> stacks;
    Map<String, Integer> stateToResults;
    static int countWhoWins = 0;
    public static void main(String[] args) {
        // Initialize the start state for stacks
        // 0, 1, 2, 3 for four colors
        Q0_BabylonGameSolver solution = new Q0_BabylonGameSolver();
        solution.stateToResults = new HashMap<>();
        solution.stacks = new LinkedList<>(); // top tile color and count
        for (int i = 0; i < 4; i++) { // 4 colors
            for (int j = 0; j < 3; j++) { // each color has 3 tiles
                solution.stacks.add(new int[]{i, 1});
            }
        }

        System.out.println(solution.whoWins(solution.stacks, solution.stateToResults));
        System.out.println(countWhoWins);
    }


    /**
     *
     * @param stacks current stacks state, which should not be changed.
     * @param i stack index 1
     * @param j stack index 2
     * @return the next state is possible, if not, return null
     */
    List<int[]> move(List<int[]> stacks, int i, int j) {
//        System.out.println("stacks.size()" + stacks.size() +  " i " + i + " j " + j);
//        if (stacks.get(i)[0] != stacks.get(j)[0] && stacks.get(i)[1] != stacks.get(j)[1]) {
//            return null;
//        }
//        List<int[]> nextState = deepCopyState(stacks);
        stacks.get(i)[1] += stacks.get(j)[1];
        stacks.remove(j);

//        nextState.sort(nextState, (a, b) -> {
//            if (a[0] != b[0]) {
//                return a[0] - b[0];
//            } else {
//                return a[1] - b[1];
//            }
//        });
        return stacks;
    }


    /**
     *
     * @param state is the current state before the player makes move
     * @param positionToResults is a map memorizing all states to result we met so far
     * @return 0 mean the player who is about the make a move will win if both perfectly, 1 otherwise.
     */

    int whoWins(List<int[]> state, Map<String, Integer> positionToResults) {
        countWhoWins++;
        List<int[]> current = deepCopyState(state);
        String currentStateString = stateToString(current);
        if (positionToResults.containsKey(currentStateString)) {
            return positionToResults.get(currentStateString);
        }

        int winner = 1; // if no move, the other player win
        int len = current.size();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (current.get(i)[1] == 0 || current.get(j)[1] == 0) {
                    continue;
                }
                if (i == j || (current.get(i)[0] != current.get(j)[0] && current.get(i)[1] != current.get(j)[1])) {
                    continue;
                }
                int jHeight = current.get(j)[1];
                current.get(i)[1] += current.get(j)[1];
                current.get(j)[1] = 0;

                if (1 == whoWins(current, positionToResults)) {
                    winner = 0;
                    positionToResults.put(currentStateString, winner);
                     // if there is a move that leads to a winner = 1 result, this player has won.
                    return winner;
                }
                // next != null && haven't found winner yet, backtracking current
                current.get(j)[1] = jHeight;
                current.get(i)[1] -= jHeight;
            }
        }

        positionToResults.put(currentStateString, winner);
        return winner;
    }

    /**
     *
     * @param stacks is the stacks we want to copy from
     * @return a new stack, which is a deep copy of the input stack
     * time: O(stacks.size())
     * space: O(stacks.size())
     */
    static List<int[]> deepCopyState(List<int[]> stacks) {
        List<int[]> copy = new LinkedList<>();
        for (int[] stack : stacks) {
            copy.add(stack.clone());
        }
        return copy;
    }

    /**
     *
     * @param stacks is the stacks we want to make a string from
     * @return a string representing the stacks
     *
     * time: O(stacks.size())
     * space: O(stacks.size())
     */
    static String stateToString(List<int[]> stacks) {
        stacks.sort((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return a[1] - b[1];
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int[] stack : stacks) {
            sb.append(stack[0]).append(1);
        }
        return sb.toString();
    }

}
