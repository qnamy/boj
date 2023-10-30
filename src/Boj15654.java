import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15654 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] nums = new int[n];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(nums);

            bw.write(solution(nums, m));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(int[] nums, int m) {
        StringBuilder answer = new StringBuilder();

        int[] used = new int[m];
        boolean[] visited = new boolean[nums.length];
        answer.append(makeSequence(nums, m, 0, used, visited));

        return answer.toString();
    }

    private static String makeSequence(int[] nums, int m, int count, int[] used, boolean[] visited) {
        if (count == m) {
            StringBuilder sb = new StringBuilder();
            for (int i : used) {
                sb.append(nums[i]).append(" ");
            }
            return sb.append("\n").toString();
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < nums.length; i++) {
            // 사용한 index 제외
            if (visited[i]) continue;
            visited[i] = true;
            used[count] = i;
            result.append(makeSequence(nums, m, count+1, used, visited));
            visited[i] = false;
        }

        return result.toString();
    }
}
