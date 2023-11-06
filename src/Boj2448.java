import java.io.*;
import java.util.Arrays;

public class Boj2448 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());

            bw.write(solution(n));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(int n) {
        char[][] star = new char[n][2*n-1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(star[i], ' ');
        }

        makeStar(star, 0, n-1, n);

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            answer.append(String.valueOf(star[i])).append("\n");
        }

        return answer.toString();
    }

    private static void makeStar(char[][] star, int x, int y, int n) {
        if (n == 3) {
            star[x][y] = '*';
            star[x+1][y-1] = star[x+1][y+1] = '*';
            star[x+2][y-2] = star[x+2][y-1] = star[x+2][y] = star[x+2][y+1] = star[x+2][y+2] = '*';
            return;
        }
        makeStar(star, x, y, n / 2);
        makeStar(star, x + (n / 2), y - (n / 2), n / 2);
        makeStar(star, x + (n / 2), y + (n / 2), n / 2);
    }
}
