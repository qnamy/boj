import java.io.*;
import java.util.Arrays;

public class Boj2467 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());
            int[] solutions = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            bw.write(solution(solutions));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String solution(int[] solutions) {
        StringBuilder answer = new StringBuilder();
        int max = Integer.MAX_VALUE;

        for (int i = 0; i < solutions.length - 1; i++) {
            int l = i + 1;
            int r = solutions.length - 1;

            while (l <= r) {
                int mid = (l + r) / 2;
                int sum = solutions[i] + solutions[mid];

                if (Math.abs(sum) <= max) {
                    answer.setLength(0);
                    answer.append(solutions[i]).append(" ").append(solutions[mid]);
                    max = Math.abs(sum);
                }

                if (sum < 0) l = mid + 1;
                else r = mid - 1;
            }
        }

        return answer.toString();
    }
}
