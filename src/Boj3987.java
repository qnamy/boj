import java.io.*;
import java.util.StringTokenizer;

public class Boj3987 {
    private static int n, m, pr, pc;
    private static char[][] grid;
    private static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                for (int j = 0; j < m; j++) {
                    grid[i][j] = line.charAt(j);
                }
            }

            st = new StringTokenizer(br.readLine());

            pr = Integer.parseInt(st.nextToken()) - 1;
            pc = Integer.parseInt(st.nextToken()) - 1;

            bw.write(solution());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution() {
        StringBuilder answer = new StringBuilder();

        final char[] direction = {'U', 'R', 'D', 'L'};

        int max = 0;
        char maxDir = 'U';

        for (int i = 0; i < direction.length; i++) {
            int count = getSignalCount(pr, pc, i);

            // max비교
            if (count > max) {
                max = count;
                maxDir = direction[i];

                if (max == MAX) break;  // 무한전파면 종료
            }
        }

        answer.append(maxDir).append("\n").append(max == MAX ? "Voyager" : max);

        return answer.toString();
    }

    private static int getSignalCount(int x, int y, int d) {
        final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        // 방향, x, y
        boolean[][][] visited = new boolean[4][n][m];

        int count = 0;
        while (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] != 'C') {
            // 무한 시그널
            if (visited[d][x][y]) return MAX;

            // count++
            visited[d][x][y] = true;
            count++;

            // 행성 방향 전환
            if (grid[x][y] == '/' || grid[x][y] == '\\') d = turn(d, grid[x][y]);

            // move
            x += dir[d][0];
            y += dir[d][1];
        }

        return count;
    }

    private static int turn(int dir, char planet){
        switch (planet) {
            case '/':
                if (dir == 0) return 1;
                else if (dir == 1) return 0;
                else if (dir == 2) return 3;
                else if (dir == 3) return 2;
            case '\\':
                if (dir == 0) return 3;
                else if (dir == 1) return 2;
                else if (dir == 2) return 1;
                else if (dir == 3) return 0;
        }
        return -1;
    }
}
