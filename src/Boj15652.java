import java.io.*;
import java.util.StringTokenizer;

public class Boj15652 {
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
        int[] array = new int[m];

        return dfs(n, m, 0, array);
    }

    private static String dfs(int n, int m, int depth, int[] array) {
        StringBuilder sb = new StringBuilder();

        if (depth == m) {
            for (int a : array) {
                sb.append(a).append(" ");
            }
            return sb.append("\n").toString();
        }

        for (int i = depth > 0 ? array[depth-1] : 1; i <= n; i++) {
            array[depth] = i;
            sb.append(dfs(n, m, depth+1, array));
        }

        return sb.toString();
    }
}
