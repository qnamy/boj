import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Boj11054 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());
            int[] array = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            bw.write(String.valueOf(solution(n, array)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int n, int[] array) {
        // 수열의 첫번째자리를 기준으로 오른쪽으로 오름차순
        // 수열의 마지막자리를 기준으로 왼쪽으로 오름차순
        int[][] dp = new int[n][2];

        // 초기값
        dp[0][0] = 1;
        dp[n-1][1] = 1;

        for (int i = 1; i < n; i++) {
            int r = (n-1)-i;    // 오른쪽 인덱스
            dp[i][0] = 1;
            dp[r][1] = 1;

            for (int j = 1; j <= i; j++) {
                if (array[i] > array[i-j]) {
                    dp[i][0] = Math.max(dp[i][0], dp[i-j][0] + 1);
                }

                if (array[r] > array[r+j]) {
                    dp[r][1] = Math.max(dp[r][1], dp[r+j][1] + 1);
                }
            }
        }

        return Arrays.stream(dp).mapToInt(row -> row[0] + row[1]).max().getAsInt() - 1;
    }
}
