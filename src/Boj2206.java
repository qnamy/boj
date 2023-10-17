import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2206 {

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
            ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] map = new int[n][m];

            for (int i = 0; i < n; i++) {
                String str = br.readLine();

                for (int j = 0; j < m; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            bw.write(solution(map)+"");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int[][] map) {
        final int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        final int n = map.length;
        final int m = map[0].length;

        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2];

        // 시작 0, 0
        visited[0][0][0] = true;
        q.add(new Point(0, 0, 1, false));

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int cnt = cur.cnt;
            boolean destoryed = cur.destroyed;

            if (x == n-1 && y == m-1) return cnt;

            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if (map[nx][ny] == 0 && !visited[nx][ny][destoryed ? 1 : 0]) {
                    visited[nx][ny][destoryed ? 1 : 0] = true;
                    q.add(new Point(nx, ny, cnt+1, destoryed));
                }

                if (map[nx][ny] == 1 && !destoryed) {
                    visited[nx][ny][1] = true;
                    q.add(new Point(nx, ny, cnt+1, true));
                }
            }
        }

        return -1;
    }

    private static class Point {
        int x;
        int y;
        int cnt;
        boolean destroyed;

        Point(int x, int y, int cnt, boolean destroyed) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.destroyed = destroyed;
        }
    }
}
