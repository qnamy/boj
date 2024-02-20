import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj9466 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int t = Integer.parseInt(br.readLine());

            while (t-- > 0) {
                int n = Integer.parseInt(br.readLine());

                int[] s = new int[n+1];

                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 1; i <= n; i++) {
                    s[i] = Integer.parseInt(st.nextToken());
                }

                bw.write(n - solution(s) + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution(int[] s) {
        // 팀 학생수
        int count = 0;

        // 방문여부
        boolean[] checked = new boolean[s.length];
        boolean[] visited = new boolean[s.length];

        for (int i = 1; i < s.length; i++) {
            if (checked[i]) continue;

            // 팀 학생수 카운트
            count += dfs(s, i, checked, visited);
        }

        return count;
    }

    private static int dfs(int[] s, int index, boolean[] checked, boolean[] visited) {
        // 체크했던 학생 종료
        if (checked[index]) return 0;

        // 팀 학생수 카운트
        if (visited[index]) {
            int count = 1;
            int r = s[index];
            while (r != index) {
                count++;
                r = s[r];
            }
            return count;
        }

        visited[index] = true;
        int count = dfs(s, s[index], checked, visited);
        checked[index] = true;

        return count;
    }
}
