import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int answer;

    static Map<String, Integer> dp;

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            char[] str = st.nextToken().toCharArray();
            N = Integer.parseInt(st.nextToken());

            answer = 0;
            dp = new HashMap<>();

            dfs(0, str);

            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int depth, char[] str) {

        String strKey = new String(str);

        if (depth == N) {
            int num = Integer.parseInt(strKey);
            answer = Math.max(answer, num);
            return;
        }

        if (dp.containsKey(strKey)) {
            return;
        }

        dp.put(strKey, depth);

        for (int i = 0; i < str.length - 1; i++) {
            for (int j = i + 1; j < str.length; j++) {
                if (str[i] != str[j]) {
                    char[] tempStr = str.clone();

                    char temp = tempStr[i];
                    tempStr[i] = tempStr[j];
                    tempStr[j] = temp;

                    dfs(depth + 1, tempStr);
                }
            }
        }
    }
}
