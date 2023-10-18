import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1932 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
            ) {
            int n = Integer.parseInt(br.readLine());

            int[][] triangle = new int[n][n];

            StringTokenizer st;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j <= i; j++) {
                    triangle[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write(solution(triangle) + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(final int[][] triangle) {
        final int n = triangle.length;

        // dp
        // dp[i][j] = min(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
        int[][] dp = new int[n][n];

        // dp 초기값
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) dp[i][j] = dp[i-1][j] + triangle[i][j];
                else if (j == i) dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                else dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
        }

        return Arrays.stream(dp[n-1]).max().orElse(0);
    }
}
