import java.io.*;
import java.util.*;

public class Boj1865 {
    public static void main (String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {
            int tc = Integer.parseInt(br.readLine());

            while (tc-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                Map<Integer, LinkedList<Edge>> graph = new HashMap<>();

                // 도로
                for (int i = 0; i < m; i++) {
                    st = new StringTokenizer(br.readLine());

                    int s = Integer.parseInt(st.nextToken());
                    int e = Integer.parseInt(st.nextToken());
                    int t = Integer.parseInt(st.nextToken());

                    LinkedList<Edge> edges1 = graph.getOrDefault(s, new LinkedList<>());
                    LinkedList<Edge> edges2 = graph.getOrDefault(e, new LinkedList<>());

                    edges1.add(new Edge(e, t));
                    edges2.add(new Edge(s, t));

                    graph.put(s, edges1);
                    graph.put(e, edges2);
                }

                // 웜홀
                for (int i = 0; i < w; i++) {
                    st = new StringTokenizer(br.readLine());

                    int s = Integer.parseInt(st.nextToken());
                    int e = Integer.parseInt(st.nextToken());
                    int t = Integer.parseInt(st.nextToken());

                    LinkedList<Edge> edges = graph.getOrDefault(s, new LinkedList<>());

                    edges.add(new Edge(e, -t));

                    graph.put(s, edges);
                }

                bw.write(solution(n, graph));
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution (int n, Map<Integer, LinkedList<Edge>> graph) {
        int[] times = new int[n];

        for (int i = 1; i <= n; i++) {
            for (int k : graph.keySet()) {
                LinkedList<Edge> edges = graph.get(k);

                for (Edge e : edges) {
                    if (times[e.v-1] > times[k-1] + e.w) {
                        times[e.v-1] = times[k-1] + e.w;

                        if (i == n) return "YES";
                    }
                }
            }
        }

        return "NO";
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
