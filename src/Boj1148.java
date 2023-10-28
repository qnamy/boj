import java.io.*;
import java.util.*;

public class Boj1148 {
    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            ArrayList<int[]> words = new ArrayList<>();

            while (true) {
                String word = br.readLine();
                if (word.equals("-")) break;

                int[] characters = countAlphabet(word);
                words.add(characters);
            }

            while (true) {
                String puzzle = br.readLine();
                if (puzzle.equals("#")) break;

                bw.write(solution(words, puzzle) + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String solution(ArrayList<int[]> words, String puzzle) {
        // puzzle의 단어 분포
        int[] puzzleArr = countAlphabet(puzzle);
        TreeMap<Character, Integer> result = new TreeMap<>();
        for (char ch : puzzle.toCharArray()) {
            result.put(ch, 0);
        }

        for (int[] wordArr : words) {
            if (checkPossible(puzzleArr, wordArr)) {
                for (char ch : result.keySet()) {
                    if (wordArr[ch - 'A'] > 0) {
                        result.put(ch, result.get(ch)+1);
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int max = 0;
        StringBuilder minSb = new StringBuilder(9);
        StringBuilder maxSb = new StringBuilder(9);

        // ch가 가운데 있을때 단어 가능한지 체크
        for (int count : result.values()) {
            // min
            if (count < min) min = count;
            // max
            if (count > max) max = count;
        }

        for (char ch : result.keySet()) {
            if (min == result.get(ch)) minSb.append(ch);
            if (max == result.get(ch)) maxSb.append(ch);
        }

        return minSb + " " + min + " " + maxSb + " " + max;
    }

    private static boolean checkPossible(int[] puzzle, int[] word) {
        for (int i = 0; i < 26; i++) {
            if (puzzle[i] < word[i]) return false;
        }

        return true;
    }

    private static int[] countAlphabet (String word) {
        int[] characters = new int[26];

        for (char ch : word.toCharArray()) {
            characters[ch-'A']++;
        }

        return characters;
    }
}
