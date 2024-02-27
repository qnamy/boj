import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2098 {
    private static int[][] dp;
    private static int MAX_MASK;
    private static final int MAX_CITY = 16;
    private static final int MAX_COST = 1_000_000;
    private static final int MAX_VALUE = MAX_CITY * MAX_COST + 1;   // 최대값
    private static final int INF = Integer.MAX_VALUE;
    private static final int START = 0; // 시작 도시

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());
            int[][] w = new int[n][n];
            MAX_MASK = (1 << n) - 1;

            StringTokenizer st;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    w[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write(Integer.toString(solution(w)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution(int[][] w) {
        dp = new int[w.length][MAX_MASK];

        return dfs(START, 1 << START, w);
    }

    private static int dfs(int city, int bitmask, int[][] w) {
        if (bitmask == MAX_MASK) {
            // START 도시로 돌아올 수 없으면 return MAX
            return w[city][START] == 0 ? MAX_VALUE : w[city][START];
        }

        if (dp[city][bitmask] != 0) {
            return dp[city][bitmask];
        }

        dp[city][bitmask] = MAX_VALUE;
        for (int i = 0; i < w.length; i++) {
            // i번 도시로 갈 수 없거나 방문했으면 다음 도시 확인
            if (w[city][i] == 0 || (bitmask & (1 << i)) != 0) continue;

            // 남은 도시 방문 비용 구하기
            int next = dfs(i, bitmask | (1 << i), w);
            dp[city][bitmask] = Math.min(dp[city][bitmask], next + w[city][i]);
        }

        return dp[city][bitmask];
    }
}
