import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj1897 {
    private static int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            char[][] board = new char[r][c];

            for (int i = 0; i < r; i++) {
                String input = br.readLine();

                for (int j = 0; j < c; j++) {
                    board[i][j] = input.charAt(j);
                }
            }

            bw.write(solution(board));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(char[][] board) {
        boolean[] visited = new boolean[26];

        return Integer.toString(dfs(board, 0, 0, visited, 0));
    }

    private static int dfs(char[][] board, int x, int y, boolean[] visited, int count) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[x].length || visited[board[x][y]-'A']) {
            return count;
        }

        int result = 0;
        visited[board[x][y]-'A'] = true;
        for (int[] d : DIR) {
            int nx = x + d[0];
            int ny = y + d[1];
            result = Math.max(result, dfs(board, nx, ny, visited, count+1));
        }
        visited[board[x][y]-'A'] = false;

        return result;
    }
}
