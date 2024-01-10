import java.io.*;
import java.util.*;

public class Boj14502 {
    private static int n;
    private static int m;
    private static int[][] map;
    private static ArrayList<Point> virusList = new ArrayList<>();
    private static ArrayList<Point> emptyList = new ArrayList<>();
    private static final int WALLS = 3;
    private static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new int[n][m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 0) emptyList.add(new Point(i, j));
                    else if (map[i][j] == 2) virusList.add(new Point(i, j));
                }
            }

            bw.write(Integer.toString(solution()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution() {
        // 벽을 3개 dfs
        return dfs(0, 0);
    }

    private static int dfs(int walls, int idx) {
        // 벽 3개 바이러스 bfs
        if (walls == WALLS) {
            return bfs();
        }

        int max = 0;
        for (int i = idx; i < emptyList.size(); i++) {
            Point cur = emptyList.get(i);
            map[cur.x][cur.y] = 1;
            max = Math.max(max, dfs(walls+1, i+1));
            map[cur.x][cur.y] = 0;
        }

        return max;
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>(virusList);

        // clone map
        int[][] cloneMap = cloneMap();
        boolean[][] visited = new boolean[n][m];

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int[] d : DIR) {
                int nx = cur.x + d[0];
                int ny = cur.y + d[1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || cloneMap[nx][ny] != 0) continue;

                cloneMap[nx][ny] = 2;
                visited[nx][ny] = true;
                q.offer(new Point(nx, ny));
            }
        }

        return countSafeArea(cloneMap);
    }

    private static int[][] cloneMap() {
        int[][] newMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            newMap[i] = Arrays.copyOf(map[i], m);
        }

        return newMap;
    }

    private static int countSafeArea(int[][] resMap) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (resMap[i][j] == 0) count++;
            }
        }
        return count;
    }

    private static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
