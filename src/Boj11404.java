import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11404 {
    private final static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
            ) {

            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());

            int[][] buses = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(buses[i], INF);
                buses[i][i] = 0;
            }

            StringTokenizer st;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                int weight = Integer.parseInt(st.nextToken());

                buses[start][end] = Math.min(buses[start][end], weight);
            }

            solution(buses);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bw.write((buses[i][j] == INF ? 0 : buses[i][j]) + " ");
                }
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solution (int[][] buses) {
        final int n = buses.length;


        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (buses[i][k] == INF || buses[k][j] == INF) continue;
                    buses[i][j] = Math.min(buses[i][j], buses[i][k] + buses[k][j]);
                }
            }
        }
    }
}
