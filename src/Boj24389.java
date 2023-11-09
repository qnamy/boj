import java.io.*;

public class Boj24389 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());
            bw.write(Integer.toString(Integer.bitCount(n^(~n+1))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
