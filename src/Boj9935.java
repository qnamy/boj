import java.io.*;
import java.util.Stack;

public class Boj9935 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            String input = br.readLine();
            String explosion = br.readLine();

            bw.write(solution(input, explosion));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(String input, String explosion) {
        StringBuilder answer = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int expLength = explosion.length();

        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));

            if (stack.size() >= expLength) {
                boolean isExplode = true;
                int start = stack.size() - expLength;

                for (int j = 0; j < expLength; j++) {
                    if (stack.get(start + j) != explosion.charAt(j)) {
                        isExplode = false;
                        break;
                    }
                }

                if (isExplode) {
                    for (int j = 0; j < expLength; j++) {
                        stack.pop();
                    }
                }
            }
        }

        for (char c : stack) {
            answer.append(c);
        }

        if (answer.length() == 0) answer.append("FRULA");

        return answer.toString();
    }
}
