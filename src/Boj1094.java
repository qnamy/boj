import java.io.*;

public class Boj1094 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            bw.write(Integer.toString(Integer.bitCount(Integer.parseInt(br.readLine()))));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
