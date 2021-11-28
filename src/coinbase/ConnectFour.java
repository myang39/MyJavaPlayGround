package coinbase;

import java.util.Arrays;
import java.util.Scanner;


// follow up: AI try to always win
public class ConnectFour {
    public static void main(String[] args) {
        ConnectFour cf = new ConnectFour(6,6);
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a number: ");
        while (true) {
            int col = reader.nextInt();
            cf.move(col);
        }
    }

    int row;
    int col;
    int[][] board;
    int[] height;
    int player = 2;
    ConnectFour(int row, int col) {
        this.row  = row;
        this.col = col;
        board = new int[row][col];
        height = new int[col];
    }

    // player 1 and 2
    // return 0 indicates error
    // return -1 indicates no win or lose for this round
    public int move(int col) {
        // sanity check
        if (col < 0 || col >= this.col || height[col] >= row) {
            return 0;
        }
        if (player == 1) {
            player = 2;
        } else {
            player = 1;
        }
        int row = height[col]++;
        board[row][col] = player;
        System.out.println("[0, 1, 2, 3, 4, 5]");
        System.out.println(Arrays.deepToString(board).replace("], ", "]\n")
                .replace("[[", "[").replace("]]", "]"));
        if (checkWin(row, col, player)) {
            System.out.println("winner: " + player);
            return player;
        }
        return -1;
    }

    public boolean checkWin(int row, int col, int player) {
        // row case
        int total = 0;
        int i = col, j = col;
        while (i >= 0) {
            if (board[row][i] == player) {
                i--;
            } else {
                break;
            }
        }
        while (j < this.col) {
            if (board[row][j] == player) {
                j++;
            } else {
                break;
            }
        }
        total = j - i - 1;
        System.out.println("total horizontal " + total);
        if (total >= 4) {
            return true;
        }
        // 1 1  1 1 0
        // i      j
        // 0 1  2 3 4
        // col case
        i = row; j = row;
        while (i >= 0) {
            if (board[i][col] == player) {
                i--;
            } else {
                break;
            }
        }
        while (j < this.row) {
            if (board[j][col] == player) {
                j++;
            } else {
                break;
            }
        }
        total = j - i - 1;
        System.out.println("total vertical: " + total);
        if (total >= 4) {
            return true;
        }
        // diagonal case
        // bottom right to top left case
        int[] tl = new int[]{row, col}; // bottom left
        while (tl[0] >= 0 && tl[1] >= 0) {
            if (board[tl[0]][tl[1]] == player) {
                tl[0]--;
                tl[1]--;
            } else {
                break;
            }
        }
        int[] br = new int[]{row, col}; // bottom right
        while (br[0] < this.row && br[1] < this.col) {
            if (board[br[0]][br[1]] == player) {
                br[0]++;
                br[1]++;
            } else {
                break;
            }
        }
        total = br[0] - tl[0] - 1;
        System.out.println("total bottom right to top left:" + total);
        if (total >= 4) {
            return true;
        }

        // top right to bottom left
        int[] tr = new int[]{row, col}; // top right
        while (tr[0] >= 0 && tr[1] < this.col) {
            if (board[tr[0]][tr[1]] == player) {
                tr[0]--;
                tr[1]++;
            } else {
                break;
            }
        }
        int[] bl = new int[]{row, col}; // bottom left
        while (bl[0] < this.row && bl[1] >= 0 ) {
            if (board[bl[0]][bl[1]] == player) {
                bl[0]++;
                bl[1]--;
            } else {
                break;
            }
        }
        total = tr[1] - bl[1] - 1;
        System.out.println("total top right to bottom left" + total);
        if (total >= 4) {
            return true;
        }

        return false;
    }

    private void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
