import java.io.*;
import java.util.*;

public class Boj1916 {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
            ) {

            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());

            Map<Integer, LinkedList<Bus>> bus = new HashMap<>();

            StringTokenizer st;

            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int price = Integer.parseInt(st.nextToken());

                LinkedList<Bus> busList = bus.getOrDefault(start, new LinkedList<>());
                busList.add(new Bus(end, price));
                bus.put(start, busList);
            }

            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            bw.write(solution(n, bus, start, end) + "");

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int n, Map<Integer, LinkedList<Bus>> bus, int start, int end) {
        PriorityQueue<Bus> q = new PriorityQueue<>(Comparator.comparingInt((Bus b) -> b.price));
        int[] price = new int[n+1];
        boolean[] visited = new boolean[n+1];

        Arrays.fill(price, INF);

        price[start] = 0;
        q.add(new Bus(start, 0));
        
        while (!q.isEmpty()) {
            Bus cur = q.poll();

            if (visited[cur.city]) continue;
            visited[cur.city] = true;

            if (!bus.containsKey(cur.city)) continue;
            
            for (Bus b : bus.get(cur.city)) {
                if (visited[b.city] || b.price + price[cur.city] > price[b.city]) continue;

                price[b.city] = b.price + price[cur.city];
                q.add(new Bus(b.city, price[b.city]));
            }
        }

        return price[end];
    }

    private static class Bus {
        int city;
        int price;

        Bus(int city, int price) {
            this.city = city;
            this.price = price;
        }
    }
}
