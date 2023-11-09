import java.io.*;
import java.util.Arrays;
import java.util.OptionalInt;

public class Boj27960 {
    public static void main (String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            bw.write(
                    String.valueOf(
                            Arrays.stream(br.readLine().split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .reduce((a, b) -> a ^ b)
                                    .orElse(0))
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
