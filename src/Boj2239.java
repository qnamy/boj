import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj2239 {
    private static final int size = 9;
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int[][] board = new int[size][size];

            for (int i = 0; i < size; i++) {
                board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }

            bw.write(solution(board));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(int[][] board) {
        StringBuilder answer = new StringBuilder();

        // 0 좌표 구하기
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    points.add(new Point(i, j));
                }
            }
        }

        bfs(board, points, 0);

        for (int[] b : board) {
            for (int n : b) {
                answer.append(n);
            }
            answer.append("\n");
        }

        return answer.toString();
    }

    private static void bfs(int[][] board, ArrayList<Point> points, int index) {
        if (index >= points.size()) return;

        final int square = (int) Math.sqrt(size);
        boolean[] impossible = new boolean[size + 1];

        Point curPoint = points.get(index);
        int x = curPoint.x;
        int y = curPoint.y;

        // 현재 좌표에 행, 열, 사각형에 존재하는 숫자를 제외
        for (int i = 0; i < size; i++) {
            impossible[board[x][i]] = true;
            impossible[board[i][y]] = true;
        }

        int xIndex = x - (x % square);
        int yIndex = y - (y % square);
        for (int i = xIndex; i < xIndex + square; i++) {
            for (int j = yIndex; j < yIndex + square; j++) {
                impossible[board[i][j]] = true;
            }
        }

        Point last = points.get(points.size()-1);
        for (int i = 1; i <= size; i++) {
            if (impossible[i]) continue;
            board[x][y] = i;
            bfs(board, points, index+1);
            if (board[last.x][last.y] != 0) return;
            board[x][y] = 0;
        }
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
