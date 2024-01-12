import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15657 {
    private static int n;
    private static int m;
    private static int[] nums;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            bw.write(solution());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution() {
        // sort arrays
        Arrays.sort(nums);

        int[] array = new int[m];

        return backTracking(array, 0, 0);
    }

    private static String backTracking(int[] array, int index, int depth) {
        StringBuilder res = new StringBuilder();

        if (depth == m) {
            for (int num : array) {
                res.append(num).append(" ");
            }
            res.append("\n");
            return res.toString();
        }

        for (int i = index; i < n; i++) {
            array[depth] = nums[i];
            res.append(backTracking(array, i, depth+1));
        }

        return res.toString();
    }
}
