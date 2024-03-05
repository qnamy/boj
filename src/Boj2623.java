import java.io.*;
import java.util.*;

public class Boj2623 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            ArrayList<Integer>[] order = new ArrayList[n+1]; // 보조PD 순서
            int[] inDegree = new int[n+1]; // 위상

            for (int i = 0; i < order.length; i++) {
                order[i] = new ArrayList<>();
            }

            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());

                int singers = Integer.parseInt(st.nextToken());
                int prevSinger = Integer.parseInt(st.nextToken());

                for (int j = 1; j < singers; j++) {
                    int curSinger = Integer.parseInt(st.nextToken());
                    order[prevSinger].add(curSinger);
                    inDegree[curSinger]++;
                    prevSinger = curSinger;
                }
            }

            ArrayList<Integer> answer = solution(order, inDegree);
            if (answer.size() != n) bw.write("0");
            else {
                for (int s : answer) {
                    bw.write(Integer.toString(s));
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<Integer> solution(ArrayList<Integer>[] order, int[] inDegree) {
        Queue<Integer> q = new LinkedList<>();

        // 0인 위상 담기
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();

        while (!q.isEmpty()) {
            int cur_singer = q.poll();

            answer.add(cur_singer);

            for (int nextSinger : order[cur_singer]) {
                inDegree[nextSinger]--;
                if (inDegree[nextSinger] == 0) {
                    q.offer(nextSinger);
                }
            }
        }

        return answer;
    }
}
