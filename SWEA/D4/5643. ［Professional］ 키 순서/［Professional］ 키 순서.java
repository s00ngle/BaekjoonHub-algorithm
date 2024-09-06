import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int M;

    static boolean[][] map;

    static int answer;

    static void f () {
        for (int i = 1; i <= N; ++i) {
            int cnt = 0;
            for (int j = 1; j <= N; ++j) {
                if (i != j && map[i][j]) {
                    cnt++;
                }
            }
            for (int j = 1; j <= N; ++j) {
                if (i != j && map[j][i]) {
                    cnt++;
                }
            }
            if (cnt == N - 1) {
                answer++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; ++t) {

            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            map = new boolean[N + 1][N + 1];

            for (int i = 1; i <= M; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[a][b] = true;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    if (k != i) {
                        for (int j = 1; j <= N; j++) {
                            if (k != j && i != j) {
                                if (map[i][k] && map[k][j]) {
                                    map[i][j] = true;
                                }
                            }
                        }
                    }
                }
            }

            answer = 0;

            f();

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }


}