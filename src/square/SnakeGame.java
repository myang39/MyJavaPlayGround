package square;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SnakeGame {
  public static void main(String[] args) {
    /*
    ["SnakeGame","move","move","move","move","move","move"]
    [[3,2,[[1,2],[0,1]]],["R"],["D"],["R"],["U"],["L"],["U"]]
     */
    SnakeGame game = new SnakeGame(3, 2, new int[][]{{1,2}, {0,1}});
    String[] moves = {"R", "D", "R", "U", "L", "U"};
    for (String d : moves) {
      System.out.print(d + " ");
    }
    System.out.println();
    for (String d : moves) {
      System.out.print(game.move(d) + " ");
    }
  }

  int width;
  int height;
  int[][] food;
  int foodIndex;
  int score;

  // 2d info encoded in 1D with two copies
  Deque<Integer> body; // find tail and head easily
  Set<Integer> set; // check whether a move collide itself into its body easily

  public SnakeGame(int w, int h, int[][] food) {
    width = w;
    height = h;
    this.food = food;
    body = new LinkedList<>();
    body.offerLast(0);
    set = new HashSet<>();
    set.add(0);
  }

  public int move(String direction) {
    if (score == -1) { // game is already over, do nothing
      return score;
    }

    // calculate new head
    int rowHead = body.peekFirst() / width;
    int colHead = body.peekFirst() % width;

    switch(direction) {
      case "U": rowHead--;
                break;
      case "D": rowHead++;
                break;
      case "L": colHead--;
                break;
      default: colHead++;
                break;
    }

    set.remove(body.peekLast()); // new head move to tail is legal, remove tail
    // for now for case 1
    // case 1: out of boundary or eat itself
    if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width
    || set.contains(rowHead * width + colHead)) {
      return score = -1;
    }

    // add newHead
    set.add(rowHead * width + colHead);
    body.offerFirst(rowHead * width + colHead);

    // case 2: eat a food
    if (foodIndex < food.length && rowHead == food[foodIndex][0] &&
    colHead == food[foodIndex][1]) {
      // add tail back, grow length by 1
      set.add(body.peekLast());
      foodIndex++;
      return ++score;
    }

    // case 3: normal case
    // remove tail from body
    body.pollLast();
    return score;
  }
}