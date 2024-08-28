import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            init();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (c == 0) {
                    union(a, b);
                } else {
                    if (find(a) == find(b)) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static void init() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    // Path compression applied
    static int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);  // Path compression
        }
        return parent[a];
    }

    // Union by simple parent assignment
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            parent[rootB] = rootA;  // Union without rank
        }
    }
}
