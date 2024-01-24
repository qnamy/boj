import java.io.*;
import java.util.Arrays;

public class Boj17070 {
    private static int n;
    private static int house[][];

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            n = Integer.parseInt(br.readLine());
            house = new int[n][n];

            for (int i = 0; i < n; i++) {
                house[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            bw.write(Integer.toString(solution()));

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution() {
        // [r][c][status]
        // status
        // 0 : 가로
        // 1 : 세로
        // 2 : 대각선
        int[][][] dp = new int[n][n][3];

        // 초기 상태
        dp[0][1][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if (house[i][j] == 1) continue;

                // 가로
                dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
                // 세로
                if (i > 0) dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
                // 대각선
                if (i > 0 && house[i-1][j] == 0 && house[i][j-1] == 0) dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
            }
        }

        return Arrays.stream(dp[n-1][n-1]).sum();
    }
}
