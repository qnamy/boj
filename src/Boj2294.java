import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2294 {
    private static final int MAX = Integer.MAX_VALUE-1;
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] coins = new int[n];

            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(br.readLine());
            }

            bw.write(Integer.toString(solution(k, coins)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int k, int[] coins) {
        int[] dp = new int[k+1];

        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for (int coin : coins) {
            for (int j = coin; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j-coin] + 1);
            }
        }

        return dp[k] == MAX ? -1 : dp[k];
    }
}
