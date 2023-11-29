import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14222 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            bw.write(Integer.toString(solution(k, a)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution(int k, int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == i+1) continue;

            while (a[i] != i + 1) {
                if (a[i] > a.length) return 0; // 배열을 벗어나면 실패

                int targetIndex = a[i] - 1;
                // 중복된 값이 있는 경우
                if (a[i] == a[targetIndex]) {
                    a[i] += k;
                } else {
                    swap(a, i, targetIndex);
                }
            }
        }

        return 1;
    }

    private static void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
