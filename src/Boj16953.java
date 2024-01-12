import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16953 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(Integer.toString(solution(a, b)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution(int a, int b) {
        Queue<Number> q = new LinkedList<>();

        q.add(new Number(a, 1));

        while (!q.isEmpty()) {
            Number cur = q.poll();

            if (cur.num == b) return cur.count;

            // 곱하기 2
            if (cur.num * 2 <= b) q.add(new Number(cur.num * 2, cur.count + 1));

            // 뒤에 1
            if (cur.num * 10 + 1 <= b) q.add(new Number(cur.num * 10 + 1, cur.count + 1));
        }

        return -1;
    }

    private static class Number {
        long num;
        int count;

        public Number(long num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}
