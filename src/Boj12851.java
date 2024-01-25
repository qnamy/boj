import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12851 {
    private static int n;
    private static int k;
    private static final int MAX = 100_000;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            bw.write(solution());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String solution() {
        StringBuilder answer = new StringBuilder();

        int min = Integer.MAX_VALUE;
        int count = 0;

        // bfs
        Queue<Integer> q = new LinkedList<>();
        int[] time = new int[MAX+1];

        // init
        q.offer(n);
        time[n] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (time[cur] > min) break;

            if (cur == k) {
                min = time[cur];
                count++;
            }

            for (int i = 0; i < 3; i++) {
                int nx;

                if (i == 0) nx = cur-1;
                else if (i == 1) nx = cur+1;
                else nx = cur*2;

                if (nx < 0 || nx > MAX) continue;

                if (time[nx] == 0 || time[nx] == time[cur]+1) {
                    time[nx] = time[cur]+1;
                    q.offer(nx);
                }
            }
        }

        return answer.append(min).append("\n").append(count).toString();
    }
}
