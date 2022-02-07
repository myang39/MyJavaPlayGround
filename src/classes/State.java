package classes;

import java.util.Objects;

class State implements Comparable {
  public int estimation, steps, row, col, k;
  private static int[] target;

  public static void initializeTarget(int[] targetValue) {
    target = targetValue;
  }

  public State(int steps, int row, int col, int k, int[] target) {
    this.steps = steps;
    this.row = row;
    this.col = col;
    this.k = k;

    this.target = target;
    int mDistance = target[0] - row + target[1] - col;
    this.estimation = mDistance + steps;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + Objects.hashCode(row);
    hash = hash * 31 + Objects.hashCode(col);
    hash = hash * 31 + Objects.hashCode(k);
    return hash;
  }

  @Override
  public int compareTo(Object o) {
    State other = (State) o;
    return this.estimation - other.estimation;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof State)) {
      return false;
    }
    State newState = (State) o;
    return (row == newState.row) && (col == newState.col)
            && (k == newState.k);
  }
}
