import java.io.*;
import java.util.*;

public class Boj11779 {
    private static final int MAX = Integer.MAX_VALUE;
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());

            Map<Integer, ArrayList<Edge>> edgeMap = new HashMap<>();

            StringTokenizer st;
            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int destination = Integer.parseInt(st.nextToken());
                int expense = Integer.parseInt(st.nextToken());

                ArrayList<Edge> edgeList = edgeMap.getOrDefault(start, new ArrayList<>());

                edgeList.add(new Edge(destination, expense));

                edgeMap.put(start, edgeList);
            }

            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());

            bw.write(solution(n, start, destination, edgeMap));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(int n, int start, int destination, Map<Integer, ArrayList<Edge>> edgeMap) {
        StringBuilder answer = new StringBuilder();

        int[] expenses = new int[n+1];          // 비용
        int[] route = new int[n+1];             // 이전 경로

        // 비용 초기화
        Arrays.fill(expenses, MAX);

        // 다익스트라
        dijkstra(n, start, expenses, route, edgeMap);

        // 도시수, 경로
        int curCity = destination;
        ArrayList<Integer> routes = new ArrayList<>();  // 경로 리스트
        while (curCity != 0) {
            routes.add(curCity);
            curCity = route[curCity];
        }

        answer.append(expenses[destination]).append("\n");
        answer.append(routes.size()).append("\n");

        for (int i = routes.size() - 1; i >= 0; i--) {
            answer.append(routes.get(i)).append(" ");
        }

        return answer.toString();
    }

    private static void dijkstra(int n, int start, int[] expenses, int[] route, Map<Integer, ArrayList<Edge>> edgeMap) {
        // 비용 우선 순위 큐
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(Comparator.comparingInt((Edge e) -> e.expense));
        boolean[] visited = new boolean[n+1];   // 도시 방문 여부

        // init
        edgeQueue.add(new Edge(start, 0));
        expenses[start] = 0;
        route[start] = 0;

        while (!edgeQueue.isEmpty()) {
            Edge curEdge = edgeQueue.poll();
            int curCity = curEdge.destination;

            if (visited[curCity]) continue;
            visited[curCity] = true;

            for (Edge e : edgeMap.getOrDefault(curCity, new ArrayList<>())) {
                int newCity = e.destination;
                int newExpense = expenses[curCity] + e.expense;

                // 현재 비용보다 크면 무시
                if (newExpense >= expenses[newCity]) continue;

                // 현재 비용보다 작으면 비용, 경로 업데이트
                expenses[newCity] = newExpense;
                route[newCity] = curCity;
                edgeQueue.offer(new Edge(newCity, newExpense));
            }
        }
    }

    private static class Edge {
        private final int destination;
        private final int expense;

        public Edge (int destination, int expense) {
            this.destination = destination;
            this.expense = expense;
        }
    }
}
