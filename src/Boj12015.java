import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj12015 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());

            int[] sequence = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                sequence[i] = Integer.parseInt(st.nextToken());
            }

            bw.write(Integer.toString(solution(sequence)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution(int[] sequence) {
        ArrayList<Integer> list = new ArrayList<>();

        // init
        list.add(sequence[0]);

        for (int i = 1; i < sequence.length; i++) {
            // list last item 비교
            int lastItem = list.get(list.size() - 1);

            // 크면 추가
            if (sequence[i] > lastItem) {
                list.add(sequence[i]);
            }
            // 작으면 교체
            else if (sequence[i] < lastItem) {
                int left = 0;
                int right = list.size() - 1;
                while (left + 1 < right) {
                    int mid = (left + right) / 2;
                    if (sequence[i] > list.get(mid)) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }

                if (sequence[i] > list.get(left)) {
                    list.set(right, sequence[i]);
                } else {
                    list.set(left, sequence[i]);
                }
            }
        }

        return list.size();
    }
}
