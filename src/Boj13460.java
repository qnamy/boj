import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13460 {
    private static final char EMPTY = '.';
    private static final char WALL = '#';
    private static final char HALL = 'O';
    private static final int[][] DIR = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private static final int MAX = 10;

    private static int n;
    private static int m;
    private static char[][] board;

    private static class Ball {
        int x, y;

        Ball(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Ball copyBall(Ball b) {
            return new Ball(b.x, b.y);
        }

        public void moveBall(int x, int y) {
            this.x += x;
            this.y += y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Ball) {
                Ball b = (Ball) obj;
                return this.x == b.x && this.y == b.y;
            } else {
                return false;
            }
        }
    }

    private static class Status {
        Ball red;
        Ball blue;
        int count;

        Status(Ball red, Ball blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {


            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            board = new char[n][m];
            Ball red = null;
            Ball blue = null;

            for (int i = 0; i < n; i++) {
                String input = br.readLine();

                for (int j = 0; j < m; j++) {
                    board[i][j] = input.charAt(j);

                    if (board[i][j] == 'R') {
                        red = new Ball(i, j);
                        board[i][j] = EMPTY;
                    } else if (board[i][j] == 'B') {
                        blue = new Ball(i, j);
                        board[i][j] = EMPTY;
                    }
                }
            }

            bw.write(Integer.toString(solution(red, blue)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution(Ball red, Ball blue) {
        // bfs
        Queue<Status> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][m][n][m];

        // init
        q.offer(new Status(red, blue, 0));
        visited[red.x][red.y][blue.x][blue.y] = true;

        while (!q.isEmpty()) {
            Status cur = q.poll();

            Ball curRed = cur.red;
            Ball curBlue = cur.blue;

            if (cur.count + 1 > MAX) break;

            for (int[] dir : DIR) {
                Ball newRed = Ball.copyBall(curRed);
                Ball newBlue = Ball.copyBall(curBlue);

                boolean isThroughBlue = rollBall(dir, newRed, newBlue);
                boolean isThroughRed = rollBall(dir, newBlue, newRed);

                if (board[newBlue.x][newBlue.y] == HALL) continue;
                if (board[newRed.x][newRed.y] == HALL) return cur.count + 1;

                if (isThroughBlue) newRed.moveBall(-dir[0], -dir[1]);
                else if (isThroughRed) newBlue.moveBall(-dir[0], -dir[1]);

                if (visited[newRed.x][newRed.y][newBlue.x][newBlue.y]) continue;
                visited[newRed.x][newRed.y][newBlue.x][newBlue.y] = true;
                q.offer(new Status(newRed, newBlue, cur.count + 1));
            }
        }

        return -1;
    }

    private static boolean rollBall(int[] dir, Ball ball, Ball otherBall) {
        boolean isThroughBall = false;
        while (isMovable(ball.x + dir[0], ball.y + dir[1])) {
            ball.moveBall(dir[0], dir[1]);

            if (ball.equals(otherBall)) isThroughBall = true;
            if (board[ball.x][ball.y] == HALL) break;
        }
        return isThroughBall;
    }

    private static boolean isMovable(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] != WALL;
    }
}
