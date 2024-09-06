import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
 
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
 
            int[][] map = new int[N + 1][N + 1];
            int[] inDegree = new int[N + 1];
            int[] outDegree = new int[N + 1];
 
            for (int i = 0; i < M; i++) {
 
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
 
                map[a][b] = 1;
                map[b][a] = -1;
 
                inDegree[b]++;
                outDegree[a]++;
            }
 
            Queue<Integer> q1 = new LinkedList<>();
            Queue<Integer> q2 = new LinkedList<>();
 
            BitSet[] set1 = new BitSet[N + 1];
            BitSet[] set2 = new BitSet[N + 1];
 
            for (int i = 1; i <= N; i++) {
                if (inDegree[i] == 0) {
                    q1.offer(i);
                }
 
                if (outDegree[i] == 0) {
                    q2.offer(i);
                }
 
                set1[i] = new BitSet(N + 1);
                set1[i].set(i);
 
                set2[i] = new BitSet(N + 1);
                set2[i].set(i);
            }
 
            while (!q1.isEmpty()) {
                int now = q1.poll();
 
                for (int next = 1; next <= N; next++) {
                    if (map[now][next] == 1) {
                        if (--inDegree[next] == 0) {
                            q1.offer(next);
                        }
                        set1[next].or(set1[now]);
                    }
                }
            }
 
            while (!q2.isEmpty()) {
                int now = q2.poll();
 
                for (int next = 1; next <= N; next++) {
                    if (map[now][next] == -1) {
                        if (--outDegree[next] == 0) {
                            q2.offer(next);
                        }
                        set2[next].or(set2[now]);
                    }
                }
            }
 
            int answer = 0;
 
            for (int i = 1; i <= N; i++) {
                if (set1[i].cardinality() + set2[i].cardinality() == N + 1) {
                    answer++;
                }
            }
 
            sb.append("#").append(t).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }
}