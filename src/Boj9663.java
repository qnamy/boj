import java.io.*;
import java.util.Arrays;

public class Boj9663 {
    public static void main (String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());

            bw.write(solution(n) + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int n) {
        // {i, queens[i]} = queen i행, queens[i]열에 있다.
        int[] queens = new int[n];

        return putQueen(0, n, queens);
    }

    private static int putQueen (int x, int n, int[] queens) {
        // 퀸 n개를 다 놓았을때 카운트
        if (x == n) return 1;

        int result = 0;

        for (int i = 0; i < n; i++) {
            if (checkPossible(x, i, queens)) {
                queens[x] = i;
                result += putQueen(x+1, n, queens);
            }
        }

        return result;
    }

    private static boolean checkPossible(int row, int col, int[] queens) {
        // 이전 행까지 loop
        for (int i = 0; i < row; i++) {
            // 행 체크
            if (queens[i] == col) return false;
            // 대각선 체크
            if (row - i == Math.abs(queens[i] - col)) return false;
        }

        return true;
    }
}
