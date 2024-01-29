import java.io.*;
import java.util.*;

public class Boj2252 {
    private static int[] orderCount;
    private static ArrayList<ArrayList<Integer>> orders;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            orderCount = new int[n+1];
            orders = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                orders.add(new ArrayList<>());
            }

            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());

                int student1 = Integer.parseInt(st.nextToken());
                int student2 = Integer.parseInt(st.nextToken());

                orders.get(student1).add(student2);
                orderCount[student2]++;
            }

            List<Integer> answer = solution();
            for (int student : answer) {
                bw.write(student + " ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Integer> solution() {
        ArrayList<Integer> answer = new ArrayList<>(orders.size()-1);
        Queue<Integer> q = new LinkedList<>();

        // init
        for (int i = 1; i < orderCount.length; i++) {
            if (orderCount[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int curStudent = q.poll();
            answer.add(curStudent);

            for (int nextStudent : orders.get(curStudent)) {
                if (--orderCount[nextStudent] == 0) q.offer(nextStudent);
            }
        }

        return answer;
    }
}
