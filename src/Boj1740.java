import java.io.*;
import java.util.Arrays;

public class Boj1740 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                ) {

            long n = Long.parseLong(br.readLine());

            long answer = 0;
            long base = 1;

            while (n > 0) {
                if ((n & 1) == 1) answer += base;
                base *= 3;
                n >>= 1;
            }

            bw.write(Long.toString(answer));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
