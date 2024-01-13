import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17144 {
    private static int r;
    private static int c;
    private static int[][] house;

    private static int[] airConditioner = new int[2];
    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {

        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))
                ) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            house = new int[r][c];

            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < c; j++) {
                    house[i][j] = Integer.parseInt(st.nextToken());
                    // 공기청정기 위치
                    if (house[i][j] == -1 && airConditioner[1] == 0) {
                        airConditioner[0] = i;
                        airConditioner[1] = i + 1;
                    }
                }
            }

            bw.write(Integer.toString(solution(t)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int solution(int t) {
        while (t-- > 0) {
            // 먼지 확산
            spreadDust();
            // 공기청정기 작동
            workTopAirConditioner();
            workDownAirConditioner();
        }

        int answer = 2; // 공기청정기
        for (int[] h : house) {
            answer += Arrays.stream(h).sum();
        }

        return answer;
    }

    private static void spreadDust() {
        ArrayList<Point> pointList = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (house[i][j] > 0) pointList.add(new Point(i, j, house[i][j]));
            }
        }

        for (Point point : pointList) {
            int count = 0; // 확산 칸 수
            int weight = point.w / 5; // 확산 미세먼지 양

            for (int[] d : DIR) {
                int nx = point.x + d[0];
                int ny = point.y + d[1];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c || house[nx][ny] == -1) continue;

                house[nx][ny] += weight;
                count++;
            }

            house[point.x][point.y] -= (weight * count);
        }
    }

    private static void workTopAirConditioner() {
        for (int i = airConditioner[0] - 1; i > 0; i--) house[i][0] = house[i - 1][0];
        for (int i = 0; i < c - 1; i++) house[0][i] = house[0][i + 1];
        for (int i = 0; i < airConditioner[0]; i++) house[i][c - 1] = house[i + 1][c - 1];
        for (int i = c - 1; i > 1; i--) house[airConditioner[0]][i] = house[airConditioner[0]][i - 1];
        house[airConditioner[0]][1] = 0;
    }

    private static void workDownAirConditioner() {
        for (int i = airConditioner[1] + 1; i < r - 1; i++) house[i][0] = house[i + 1][0];
        for (int i = 0; i < c - 1; i++) house[r - 1][i] = house[r - 1][i + 1];
        for (int i = r - 1; i > airConditioner[1]; i--) house[i][c - 1] = house[i - 1][c - 1];
        for (int i = c - 1; i > 1; i--) house[airConditioner[1]][i] = house[airConditioner[1]][i - 1];
        house[airConditioner[1]][1] = 0;
    }

    private static class Point {
        int x;
        int y;
        int w;

        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}
