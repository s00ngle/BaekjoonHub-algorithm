import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {

    static int N;
    static int[][] map;

    static int[][] dist;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
                }
            }

            int answer = dijkstra();

            sb.append("#").append(t).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }

    static int dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>((o1, o2) -> map[o1.x][o1.y] - map[o2.x][o2.y]);
        pq.add(new Point(0, 0));

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Point now = pq.poll();

            if (dist[now.x][now.y] < map[now.x][now.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                if (dist[nx][ny] > dist[now.x][now.y] + map[nx][ny]) {
                    dist[nx][ny] = dist[now.x][now.y] + map[nx][ny];
                    pq.add(new Point(nx, ny));
                }
            }
        }
        return dist[N - 1][N - 1];
    }

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}