import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class Boj1504 {
    public static void main (String[] args) {
        try (
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                OutputStreamWriter osw = new OutputStreamWriter(System.out);
                BufferedWriter bw = new BufferedWriter(osw)
        ) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            // 간선배열
            ArrayList<ArrayList<Edge>> graph = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            while (e-- > 0) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;
                int c = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Edge(b, c));
                graph.get(b).add(new Edge(a, c));
            }

            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken())-1;
            int v2 = Integer.parseInt(st.nextToken())-1;

            bw.write(solution(graph, n, v1, v2) + "");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Edge {
        int v;
        int dist;

        Edge (int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    private static int solution (ArrayList<ArrayList<Edge>> graph, int n, int v1, int v2) {
        final int INF = Integer.MAX_VALUE;

        // 1에서 v1, v2로 가는 경로 찾기
        int[] dist1 = findMinDist(graph, 0, n);
        // v1 -> v2, n
        int[] distV1 = findMinDist(graph, v1, n);
        // v2 -> v1, n
        int[] distV2 = findMinDist(graph, v2, n);

        int res1 = dist1[v1] == INF || distV1[v2] == INF || distV2[n-1] == INF ? -1 : dist1[v1] + distV1[v2] + distV2[n-1];
        int res2 = dist1[v2] == INF || distV2[v1] == INF || distV1[n-1] == INF ? -1 : dist1[v2] + distV2[v1] + distV1[n-1];

        return (res1 == -1 || res2 == -1) ? Math.max(res1, res2) : Math.min(res1, res2);
    }

    private static int[] findMinDist (ArrayList<ArrayList<Edge>> graph, int start, int n) {
        Queue<Edge> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        visited[start] = true;
        q.add(new Edge(start, 0));

        while (!q.isEmpty()) {
            Edge cur = q.poll();

            if (cur.dist > dist[cur.v]) continue;

            for (Edge e : graph.get(cur.v)) {
                if (dist[cur.v] + e.dist > dist[e.v]) continue;

                dist[e.v] = dist[cur.v] + e.dist;
                q.add(new Edge(e.v, dist[e.v]));
            }
        }

        return dist;
    }
}
