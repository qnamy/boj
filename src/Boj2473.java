import java.io.*;
import java.util.Arrays;

public class Boj2473 {
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

        Arrays.sort(solutions);

        long min = Long.MAX_VALUE;
        for (int i = 0; i < solutions.length - 2; i++) {
            int left = i + 1;
            int right = solutions.length - 1;

            while (left < right) {
                long sum = (long) solutions[i] + solutions[left] + solutions[right];

                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    answer.setLength(0);
                    answer.append(solutions[i]).append(" ")
                            .append(solutions[left]).append(" ")
                            .append(solutions[right]);
                }

                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    break;
                }
            }

            if (min == 0) break;
        }

        return answer.toString();
    }
}
