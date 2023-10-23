import java.io.*;
import java.util.StringTokenizer;

public class Boj9465 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {
            // 테스트 케이스
            int t = Integer.parseInt(br.readLine());

            while (t-- > 0) {
                int n = Integer.parseInt(br.readLine());

                int[][] stickers = new int[2][n];

                StringTokenizer st;

                for (int i = 0; i < 2; i++) {
                    st = new StringTokenizer(br.readLine());

                    for (int j = 0; j < n; j++) {
                        stickers[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                bw.write(solution(stickers) + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int[][] stickers) {
        int n = stickers[0].length;

        int[][] dp = new int[2][n];

        dp[0][0] = stickers[0][0];
        dp[1][0] = stickers[1][0];

        if (n > 1) {
            dp[0][1] = stickers[1][0] + stickers[0][1];
            dp[1][1] = stickers[0][0] + stickers[1][1];
        }

        for (int i = 2; i < n; i++) {
            dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + stickers[0][i];
            dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + stickers[1][i];
        }

        return Math.max(dp[0][n-1], dp[1][n-1]);
    }
}
