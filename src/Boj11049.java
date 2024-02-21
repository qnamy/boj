import java.io.*;
import java.util.Arrays;

public class Boj11049 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());

            int[][] matrix = new int[n][2];

            for (int i = 0; i < n; i++) {
                matrix[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            bw.write(Integer.toString(solution(matrix)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix.length];

        for (int len = 1; len < matrix.length; len++) {
            for (int start = 0; start + len < matrix.length; start++) {
                int end = start + len;

                int min = Integer.MAX_VALUE;

                for (int i = start; i < end; i++) {
                    int multiple = dp[start][i] + dp[i+1][end] + matrix[start][0] * matrix[i][1] * matrix[end][1];
                    min = Math.min(min, multiple);
                }

                dp[start][end] = min;
            }
        }

        return dp[0][matrix.length-1];
    }
}
