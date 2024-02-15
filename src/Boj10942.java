import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj10942 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {

            int n = Integer.parseInt(br.readLine());

            int[] nums = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            int m = Integer.parseInt(br.readLine());

            int[][] questions = new int[m][2];

            for (int i = 0; i < m; i++) {
                questions[i] = Arrays
                        .stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            for (int answer : solution(nums, questions)) {
                bw.write(answer + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int[] solution(int[] nums, int[][] questions) {
        boolean[][] dp = new boolean[nums.length][nums.length];

        // dp[s][e] = nums[s] == nums[e] && dp[s+1][e-1]
        for (int len = 0; len < dp.length; len++) {
            for (int s = 0; s < dp.length - len; s++) {
                int e = s + len;
                // 길이 1~2
                if (len <= 1) {
                    dp[s][e] = nums[s] == nums[e];
                }
                // 길이 3이상
                else {
                    dp[s][e] = nums[s] == nums[e] && dp[s + 1][e - 1];
                }
            }
        }

        // answer
        int[] answer = new int[questions.length];
        for (int i = 0; i < questions.length; i++) {
            int s = questions[i][0] - 1;
            int e = questions[i][1] - 1;

            answer[i] = dp[s][e] ? 1 : 0;
        }

        return answer;
    }
}
