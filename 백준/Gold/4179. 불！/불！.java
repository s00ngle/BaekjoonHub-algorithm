import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R;
    static int C;
    static char[][] map;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        int x;
        int y;
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        public Node(Point point, int time) {
            this.x = point.x;
            this.y = point.y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        Point jihoon = new Point(0, 0);
        List<Point> fires = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'J') {
                    jihoon = new Point(i, j);
                }
                if (map[i][j] == 'F') {
                    fires.add(new Point(i, j));
                }
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(jihoon, 1));
        for (Point fire : fires) {
            q.add(new Node(fire, 1));
        }

        while (!q.isEmpty()) {
            Node now = q.poll();
            int x = now.x;
            int y = now.y;

            if (map[x][y] == 'J') {
                if (x == 0 || y == 0 || x == R - 1 || y == C - 1) {
                    System.out.println(now.time);
                    return;
                }
            }

            int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    continue;
                }

                if (map[x][y] == 'J') {
                    if (map[nx][ny] == '.') {
                        map[nx][ny] = 'J';
                        q.add(new Node(nx, ny, now.time + 1));
                    }
                }
                else if (map[x][y] == 'F') {
                    if (map[nx][ny] == '.' || map[nx][ny] == 'J') {
                        map[nx][ny] = 'F';
                        q.add(new Node(nx, ny, now.time + 1));
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
