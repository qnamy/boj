import java.io.*;
import java.util.StringTokenizer;

public class Boj25166 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            final int ari = 1023;

            int sandwich = Integer.parseInt(st.nextToken());
            int kugi = Integer.parseInt(st.nextToken());

            int remained = sandwich - ari;

            bw.write(remained <= 0 ? "No thanks"
                    : (remained & kugi) == remained ? "Thanks" : "Impossible");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
