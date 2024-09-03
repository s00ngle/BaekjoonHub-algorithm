import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int N, M;
    static int answer;
 
    static class Point {
        int x;
        int y;
 
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
 
    static ArrayList<Point> list;
 
    public static void f(int x, int y) {
        int[] costSum = new int[2 * N];
        for (Point p : list) {
            costSum[Math.abs(x - p.x) + Math.abs(y - p.y)]++;
        }
 
        for (int i = 0; i < 2 * N - 1; i++) {
            costSum[i + 1] += costSum[i];
            if (2 * i * (i + 1) + 1 <= costSum[i] * M) {
                answer = Math.max(answer, costSum[i]);
            }
        }
    }
 
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(bf.readLine());
 
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
 
            list = new ArrayList<>();
 
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    if (Integer.parseInt(st.nextToken()) == 1) {
                        list.add(new Point(i, j));
                    }
                }
            }
 
            answer = 0;
 
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    f(i, j);
                }
            }
 
            sb.append("#").append(t).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }
 
}