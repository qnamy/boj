import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj14938 {
    private static int n;
    private static int m;
    private static int[] items;
    private static ArrayList<ArrayList<Edge>> edges = new ArrayList<>();

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            items = Arrays
                    .stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int i = 0; i < n; i++) {
                edges.add(new ArrayList<>());
            }

            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());

                int v1 = Integer.parseInt(st.nextToken())-1;
                int v2 = Integer.parseInt(st.nextToken())-1;
                int dist = Integer.parseInt(st.nextToken());

                edges.get(v1).add(new Edge(v2, dist));
                edges.get(v2).add(new Edge(v1, dist));
            }

            bw.write(Integer.toString(solution()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution() {
        int max = 0;

        // 다익스트라 loop
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dijkstra(i));
        }

        // 최대값 return
        return max;
    }

    private static int dijkstra(int v) {
        // dijkstra
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n];

        // init
        pq.offer(new Edge(v, 0));
        dist[v] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (visited[cur.v]) continue;
            visited[cur.v] = true;

            for (Edge e : edges.get(cur.v)) {
                if (dist[cur.v] + e.dist > dist[e.v]) continue;

                dist[e.v] = dist[cur.v] + e.dist;
                pq.offer(new Edge(e.v, dist[cur.v] + e.dist));
            }
        }

        return getItemCount(dist);
    }

    private static int getItemCount(int[] dist) {
        int sum = 0;
        
        for (int i = 0; i < n; i++) {
            if (dist[i] <= m) sum += items[i];
        }

        return sum;
    }

    private static class Edge implements Comparable<Edge> {
        int v;
        int dist;

        Edge(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}
