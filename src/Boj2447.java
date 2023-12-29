import java.io.*;
import java.util.Arrays;

public class Boj2447 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());

            char[][] star = new char[n][n];

            bw.write(solution(n, star));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(int n, char[][] star) {
        for (int i = 0; i < n; i++) {
            Arrays.fill(star[i], ' ');
        }

        makeStar(n, star, 0, 0);

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer.append(star[i][j]);
            }
            answer.append("\n");
        }

        return answer.toString();
    }

    private static void makeStar(int n, char[][] star, int x, int y) {
        if (n == 1) {
            star[x][y] = '*';
            return;
        }

        int part = 0;
        for (int i = x; i < x + n; i += n / 3) {
            for (int j = y; j < y + n; j += n / 3) {
                if (part++ == 4) continue;
                makeStar(n / 3, star, i, j);
            }
        }
    }
}
