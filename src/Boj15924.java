import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15924 {
    private static int n, m;
    private static char[][] map;
    private static final int MOD = 1_000_000_009;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            map = new char[n][m];

            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = input.charAt(j);
                }
            }

            bw.write(Integer.toString(solution()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution() {
        // dp
        int[][] dp = new int[n][m];

        for (int i = n-1; i >= 0; i--) {
            for (int j = m-1; j >= 0; j--) {
                switch (map[i][j]) {
                    case 'X':
                        dp[i][j] = 1;
                        break;
                    case 'E':
                        if (j + 1 < m) dp[i][j] = dp[i][j+1];
                        break;
                    case 'S':
                        if (i + 1 < n) dp[i][j] = dp[i+1][j];
                        break;
                    case 'B':
                        dp[i][j] = ((j+1 < m ? dp[i][j+1] : 0) + (i+1 < n ? dp[i+1][j] : 0)) % MOD;
                        break;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                answer = (answer + dp[i][j]) % MOD;
            }
        }

        return answer;
    }
}
