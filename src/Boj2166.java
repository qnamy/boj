import java.io.*;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Arrays;

public class Boj2166 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());

            int[][] point = new int[n+1][2];

            for (int i = 0; i < n; i++) {
                point[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
            point[n] = point[0];

            bw.write(String.format("%.1f", solution(point)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static double solution(int[][] point) {
        double answer = 0d;

        // 신발끈 공식
        for (int i = 0; i < point.length-1; i++) {
            double x1 = point[i][0];
            double y1 = point[i][1];
            double x2 = point[i+1][0];
            double y2 = point[i+1][1];
            answer += x1 * y2 - x2 * y1;
        }

        return Math.round(Math.abs(answer) / 2 * 10) / 10d;
    }
}
