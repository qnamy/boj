import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj10830 {
    private static final int MOD = 1_000;
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            int[][] array = new int[n][n];

            for (int i = 0; i < n; i++) {
                array[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(num -> Integer.parseInt(num) % MOD)
                        .toArray();
            }

            for (int[] a : solution(n, b, array)) {
                for (int num : a) {
                    bw.write(num + " ");
                }
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[][] solution(int n, long b, int[][] array) {
        return pow(n, b, array);
    }

    private static int[][] pow(int n, long b, int[][] array) {
        if (b == 1) return array;

        int[][] pow = pow(n, b / 2, array);

        pow = mul(n, pow, pow);

        if (b % 2 == 1L) {
            pow = mul(n, pow, array);
        }

        return pow;
    }

    private static int[][] mul(int n, int[][] a, int[][] b) {
        int[][] newArray = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    newArray[i][j] += (a[i][k] * b[k][j]);
                    newArray[i][j] %= MOD;
                }
            }
        }

        return newArray;
    }
}
