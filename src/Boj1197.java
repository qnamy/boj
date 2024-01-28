import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1197 {
    private static int[] parent;
    private static PriorityQueue<Edge> edges = new PriorityQueue<>();

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            parent = new int[v+1];
            for (int i = 1; i <= v; i++) {
                parent[i] = i;
            }

            while (e-- > 0) {
                st = new StringTokenizer(br.readLine());

                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                if (v1 < v2) edges.offer(new Edge(v1, v2, weight));
                else edges.offer(new Edge(v2, v1, weight));
            }

            bw.write(Integer.toString(solution()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution() {
        int answer = 0;

        while (!edges.isEmpty()) {
            Edge e = edges.poll();

            int from = findRoot(e.from);
            int to = findRoot(e.to);

            if (from == to) continue;

            // 각 정점의 root가 다를 경우 간선을 선택한다.
            // 선택된 간선의 각 root를 연결해 하나의 트리로 만든다.
            parent[to] = from;
            answer += e.weight;
        }

        return answer;
    }

    private static int findRoot(int v) {
        if (v == parent[v]) return v;
        return findRoot(parent[v]);
    }

    private static void unionTree(int from, int to) {
        parent[to] = from;
    }

    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
