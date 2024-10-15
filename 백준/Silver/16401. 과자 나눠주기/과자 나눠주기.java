import java.io.*;
import java.util.*;

public class Main {

    static int M;
    static int N;
    static int[] cookies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cookies = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cookies[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cookies);

        int answer = 0;
        int start = 1;
        int end = cookies[N - 1];

        while (true) {
//            System.out.println(start + ", " + end);

            if (start > end) {
                break;
            }

            int mid = (start + end) / 2;
//            System.out.println("mid : " + mid);

            int cnt = 0;
            boolean flag = false;
            for (int i = N - 1; i >= 0; i--) {
                cnt += cookies[i] / mid;
                if (cnt >= M) {
//                    System.out.println(mid);
                    answer = mid;
                    flag = true;
                    break;
                }
            }

            if (flag) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
