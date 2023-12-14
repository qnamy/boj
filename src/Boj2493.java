import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj2493 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {
            int n = Integer.parseInt(br.readLine());

            Top[] tops = new Top[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                tops[i] = new Top(i+1, Integer.parseInt(st.nextToken()));
            }

            bw.write(solution(tops));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(Top[] tops) {
        StringBuilder answer = new StringBuilder();

        Stack<Top> stack = new Stack<>();

        for (Top top : tops) {
            while (!stack.isEmpty() && stack.peek().height < top.height) {
                stack.pop();
            }
            answer.append(stack.isEmpty() ? 0 : stack.peek().index).append(" ");
            stack.push(top);
        }

        return answer.toString();
    }

    private static class Top {
        int index;
        int height;

        Top(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}
