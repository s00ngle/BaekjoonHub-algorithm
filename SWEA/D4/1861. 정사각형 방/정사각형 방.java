import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int[][] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = 0;
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int cnt = dfs(i, j, 1);
                    if (cnt > max) {
                        max = cnt;
                        min = arr[i][j];
                    } else if (cnt == max) {
                        min = Math.min(min, arr[i][j]);
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(min).append(" ").append(max).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int dfs(int x, int y, int depth) {
        int cnt = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (arr[nx][ny] == arr[x][y] + 1) {
                cnt += dfs(nx, ny, depth + 1);
            }
        }
        return cnt;
    }
}
