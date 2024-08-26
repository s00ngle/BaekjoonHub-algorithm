import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int N;
    static char[][] map;
    static boolean[][] visited;
 
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            visited = new boolean[N][N];
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String temp = st.nextToken();
                for (int j = 0; j < N; j++) {
                    map[i][j] = temp.charAt(j);
                }
            }
 
            int answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.' && !visited[i][j] && bomb(i, j) == 0) {
                        BFS(i, j);
                        answer += 1;
                    }
                }
            }
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == '.') {
                        answer++;
                    }
                }
            }
 
            System.out.println("#" + t + " " + answer);
        }
    }
 
    static void BFS(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
 
        while (!q.isEmpty()) {
            Point p = q.poll();
            visited[p.x][p.y] = true;
 
            int cnt = bomb(p.x, p.y);
            map[p.x][p.y] = (char) (cnt + '0');
 
            if (cnt > 0) {
                continue;
            }
 
            for (int i = 0; i < 8; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
 
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
 
                if (!visited[nx][ny] && map[nx][ny] == '.') {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }
 
    static int bomb(int x, int y) {
        int cnt = 0;
 
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
 
            if (map[nx][ny] == '*') {
                cnt++;
            }
        }
 
        return cnt;
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