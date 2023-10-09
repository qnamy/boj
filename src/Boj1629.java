import java.io.*;
import java.util.StringTokenizer;

public class Boj1629 {
    public static void main (String[] args) {
        try (
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                OutputStreamWriter osw = new OutputStreamWriter(System.out);
                BufferedWriter bw = new BufferedWriter(osw);
                ) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            bw.write(solution(a, b, c) + "");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long solution (long a, long b, long c) {
        // a 와 c 의 최대공약수
        return calc(a, b, c);
    }

    private static long calc (long a, long b, long c) {
        if (b == 1) return a % c;

        long half = calc(a, b/2, c);

        return (b % 2 == 1) ? ((half * half) % c) * (a % c) % c : (half * half) % c;
    }
}
