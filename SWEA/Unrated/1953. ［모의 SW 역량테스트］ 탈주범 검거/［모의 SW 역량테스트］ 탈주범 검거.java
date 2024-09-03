import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int N, M, L;
 
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] check;
 
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
 
    static int answer;
 
    static void dfs(int x, int y, int depth) {
 
        if (depth >= L) return;
 
        for (int i = 0; i < 4; i++) {
 
            if (i == 0 && (map[x][y] == 2 || map[x][y] == 6 || map[x][y] == 7)) continue;
            else if (i == 1 && (map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 5)) continue;
            else if (i == 2 && (map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 7)) continue;
            else if (i == 3 && (map[x][y] == 3 || map[x][y] == 5 || map[x][y] == 6)) continue;
 
            int nx = x + dx[i];
            int ny = y + dy[i];
 
            if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny]) {
                if (i == 0) {
                    if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
                        if (!check[nx][ny]) {
                            check[nx][ny] = true;
                            answer++;
                        }
                        visited[nx][ny] = true;
                        dfs(nx, ny, depth + 1);
                        visited[nx][ny] = false;
                    }
                } else if (i == 1) {
                    if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
                        if (!check[nx][ny]) {
                            check[nx][ny] = true;
                            answer++;
                        }
                        visited[nx][ny] = true;
                        dfs(nx, ny, depth + 1);
                        visited[nx][ny] = false;
                    }
                } else if (i == 2) {
                    if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
                        if (!check[nx][ny]) {
                            check[nx][ny] = true;
                            answer++;
                        }
                        visited[nx][ny] = true;
                        dfs(nx, ny, depth + 1);
                        visited[nx][ny] = false;
                    }
                } else {
                    if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
                        if (!check[nx][ny]) {
                            check[nx][ny] = true;
                            answer++;
                        }
                        visited[nx][ny] = true;
                        dfs(nx, ny, depth + 1);
                        visited[nx][ny] = false;
                    }
                }
 
            }
 
 
 
        }
    }
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(bf.readLine());
 
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
 
            map = new int[N][M];
            visited = new boolean[N][M];
            check = new boolean[N][M];
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            answer = 1;
            visited[x][y] = true;
            dfs(x, y, 1);
 
            sb.append("#").append(t).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }
 
}