import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Boj11729 {
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
        ArrayList<int[]> moves = new ArrayList<>();
        hanoi(n, 1, 2, 3, moves);

        StringBuilder answer = new StringBuilder();
        answer.append(moves.size()).append("\n");
        for (int[] move : moves) {
            answer.append(move[0]).append(" ").append(move[1]).append("\n");
        }

        return answer.toString();
    }

    private static void hanoi(int n, int from, int mid, int to, List<int[]> moves) {
        if (n == 1) {
            moves.add(new int[] {from, to});
            return;
        }

        // from -> mid n-1개 이동
        hanoi(n-1, from, to, mid, moves);

        // from -> to 1개 이동
        moves.add(new int[] {from, to});

        // mid -> to n-1개 이동
        hanoi(n-1, mid, from, to, moves);
    }
}
