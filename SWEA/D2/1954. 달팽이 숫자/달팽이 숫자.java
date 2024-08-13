import java.io.*;

public class Solution {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];

            int num = 1;

            for (int i = 0; i < N; i++) {
                arr[0][i] = num++;
            }

            int dir = 1;
            int x = 0;
            int y = N - 1;

            for (int i = N - 1; i >= 1; i--) {
                for (int j = 0; j < i; j++) {
                    x += dx[dir];
                    y += dy[dir];
                    arr[x][y] = num++;
                }
                dir = (dir + 1) % 4;
                for (int j = 0; j < i; j++) {
                    x += dx[dir];
                    y += dy[dir];
                    arr[x][y] = num++;
                }
                dir = (dir + 1) % 4;
            }
            System.out.println("#" + t);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }

        }
    }
}
