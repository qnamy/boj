import java.io.*;
import java.util.StringTokenizer;

public class Boj1991 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            // 노드의 개수
            int n = Integer.parseInt(br.readLine());

            // root node
            Node root = new Node('A', null, null);

            // 노드삽입
            StringTokenizer st;
            while (n-- > 0) {
                st = new StringTokenizer(br.readLine());
                
                char name = st.nextToken().charAt(0);
                char left = st.nextToken().charAt(0);
                char right = st.nextToken().charAt(0);

                insertNode(root, name, left, right);
            }

            // 전위 중위 후위
            bw.write(getPreorder(root));
            bw.newLine();
            bw.write(getInorder(root));
            bw.newLine();
            bw.write(getPostorder(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPreorder(Node node) {
        StringBuilder sb = new StringBuilder();

        sb.append(node.name);
        if (node.left != null) sb.append(getPreorder(node.left));
        if (node.right != null) sb.append(getPreorder(node.right));

        return sb.toString();
    }

    private static String getInorder(Node node) {
        StringBuilder sb = new StringBuilder();

        if (node.left != null) sb.append(getInorder(node.left));
        sb.append(node.name);
        if (node.right != null) sb.append(getInorder(node.right));

        return sb.toString();
    }

    private static String getPostorder(Node node) {
        StringBuilder sb = new StringBuilder();

        if (node.left != null) sb.append(getPostorder(node.left));
        if (node.right != null) sb.append(getPostorder(node.right));
        sb.append(node.name);

        return sb.toString();
    }

    private static void insertNode(Node node, char name, char left, char right) {
        if (node.name == name) {
            if (left != '.') node.left = new Node(left, null, null);
            if (right != '.') node.right = new Node(right, null, null);
        } else {
            if (node.left != null) insertNode(node.left, name, left, right);
            if (node.right != null) insertNode(node.right, name, left, right);
        }
    }

    private static class Node {
        char name;
        Node left;
        Node right;

        Node(char name, Node left, Node right) {
            this.name = name;
            this.left = left;
            this.right = right;
        }
    }
}
