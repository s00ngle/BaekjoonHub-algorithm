import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int M;
    static char[][] map;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Point R;
    static Point B;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        R = new Point(0, 0);
        B = new Point(0, 0);

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    R.x = i;
                    R.y = j;
                }
                if (map[i][j] == 'B') {
                    B.x = i;
                    B.y = j;
                }
            }
        }
        System.out.println(BFS());
    }

    static int BFS() {
        Queue<Item> queue = new LinkedList<>();
        queue.add(new Item(R, B, 0));

        while (!queue.isEmpty()) {

            Item now = queue.poll();

            if (now.cnt == 10) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int rx = now.Red.x;
                int ry = now.Red.y;
                int bx = now.Blue.x;
                int by = now.Blue.y;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // move red ball
                while (true) {
                    int nx = rx + dx[i];
                    int ny = ry + dy[i];

                    if (map[nx][ny] == '#') {
                        break;
                    }

                    if (map[nx][ny] == 'O') {
                        isRedHole = true;
                        break;
                    }

                    rx = nx;
                    ry = ny;
                }

                // move blue ball
                while (true) {
                    int nx = bx + dx[i];
                    int ny = by + dy[i];

                    if (map[nx][ny] == '#') {
                        break;
                    }

                    if (map[nx][ny] == 'O') {
                        isBlueHole = true;
                        break;
                    }

                    bx = nx;
                    by = ny;
                }

                if (isBlueHole) {
                    continue;
                }
                else if (isRedHole) {
                    return now.cnt + 1;
                }

                // if all balls don't move -> don't add in queue
                if (now.Red.x == rx && now.Red.y == ry && now.Blue.x == bx && now.Blue.y == by) {
                    continue;
                }

                // overlap -> move
                if (rx == bx && ry == by) {
                    if (i == 0) {
                        // down
                        if (now.Red.x < now.Blue.x) {
                            rx--;
                        }
                        else {
                            bx--;
                        }
                    }
                    else if (i == 1) {
                        // up
                        if (now.Red.x < now.Blue.x) {
                            bx++;
                        }
                        else {
                            rx++;
                        }
                    }
                    else if (i == 2) {
                        // left
                        if (now.Red.y < now.Blue.y) {
                            by++;
                        }
                        else {
                            ry++;
                        }
                    }
                    else {
                        // right
                        if (now.Red.y < now.Blue.y) {
                            ry--;
                        }
                        else {
                            by--;
                        }
                    }
                }

                queue.add(new Item(new Point(rx, ry), new Point(bx, by), now.cnt + 1));
            }
        }

        return -1;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    static class Item {
        Point Red;
        Point Blue;
        int cnt;

        Item (Point Red, Point Blue, int cnt) {
            this.Red = Red;
            this.Blue = Blue;
            this.cnt = cnt;
        }
    }
}