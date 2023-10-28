import java.io.*;
import java.util.*;

public class Boj11725 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());

            // edge list
            ArrayList<ArrayList<Integer>> edges = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                edges.add(i, new ArrayList<>());
            }

            // edge list 채우기
            StringTokenizer st;
            for(int i = 0; i < n-1; i++) {
                st = new StringTokenizer(br.readLine());

                int v1 = Integer.parseInt(st.nextToken()) - 1;
                int v2 = Integer.parseInt(st.nextToken()) - 1;

                edges.get(v1).add(v2);
                edges.get(v2).add(v1);
            }

            bw.write(solution(n, edges));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(int n, ArrayList<ArrayList<Integer>> edges) {
        int[] parents = new int[n];

        // 트리 탐색
        searchTree(0, edges, parents);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            sb.append((parents[i] + 1)).append("\n");
        }

        return sb.toString();
    }

    private static void searchTree(int current, ArrayList<ArrayList<Integer>> edges, int[] parents) {
        for (int e : edges.get(current)) {
            // 정점중 부모는 제외
            if (e == parents[current]) continue;
            // 부모노드 저장
            parents[e] = current;
            // 자식노드 탐색
            searchTree(e, edges, parents);
        }
    }
}
