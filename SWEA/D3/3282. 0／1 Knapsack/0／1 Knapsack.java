import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] stuff = new int[N][2];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                stuff[i][0] = Integer.parseInt(st.nextToken());
                stuff[i][1] = Integer.parseInt(st.nextToken());
            }

            int[] dp = new int[K + 1];

            for (int i = 0; i < N; i++) {
                for (int j = K; j >= stuff[i][0]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - stuff[i][0]] + stuff[i][1]);
                }
            }

            sb.append("#").append(t).append(" ").append(dp[K]).append("\n");
        }

        System.out.print(sb);
    }
}
