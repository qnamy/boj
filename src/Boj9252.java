import java.io.*;
import java.util.Arrays;

public class Boj9252 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            char[] str1 = br.readLine().toCharArray();
            char[] str2 = br.readLine().toCharArray();

            bw.write(solution(str1, str2));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String solution(char[] str1, char[] str2) {
        StringBuilder answer = new StringBuilder();

        int len1 = str1.length;
        int len2 = str2.length;

        int[][] dp = getDp(str1, str2);
        answer.append(dp[len1][len2]).append("\n");

        // lcs 만들기
        StringBuilder lcs = new StringBuilder();
        int index1 = len1;
        int index2 = len2;
        while (index1 > 0 && index2 > 0) {
            if (str1[index1-1] == str2[index2-1]) {
                lcs.append(str1[index1-1]);
                index1--;
                index2--;
            } else {
                if (dp[index1][index2] == dp[index1-1][index2]) {
                    index1--;
                } else {
                    index2--;
                }
            }
        }

        answer.append(lcs.reverse());

        return answer.toString();
    }

    private static int[][] getDp(char[] str1, char[] str2) {
        int len1 = str1.length;
        int len2 = str2.length;

        int[][] dp = new int[len1+1][len2+1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1[i-1] == str2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp;
    }
}
