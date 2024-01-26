import java.io.*;
import java.util.StringTokenizer;

public class Boj13172 {
    private static int[][] dices;
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int m = Integer.parseInt(br.readLine());
            dices = new int[m][2];

            StringTokenizer st;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                dices[i][0] = Integer.parseInt(st.nextToken());
                dices[i][1] = Integer.parseInt(st.nextToken());
            }

            bw.write(Long.toString(solution()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long solution() {
        // 통분
        long n = 1;
        long s = 0;
        for (int[] dice : dices) {
            s = (s * dice[0] + dice[1] * n) % MOD;
            n = (n * dice[0]) % MOD;
        }

        return s % n == 0 ? s / n : (s * pow(n, MOD-2)) % MOD;
    }

    private static long pow(long base, int index) {
        if (index == 1) return base;

        long pow = pow(base, index / 2);

        if (index % 2 == 0) return (pow * pow) % MOD;
        else return (pow * pow) % MOD * base % MOD ;
    }
}
