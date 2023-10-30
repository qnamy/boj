import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15650 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            bw.write(solution(n, m));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(int n, int m) {
        StringBuilder answer = new StringBuilder();

        for (int i = 1; i <= n - m + 1; i++) {
            answer.append(makeSequence(n, m, i, 1, String.valueOf(i)));
        }

        return answer.toString();
    }

    // 두번째~m까지 재귀
    private static String makeSequence(int n, int m, int num, int count, String sequence) {
        StringBuilder result = new StringBuilder();

        if (count == m) return sequence + "\n";

        for (int i = num+1; i <= n; i++) {
            String curSeq = makeSequence(n, m, i, count+1, sequence + " " + i);
            if (!curSeq.isEmpty()) result.append(curSeq);
        }

        return result.toString();
    }
}
