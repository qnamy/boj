import java.io.*;
import java.sql.Array;
import java.util.*;

public class Boj2638 {
    private static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    private static int n;
    private static int m;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            int[][] paper = new int[n][m];
            ArrayList<int[]> cheese = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < m; j++) {
                    paper[i][j] = Integer.parseInt(st.nextToken());
                    if (paper[i][j] == 1) cheese.add(new int[] {i, j});
                }
            }

            bw.write(solution(paper, cheese) + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int[][] paper, ArrayList<int[]> cheese) {
        // 외부공기 bfs
        boolean[][] visited = new boolean[n][m];
        bfs(paper, visited, 0, 0);

        // 녹는 치즈 찾기
        int time = 0;
        while (!cheese.isEmpty()) {
            ArrayList<int[]> meltingCheese = getMelitingCheese(paper, cheese);

            // 치즈를 녹이고 외부공기로 바꾼다.
            for (int[] m : meltingCheese) {
                int x = m[0];
                int y = m[1];

                bfs(paper, visited, x, y);
            }

            time++;
        }

        return time;
    }

    private static void bfs(int[][] paper, boolean[][] visited, int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        visited[x][y] = true;
        paper[x][y] = 2;
        q.add(new int[] {x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] d : dir) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || paper[nx][ny] == 1) continue;

                // 외부공기의 값을 2로 변경
                visited[nx][ny] = true;
                paper[nx][ny] = 2;
                q.add(new int[] {nx, ny});
            }
        }
    }

    private static ArrayList<int[]> getMelitingCheese(int[][] paper, ArrayList<int[]> cheese) {
        ArrayList<int[]> meltingCheese = new ArrayList<>();

        int index = 0;
        while (index < cheese.size()) {
            int[] cur = cheese.get(index);

            int x = cur[0];
            int y = cur[1];

            int count = 0;
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (paper[nx][ny] == 2) count++;
            }

            if (count > 1) {
                meltingCheese.add(new int[] {x, y});
                cheese.remove(cur);
            } else {
                index++;
            }
        }

        return meltingCheese;
    }
}
