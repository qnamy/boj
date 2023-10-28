import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13549 {
    private static final int max = 100000;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 수빈위치
            int n = Integer.parseInt(st.nextToken());

            // 동생위치
            int k = Integer.parseInt(st.nextToken());

            bw.write(solution(n, k) + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int n, int k) {
        // bfs
        Queue<int[]> q = new LinkedList<>();
        // 시간초과 이동했던 위치 재이동 제외
        boolean[] visited = new boolean[max+1];

        // 수빈이 초기 위치 [위치, 시간]
        q.add(new int[] {n, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            // 동생을 찾으면 종료
            if (cur[0] == k) return cur[1];

            // 방문여부체크
            visited[cur[0]] = true;

            // 순간이동
            if (cur[0] * 2 <= max && !visited[cur[0]*2]) q.add(new int[] {cur[0]*2, cur[1]});
            // x-1, x+1 이동
            if (cur[0] - 1 >= 0 && !visited[cur[0]-1]) q.add(new int[] {cur[0]-1, cur[1]+1});
            if (cur[0] + 1 <= max && !visited[cur[0]+1]) q.add(new int[] {cur[0]+1, cur[1]+1});
        }

        return 0;
    }
}
