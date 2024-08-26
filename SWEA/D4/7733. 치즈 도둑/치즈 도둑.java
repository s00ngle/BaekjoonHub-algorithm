import java.io.*;
import java.util.*;

public class Solution {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int N;

    static int[][] cheese;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());

            cheese = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cheese[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int answer = 0;

            for (int taste = 0; taste <= 100; taste++) {

                int cnt = 0;
                visited = new boolean[N][N];

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (cheese[i][j] == taste) {
                            cheese[i][j] = 0;
                        }
                    }
                }

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (cheese[i][j] != 0 && !visited[i][j]) {
                            bfs(i, j);
                            cnt++;
                        }
                    }
                }
                if (answer < cnt) {
                    answer = cnt;
                }
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                if (!visited[nx][ny] && cheese[nx][ny] != 0) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }

    static class Point {
        int x;
        int y;
        public Point(int x, int y)  {
            this.x = x;
            this.y = y;
        }
    }
}
