import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class Boj14003 {
    private static final int MIN = -1_000_000_001;
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {

            int n = Integer.parseInt(br.readLine());

            int[] array = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            bw.write(solution(array));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String solution(int[] array) {
        StringBuilder answer = new StringBuilder();

        int[] list = new int[array.length+1];
        int[] dp = new int[array.length];

        list[0] = MIN;
        int len = 0;

        for (int i = 0; i < array.length; i++) {
            // list의 마지막 항목보다 크면 추가
            if (array[i] > list[len]) {
                dp[i] = ++len;
                list[len] = array[i];
            }
            // 작으면 교체
            else {
                int index = getIndex(list, 0, len, array[i]);
                dp[i] = index;
                list[index] = array[i];
            }
        }

        answer.append(len).append("\n");

        Stack<Integer> stack = new Stack<>();
        for (int i = dp.length-1; i >= 0; i--) {
            if (dp[i] == len) {
                stack.push(array[i]);
                len--;
            }
        }

        while (!stack.isEmpty()) {
            answer.append(stack.pop()).append(" ");
        }

        return answer.toString();
    }

    private static int getIndex(int[] list, int left, int right, int value) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (list[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
