import java.io.*;
import java.util.*;

public class Boj1202 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 무게 오름차순 가격 내림차순
            PriorityQueue<Gem> gems = new PriorityQueue<>(n);
            int[] bags = new int[k];

            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                gems.add(new Gem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            for (int i = 0; i < k; i++) {
                bags[i] = Integer.parseInt(br.readLine());
            }

            // 가방 오름차순 정렬
            Arrays.sort(bags);

            bw.write(solution(gems, bags) + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long solution(PriorityQueue<Gem> gems, int[] bags) {
        // 가방에 넣을 수 있는 보석 가격
        // 가격 내림차순
        PriorityQueue<Integer> possibleGem = new PriorityQueue<>(Comparator.reverseOrder());

        long sum = 0;

        for (int bag : bags) {
            // 가방에 넣을 수 있는 보석을 모두 담는다.
            while (!gems.isEmpty() && bag >= gems.peek().weight) {
                possibleGem.add(gems.poll().price);
            }

            if (!possibleGem.isEmpty()) sum += possibleGem.poll();
        }

        return sum;
    }

    private static class Gem implements Comparable<Gem> {
        int weight;
        int price;

        Gem(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Gem g) {
            return this.weight - g.weight; // 무게 오름차순
        }
    }
}
