import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11053 {
    public static void main (String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());
            int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            bw.write(solution(a) + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int[] a) {
        final int n = a.length;
        // dp
        // i까지의 부분 수열
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            // dp[i] 본인 포함 최소 1
            dp[i] = 1;

            // 이전 값을 돌면서 현재값보다 작은 값이 있으면
            // dp값 업데이트
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        return Arrays.stream(dp).max().orElse(-1);
    }
}
