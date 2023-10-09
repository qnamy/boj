import java.io.*;
import java.util.*;

public class Boj1753 {
    private static final int INF = Integer.MAX_VALUE;

    public static void main (String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int k = Integer.parseInt(br.readLine());

            Map<Integer, LinkedList<Edge>> graph = new HashMap<>();

            while (e-- > 0) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                LinkedList<Edge> edges = graph.getOrDefault(start, new LinkedList<>());
                edges.add(new Edge(end, weight));

                graph.put(start, edges);
            }
            for (int n : solution(graph, v, k)) {
                if (n == INF) bw.write("INF\n");
                else bw.write(n + "\n");
            }

            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] solution (Map<Integer, LinkedList<Edge>> graph, int v, int k) {
        PriorityQueue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        boolean[] visited = new boolean[v];

        int[] dist = new int[v];
        Arrays.fill(dist, INF);

        dist[k-1] = 0;
        q.add(new Edge(k, 0));

        while (!q.isEmpty()) {
            Edge cur = q.poll();

            if (cur.w > dist[cur.v-1]) continue;

            if (visited[cur.v-1]) continue;
            visited[cur.v-1] = true;

            for (Edge edge : graph.getOrDefault(cur.v, new LinkedList<>())) {
                if (cur.w + edge.w > dist[edge.v-1]) continue;

                dist[edge.v-1] = cur.w + edge.w;
                q.add(new Edge(edge.v, dist[edge.v-1]));
            }
        }

        return dist;
    }

    private static class Edge {
        int v;
        int w;

        public int getWeight() {
            return this.w;
        }

        Edge (int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
