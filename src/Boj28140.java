import java.io.*;
import java.util.*;

public class Boj28140 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int[][] q_list = new int[q][2];

            String input = br.readLine();

            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());

                q_list[i][0] = Integer.parseInt(st.nextToken());
                q_list[i][1] = Integer.parseInt(st.nextToken());
            }

            bw.write(solution(input, q_list));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(String input, int[][] q) {
        ArrayList<Integer> r_list = new ArrayList<>();
        ArrayList<Integer> b_list = new ArrayList<>();

        int[] r_index = new int[input.length()];
        int[] b_index = new int[input.length()];

        int r_idx = 0;
        int b_idx = -1;

        for (int i = 0; i < input.length(); i++) {
            r_index[i] = r_idx;
            if (input.charAt(i) == 'R') {
                r_list.add(i);
                r_idx++;
            }

            if (input.charAt(i) == 'B') {
                b_list.add(i);
                b_idx++;
            }
            b_index[i] = b_idx;
        }

        StringBuilder answer = new StringBuilder();

        for (int[] ints : q) {
            int l = ints[0];
            int r = ints[1];

            if (r_index[l] + 1 >= r_list.size() || b_index[r] - 1 < 0
                    || r_list.get(r_index[l] + 1) > b_list.get(b_index[r] - 1)) {
                answer.append(-1).append("\n");
                continue;
            }

            answer.append(r_list.get(r_index[l])).append(" ");
            answer.append(r_list.get(r_index[l] + 1)).append(" ");
            answer.append(b_list.get(b_index[r] - 1)).append(" ");
            answer.append(b_list.get(b_index[r])).append("\n");
        }

        return answer.toString();
    }
}
