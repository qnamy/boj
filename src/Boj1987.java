import java.io.*;
import java.util.StringTokenizer;

public class Boj1987 {
    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    private static int r;
    private static int c;


    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            char[][] board = new char[r][c];

            for (int i = 0; i < r; i++) {
                String input = br.readLine();
                for (int j = 0; j < c; j++) {
                    board[i][j] = input.charAt(j);
                }
            }

            bw.write(solution(board)+"");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(char[][] board) {
        boolean[] visited = new boolean[26];
        return dfs(board, 0, 0, 0, visited);
    }

    private static int dfs(char[][] board, int x, int y, int count, boolean[] visited) {
        if (x < 0 || x >= r || y < 0 || y >= c || visited[board[x][y]-'A']) {
            return count;
        }

        visited[board[x][y]-'A'] = true;
        int max = 0;
        for (int[] d : DIR) {
            int nx = x + d[0];
            int ny = y + d[1];
            max = Math.max(max, dfs(board, nx, ny, count+1, visited));
        }
        visited[board[x][y]-'A'] = false;

        return max;
    }
}
