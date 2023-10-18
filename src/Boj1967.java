import java.io.*;
import java.util.*;

public class Boj1967 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            int n = Integer.parseInt(br.readLine());

            ArrayList<ArrayList<Edge>> edges = new ArrayList<>(n+1);
            for (int i = 0; i <= n; i++) {
                edges.add(new ArrayList<>());
            }

            StringTokenizer st;

            for (int i = 0; i < n-1; i++) {
                st = new StringTokenizer(br.readLine());

                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                edges.get(v1).add(new Edge(v2, w));
                edges.get(v2).add(new Edge(v1, w));
            }

            bw.write(solution(edges) + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution (ArrayList<ArrayList<Edge>> edges) {
        // 임의의 노드에서 가장 먼거리에 있는 노드 찾기
        int[] dist = bfs(edges, 1);

        int index = 0;
        int max = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] > max) index = i;
            max = Math.max(max, dist[i]);
        }

        // 찾은 노드에서 가장 먼거리 return
        dist = bfs(edges, index);
        max = 0;
        for (int i = 1; i < dist.length; i++) {
            max = Math.max(max, dist[i]);
        }

        return max;
    }

    private static int[] bfs (ArrayList<ArrayList<Edge>> edges, int start) {
        int[] dist = new int[edges.size()];

        Queue<Edge> q = new LinkedList<>();
        boolean[] visited = new boolean[edges.size()];

        dist[start] = 0;
        visited[start] = true;
        q.add(new Edge(start, 0));

        while (!q.isEmpty()) {
            Edge cur = q.poll();

            for (Edge e : edges.get(cur.v)) {
                if (visited[e.v]) continue;
                dist[e.v] = dist[cur.v] + e.w;
                visited[e.v] = true;
                q.add(new Edge(e.v, dist[e.v]));
            }
        }

        return dist;
    }

    private static class Edge {
        int v;
        int w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
