import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1208 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            bw.write(solution(n, s, seq) + "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long solution(int n, int s, int[] seq) {
        // 수열 반으로 나눠서 부분수열의 합 구하기
        ArrayList<Long> left = new ArrayList<>();
        ArrayList<Long> right = new ArrayList<>();
        calcSubSeq(0, n / 2, seq, 0, left);
        calcSubSeq(n / 2, n, seq, 0, right);
        // 정렬
        left.sort(Long::compareTo);
        right.sort(Long::compareTo);

        int l = 0;
        int r = right.size() - 1;
        long count = 0;

        while (l < left.size() && r >= 0) {
            long sum = left.get(l) + right.get(r);

            if (sum == s) {
                long curLeft = left.get(l);
                long leftCount = 0;
                while (l < left.size() && left.get(l) == curLeft) {
                    leftCount++;
                    l++;
                }

                long curRight = right.get(r);
                long rightCount = 0;
                while (r >= 0 && right.get(r) == curRight) {
                    rightCount++;
                    r--;
                }

                count += leftCount * rightCount;
            }

            else if (sum < s) l++;
            else if (sum > s) r--;
        }

        // s가 0일때 아무것도 선택하지 않은 부분수열은 제외한다.
        if (s == 0) count--;

        return count;
    }

    private static void calcSubSeq(int start, int end, int[] seq, long sum, ArrayList<Long> list) {
        if (start == end) {
            list.add(sum);
            return;
        }
        calcSubSeq(start+1, end, seq, sum, list);
        calcSubSeq(start+1, end, seq, sum + seq[start], list);
    }
}
