import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    private static int[] arr;
    private static int[][] dp;
    private static int bit; // Declare bit as a global variable

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            arr = new int[N];

            int sum = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum += arr[i];
            }

            dp = new int[sum + 1][1 << N];
            bit = 0; // Initialize bit for each test case

            int answer = dfs(0, 0, 0);

            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    private static int dfs(int depth, int left, int right) {
        if (left < right) {
            return 0;
        }

        if (depth == N) {
            return dp[left][bit] = 1;
        }
        
        if (dp[left][bit] != 0) {
            return dp[left][bit];
        }


        int result = 0;

        for (int i = 0; i < N; i++) {
            if ((bit & (1 << i)) == 0) { // i 번째 추를 아직 안올림
                bit |= (1 << i); // i 번째 추 사용
                result += dfs(depth + 1, left + arr[i], right);
                result += dfs(depth + 1, left, right + arr[i]);
                bit &= ~(1 << i); // i 번째 추 내림
            }
        }
        return dp[left][bit] = result;
    }

}
