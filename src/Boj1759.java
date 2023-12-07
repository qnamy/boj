import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1759 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            char[] alphabets = br.readLine().replaceAll("\\s", "").toCharArray();

            bw.write(solution(l, c, alphabets));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(int l, int c, char[] alphabets) {
        Arrays.sort(alphabets);
        char[] password = new char[l];
        return backTracking(l, c, alphabets, 0, 0, password);
    }

    private static String backTracking(int l, int c, char[] alphabets, int depth, int index, char[] password) {
        StringBuilder res = new StringBuilder();

        if (depth == l) {
            // 모음 갯수
            int count = 0;
            for (char p : password) {
                if (p == 'a' || p == 'e' || p == 'i' || p == 'o' || p == 'u') count++;
                res.append(p);
            }

            // 모음 1개 이상 자음 2개 이상 체크
            return count >= 1 && l - count >= 2 ? res.append("\n").toString() : "";
        }

        for (int i = index; i < c; i++) {
            password[depth] = alphabets[i];
            res.append(backTracking(l, c, alphabets, depth+1, i+1, password));
        }

        return res.toString();
    }
}
