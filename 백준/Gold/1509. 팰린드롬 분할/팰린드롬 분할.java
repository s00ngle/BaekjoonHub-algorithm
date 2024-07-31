import java.util.*;
import java.io.*;

public class Main {

    static boolean[][] isPalindrome;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int n = str.length();

        isPalindrome = new boolean[n][n];
        checkPalindrome(str);

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        System.out.println(dp[n]);
    }

    static void checkPalindrome(String str) {
        int n = str.length();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int start = i;
                int end = j;

                boolean flag = true;
                while(start <= end) {
                    if (str.charAt(start) != str.charAt(end)) {
                        flag = false;
                        break;
                    }
                    start++;
                    end--;
                }
                if (flag) {
                    isPalindrome[i][j] = true;
                }
            }
        }
    }
}
