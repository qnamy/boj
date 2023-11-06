import java.io.*;

public class Boj5639 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            // 전위순위 결과로 이진트리 구성
            String input;
            Node root = null;
            while (true) {
                input = br.readLine();
                if (input == null || input.isEmpty()) break;

                if (root == null) {
                    root = new Node(Integer.parseInt(input), null, null);
                } else {
                    insertNode(root, Integer.parseInt(input));
                }
            }

            bw.write(solution(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(Node root) {
        return postOrder(root);
    }

    private static String postOrder(Node current) {
        StringBuilder result = new StringBuilder();

        if (current.left != null) result.append(postOrder(current.left));
        if (current.right != null) result.append(postOrder(current.right));

        result.append(current.value).append("\n");

        return result.toString();
    }

    private static void insertNode(Node current, int value) {
        if (current.value > value) {
            if (current.left == null) {
                current.left = new Node(value, null, null);
                return;
            }
            insertNode(current.left, value);
        }
        else if (current.value < value) {
            if (current.right == null) {
                current.right = new Node(value, null, null);
                return;
            }
            insertNode(current.right, value);
        }
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
