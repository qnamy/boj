import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15666 {
    private static int n;
    private static int m;
    private static int[] array;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            array = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            bw.write(solution());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String solution() {
        int[] sequence = new int[m];

        Arrays.sort(array);

        return backTracking(0, 0, sequence);
    }

    private static String backTracking(int index, int depth, int[] sequence) {
        StringBuilder answer = new StringBuilder();

        if (depth == m) {
            for (int num : sequence) {
                answer.append(num).append(" ");
            }

            return answer.append("\n").toString();
        }

        for (int i = index; i < n; i++) {
            if (i > 0 && array[i] == array[i-1]) continue;

            sequence[depth] = array[i];
            answer.append(backTracking(i, depth+1, sequence));
        }

        return answer.toString();
    }
}
