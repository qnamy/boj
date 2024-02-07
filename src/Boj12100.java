import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj12100 {
    private static final int MAX = 5; // 최대 5번
    private static int n;

    private enum Dir {
        UP, DOWN, LEFT, RIGHT;
    }

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            n = Integer.parseInt(br.readLine());

            int[][] board = new int[n][n];

            for (int i = 0; i < n; i++) {
                board[i] = Arrays
                        .stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            bw.write(Integer.toString(solution(board)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution(int[][] board) {
        return backTracking(board, 0);
    }

    private static int backTracking(int[][] board, int depth) {
        if (depth == MAX) return getMax(board);

        int max = 0;
        for (Dir dir : Dir.values()) {
            int[][] copyBoard = copyBoard(board);
            boolean isMove = false;

            switch (dir) {
                case UP:
                    isMove = moveUp(copyBoard);
                    break;
                case DOWN:
                    isMove = moveDown(copyBoard);
                    break;
                case LEFT:
                    isMove = moveLeft(copyBoard);
                    break;
                case RIGHT:
                    isMove = moveRight(copyBoard);
                    break;
            }

            if (isMove) max = Math.max(max, backTracking(copyBoard, depth + 1));
            else max = Math.max(max, getMax(copyBoard));
        }

        return max;
    }

    private static boolean moveUp(int[][] board) {
        boolean isMove = false;

        for (int j = 0; j < n; j++) {
            int combinableRow = 0;
            for (int i = 1; i < n; i++) {
                if (board[i][j] == 0) continue;

                int curRow = i;
                while (curRow > combinableRow) {
                    if (board[curRow-1][j] == 0) {
                        isMove = true;
                        board[curRow-1][j] += board[curRow][j];
                        board[curRow][j] = 0;
                        curRow--;
                    } else if (board[curRow-1][j] == board[curRow][j]) {
                        isMove = true;
                        board[curRow-1][j] += board[curRow][j];
                        board[curRow][j] = 0;
                        combinableRow++;
                        break;
                    } else {
                        combinableRow++;
                        break;
                    }
                }
            }
        }

        return isMove;
    }

    private static boolean moveDown(int[][] board) {
        boolean isMove = false;

        for (int j = 0; j < n; j++) {
            int combinableRow = n-1;
            for (int i = n-2; i >= 0; i--) {
                if (board[i][j] == 0) continue;

                int curRow = i;
                while (curRow < combinableRow) {
                    if (board[curRow+1][j] == 0) {
                        isMove = true;
                        board[curRow+1][j] += board[curRow][j];
                        board[curRow][j] = 0;
                        curRow++;
                    } else if (board[curRow+1][j] == board[curRow][j]) {
                        isMove = true;
                        board[curRow+1][j] += board[curRow][j];
                        board[curRow][j] = 0;
                        combinableRow--;
                        break;
                    } else {
                        combinableRow--;
                        break;
                    }
                }
            }
        }

        return isMove;
    }

    private static boolean moveLeft(int[][] board) {
        boolean isMove = false;

        for (int i = 0; i < n; i++) {
            int combinableCol = 0;
            for (int j = 1; j < n; j++) {
                if (board[i][j] == 0) continue;

                int curCol = j;
                while (curCol > combinableCol) {
                    if (board[i][curCol-1] == 0) {
                        isMove = true;
                        board[i][curCol-1] += board[i][curCol];
                        board[i][curCol] = 0;
                        curCol--;
                    } else if (board[i][curCol-1] == board[i][curCol]) {
                        isMove = true;
                        board[i][curCol-1] += board[i][curCol];
                        board[i][curCol] = 0;
                        combinableCol++;
                        break;
                    } else {
                        combinableCol++;
                        break;
                    }
                }
            }
        }

        return isMove;
    }

    private static boolean moveRight(int[][] board) {
        boolean isMove = false;

        for (int i = 0; i < n; i++) {
            int combinableCol = n-1;
            for (int j = n-2; j >= 0; j--) {
                if (board[i][j] == 0) continue;

                int curCol = j;
                while (curCol < combinableCol) {
                    if (board[i][curCol+1] == 0) {
                        isMove = true;
                        board[i][curCol+1] += board[i][curCol];
                        board[i][curCol] = 0;
                        curCol++;
                    } else if (board[i][curCol+1] == board[i][curCol]) {
                        isMove = true;
                        board[i][curCol+1] += board[i][curCol];
                        board[i][curCol] = 0;
                        combinableCol--;
                        break;
                    } else {
                        combinableCol--;
                        break;
                    }
                }
            }
        }

        return isMove;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[n][n];

        for (int i = 0; i < n; i++) {
            newBoard[i] = Arrays.copyOf(board[i], n);
        }

        return newBoard;
    }

    private static int getMax(int[][] board) {
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, board[i][j]);
            }
        }

        return max;
    }
}
