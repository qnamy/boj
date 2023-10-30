import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15663 {
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
        int[] used = new int[m];
        boolean[] visited = new boolean[nums.length];
        return makeSequence(nums, m, 0, used, visited);
    }

    private static String makeSequence(int[] nums, int m, int count, int[] used, boolean[] visited) {
        if (count == m) {
            StringBuilder sb = new StringBuilder();
            for (int n : used) {
                sb.append(n).append(" ");
            }
            return sb.append("\n").toString();
        }

        StringBuilder result = new StringBuilder();
        // 같은 자리에 동일한 숫자를 사용하지 못하게 해서 중복 수열 제거
        int prevNum = -1;

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || prevNum == nums[i]) continue;
            visited[i] = true;
            prevNum = nums[i];
            used[count] = nums[i];
            result.append(makeSequence(nums, m, count+1, used, visited));
            visited[i] = false;
        }

        return result.toString();
    }
}
