import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj15686 {
    private static final int MAX = Integer.MAX_VALUE;
    private static int m;
    private static final ArrayList<Point> houses = new ArrayList<>();
    private static final ArrayList<Point> chickens = new ArrayList<>();
    private static Point[] choice;

    public static void main(String[] args) {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            choice = new Point[m];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    if (value == 1) {
                        houses.add(new Point(i, j));
                    } else if (value == 2) {
                        chickens.add(new Point(i, j));
                    }
                }
            }

            bw.write(Integer.toString(solution()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int solution() {
        return choiceChicken(0, 0);
    }

    private static int choiceChicken( int index, int depth) {
        if (depth == m) {
            return getMinDist();
        }

        int dist = MAX;

        for (int i = index; i < chickens.size(); i++) {
            choice[depth] = chickens.get(i);
            dist = Math.min(dist, choiceChicken(i+1, depth+1));
        }

        return dist;
    }

    private static int getMinDist() {
        int dist = 0;
        for (Point house : houses) {
            int minDist = MAX;
            for (Point chicken : choice) {
                minDist = Math.min(minDist, house.getDist(chicken));
            }
            dist += minDist;
        }
        return dist;
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getDist(Point p) {
            return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
        }
    }
}
