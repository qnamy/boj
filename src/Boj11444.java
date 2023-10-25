import java.io.*;

public class Boj11444 {
    private static int MOD = 1000000007;
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            long n = Long.parseLong(br.readLine());

            bw.write(solution(n) + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(long n) {
        /*
        fn   = [1 1]n [1]
        fn-1 = [1 0]  [0]
        */
        final long[][] a = {{1, 1}, {1, 0}};

        return (int) pow(a, n-1)[0][0];
    }

    private static long[][] pow (final long[][] a, long n) {
        if (n == 0 || n == 1) return a;

        long[][] ret = pow(a, n / 2);

        ret = multiply(ret, ret);

        if (n % 2 == 1L) {
            ret = multiply(ret, a);
        }

        return ret;
    }

    private static long[][] multiply(final long[][] a, final long[][] b) {
        long[][] ret = new long[2][2];

        ret[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        ret[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        ret[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        ret[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;

        return ret;
    }
}
