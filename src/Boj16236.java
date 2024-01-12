import java.io.*;
import java.util.*;

public class Boj16236 {
    private static final int[][] DIR = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private static int n;
    private static int[][] space;
    private static Shark shark;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            n = Integer.parseInt(br.readLine());
            space = new int[n][n];

            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    int input = Integer.parseInt(st.nextToken());

                    if (input == 9) {
                        shark = new Shark(i, j);
                    } else {
                        space[i][j] = input;
                    }
                }
            }

            bw.write(Integer.toString(solution()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution() {
        while (true) {
            if (!dfs()) break;
        }

        return shark.moveCount;
    }

    private static boolean dfs() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];

        // 초기값
        pq.offer(new Point(shark.x, shark.y, 0));
        visited[shark.x][shark.y] = true;

        while (!pq.isEmpty()) {
            Point cur = pq.poll();

            int x = cur.x;
            int y = cur.y;
            int count = cur.count;

            // 먹기
            if (space[x][y] > 0 && space[x][y] < shark.size) {
                space[x][y] = 0;
                shark.eatFish(x, y, count);
                return true;
            }

            for (int[] d : DIR) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || space[nx][ny] > shark.size) continue;

                visited[nx][ny] = true;
                pq.offer(new Point(nx, ny, count + 1));
            }
        }

        return false;
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            return this.count == o.count
                    ? (this.x == o.x ? this.y - o.y : this.x - o.x)
                    : this.count - o.count;
        }
    }

    private static class Shark {
        int x;
        int y;
        int size = 2;
        int eatCount = 0;
        int moveCount = 0;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void eatFish(int x, int y, int count) {
            this.x = x;
            this.y = y;
            moveCount += count;

            eatCount++;

            if (eatCount == size) {
                size++;
                eatCount = 0;
            }
        }
    }
}
