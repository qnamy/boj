import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj12833 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            bw.write(Integer.toString(c % 2 == 1 ? a^b : a));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
