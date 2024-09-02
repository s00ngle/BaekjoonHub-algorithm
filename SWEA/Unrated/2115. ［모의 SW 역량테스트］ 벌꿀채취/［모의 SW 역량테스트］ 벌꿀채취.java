import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, M, C;
    static int[][] map = new int[10][10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            for (int i = 0; i < N * N; i++) {
                for (int j = i + M; j < N * N; j++) {

                    int r1 = i / N;
                    int c1 = i % N;

                    int r2 = j / N;
                    int c2 = j % N;

                    if (c1 > N - M || c2 > N - M) continue;

                    int[] dp1 = new int[C + 1];
                    int[] dp2 = new int[C + 1];

                    for (int k = 0; k < M; k++) {
                        int num1 = map[r1][c1 + k];
                        int square1 = num1 * num1;

                        int num2 = map[r2][c2 + k];
                        int square2 = num2 * num2;

                        // 현재 숫자를 포함할 수 있는 최대 합 j부터 0까지
                        for (int l = C; l >= num1; l--) {
                            dp1[l] = Math.max(dp1[l], dp1[l - num1] + square1);
                        }
                        for (int l = C; l >= num2; l--) {
                            dp2[l] = Math.max(dp2[l], dp2[l - num2] + square2);
                        }
                    }
                    int sum = dp1[C] + dp2[C];
                    if (sum > answer) {
                        answer = sum;
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }
}
