import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj1644 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            int n = Integer.parseInt(br.readLine());

            bw.write(Integer.toString(solution(n)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution(int n) {
        int answer = 0;
        ArrayList<Integer> primes = getPrimes(n);

        // init
        int left = 0;
        int right = 0;
        int sum = 0;

        while (left <= right) {
            // 합계가 n보다 작으면 right 이동
            if (sum < n) {
                if (right >= primes.size()) break;
                sum += primes.get(right++);
            }
            // 합계가 n보다 크면 left 이동
            else {
                if (sum == n) answer++;
                sum -= primes.get(left++);
            }
        }

        return answer;
    }

    private static ArrayList<Integer> getPrimes(int n) {
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[n+1];

        for (int i = 2; i * i < isPrime.length; i++) {
            if (!isPrime[i]) {

                for (int j = i * i; j < isPrime.length; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) primes.add(i);
        }

        return primes;
    }
}
