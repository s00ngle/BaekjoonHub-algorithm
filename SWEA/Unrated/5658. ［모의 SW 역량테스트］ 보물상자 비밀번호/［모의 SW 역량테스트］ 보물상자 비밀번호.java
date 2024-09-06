import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int K;
    static int side;
    static int answer;
    static String table;

    static Set<Integer> set;

    public static void main(String[] args) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            table = br.readLine();
            table += table;

            side = N / 4; // 테이블 한 변당 숫자 개수
            set = new HashSet<>();

            for (int i = 0; i < side; i++) {
                set.add(Integer.parseInt(table.substring(i, i + side), 16));
                set.add(Integer.parseInt(table.substring(i + side, i +  2 * side), 16));
                set.add(Integer.parseInt(table.substring(i + 2 * side, i + 3 * side), 16));
                set.add(Integer.parseInt(table.substring(i + 3 * side, i + 4 * side), 16));
            }

            List<Integer> list = new ArrayList<>(set);
            Collections.sort(list, Comparator.reverseOrder());
            answer = list.get(K - 1);

            sb.append('#').append(t).append(' ').append(answer).append('\n');
        }

        System.out.print(sb);
    }
}
