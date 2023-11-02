import java.io.*;
import java.util.*;

public class Boj1005 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int t = Integer.parseInt(br.readLine());

            StringTokenizer st;

            while (t-- > 0) {
                st = new StringTokenizer(br.readLine());

                int n = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                int[] time = new int[n+1];
                int[] indegree = new int[n+1];
                Map<Integer, ArrayList<Integer>> buildOrder = new HashMap<>();

                st = new StringTokenizer(br.readLine());
                for (int i = 1; i <= n; i++) {
                    time[i] = Integer.parseInt(st.nextToken());
                }

                while (k-- > 0) {
                    st = new StringTokenizer(br.readLine());

                    int building = Integer.parseInt(st.nextToken());
                    int next = Integer.parseInt(st.nextToken());

                    ArrayList<Integer> list = buildOrder.getOrDefault(building, new ArrayList<>());
                    list.add(next);

                    buildOrder.put(building, list);
                    indegree[next]++;
                }

                int w = Integer.parseInt(br.readLine());

                bw.write(solution(time, indegree, buildOrder, w)+"\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int[] time, int[] indegree, Map<Integer, ArrayList<Integer>> buildOrder, int w) {
        int[] dp = new int[time.length];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < time.length; i++) {
            dp[i] = time[i];
            if (indegree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : buildOrder.getOrDefault(cur, new ArrayList<>())) {
                dp[next] = Math.max(dp[next], dp[cur]+time[next]);
                indegree[next]--;
                if (indegree[next] == 0) q.add(next);
            }
        }

        return dp[w];
    }
}
