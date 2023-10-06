import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1238 {
    private static int x;
    private static int[][] road;
    private static int[][] revRoad;

    public static void main(String[] args) {
        try (
                OutputStreamWriter osw = new OutputStreamWriter(System.out);
                BufferedWriter bw = new BufferedWriter(osw)
        ) {

            input();

            bw.write(findMaxTime() + "");

            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findMaxTime() {
        int n = road.length;

        int[] dist = findTimes(road);
        int[] revDist = findTimes(revRoad);
        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, dist[i] + revDist[i]);
        }

        return max;
    }

    private static int[] findTimes(int[][] road) {
        int n = road.length;

        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        // x에서 시작
        result[x-1] = 0;
        visited[x-1] = true;
        q.add(x-1);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < n; i++) {
                // i로 가는 길이 없으면 계산하지 않고 넘어간다.
                if (road[cur][i] == 0) continue;

                result[i] = Math.min(result[i], result[cur] + road[cur][i]);

                for (int j = 0; j < n; j++) {
                    if (road[i][j] == 0) continue;

                    result[j] = Math.min(result[j], result[i] + road[i][j]);
                }

                if (visited[i]) continue;
                visited[i] = true;
                q.add(i);
            }
        }

        return result;
    }

    private static void input() {
        try (
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr)
        ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            road = new int[n][n];
            revRoad = new int[n][n];

            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                road[s-1][e-1] = d;
                revRoad[e-1][s-1] = d;
            }

        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}