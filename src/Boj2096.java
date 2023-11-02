import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2096 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());
            int[][] lines = new int[n][3];

            for (int i = 0; i < n; i++) {
                lines[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            bw.write(solution(lines));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(int[][] lines) {
        final int n = lines.length;

        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[n][3];

        dp[0][0] = lines[0][0];
        dp[0][1] = lines[0][1];
        dp[0][2] = lines[0][2];

        sb.append(getMax(lines, dp, n)).append(" ").append(getMin(lines, dp, n));

        return sb.toString();
    }

    private static int getMax(int[][] lines, int[][] dp, int n) {
        for (int i = 1; i < n; i++) {
            int max_0_1 = Math.max(dp[i-1][0], dp[i-1][1]);
            int max_1_2 = Math.max(dp[i-1][1], dp[i-1][2]);
            dp[i][0] = max_0_1 + lines[i][0];
            dp[i][1] = Math.max(max_0_1, max_1_2) + lines[i][1];
            dp[i][2] = max_1_2 + lines[i][2];
        }

        return Arrays.stream(dp[n-1]).max().orElse(0);
    }

    private static int getMin(int[][] lines, int[][] dp, int n) {
        for (int i = 1; i < n; i++) {
            int min_0_1 = Math.min(dp[i-1][0], dp[i-1][1]);
            int min_1_2 = Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][0] = min_0_1 + lines[i][0];
            dp[i][1] = Math.min(min_0_1, min_1_2) + lines[i][1];
            dp[i][2] = min_1_2 + lines[i][2];
        }

        return Arrays.stream(dp[n-1]).min().orElse(0);
    }
}
