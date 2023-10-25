import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11660 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 누적합 배열
            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    int cur = Integer.parseInt(st.nextToken());
                    // arr[i][j] = arr[i-1][j] + arr[i][j-1] - arr[i-1][j-1] + cur;
                    int sum1 = i-1 < 0 ? 0 : arr[i-1][j];
                    int sum2 = j-1 < 0 ? 0 : arr[i][j-1];
                    int sum3 = i-1 < 0 || j-1 < 0 ? 0 : arr[i-1][j-1];
                    arr[i][j] = cur + sum1 + sum2 - sum3;
                }
            }

            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());

                int x1 = Integer.parseInt(st.nextToken()) - 1;
                int y1 = Integer.parseInt(st.nextToken()) - 1;
                int x2 = Integer.parseInt(st.nextToken()) - 1;
                int y2 = Integer.parseInt(st.nextToken()) - 1;

                bw.write(solution(arr, x1, y1, x2, y2) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int[][] arr, int x1, int y1, int x2, int y2) {
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);

        // arr[maxX][maxY] - arr[maxX][minY-1] - arr[minX-1][maxY] + arr[minX-1][minY-1]
        int sum1 = minY-1 < 0 ? 0 : arr[maxX][minY-1];
        int sum2 = minX-1 < 0 ? 0 : arr[minX-1][maxY];
        int sum3 = minX-1 < 0 || minY-1 < 0 ? 0 : arr[minX-1][minY-1];

        return arr[maxX][maxY] - sum1 - sum2 + sum3;
    }
}
