import java.io.*;
import java.util.Stack;

public class Boj1918 {
    public static void main(String[] args) {
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
            ) {

            String exp = br.readLine();

            Stack<Character> stack = new Stack<>();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < exp.length(); i++) {
                char op = exp.charAt(i);
                int priority = priority(op);

                switch (op) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        // 우선순위가 높거나 같은 연산자 꺼내기
                        while (!stack.isEmpty() && priority(stack.peek()) >= priority) {
                            sb.append(stack.pop());
                        }
                        stack.push(op);
                        break;
                    case '(':
                        stack.push(exp.charAt(i));
                        break;
                    case ')':
                        while (!stack.isEmpty() && priority(stack.peek()) > priority) {
                            sb.append(stack.pop());
                        }
                        // ( 꺼내기
                        stack.pop();
                        break;
                    default:
                        sb.append(exp.charAt(i));
                }
            }

            // 스택에 남은 연산자 꺼내기
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            bw.write(sb.toString());

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static int priority(char operator) {
        switch (operator) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            case '(':
            case ')':
                return 0;
            default:
                return -1;
        }
    }
}
