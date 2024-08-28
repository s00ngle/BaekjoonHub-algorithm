import java.io.*;
import java.sql.Array;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[101];

            HashSet<Integer>[] graph = new HashSet[101];
            for (int i = 1; i < 101; i++) {
                graph[i] = new HashSet<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
            }

            int answer = 0;
            int max = 0;
            visited[start] = true;

            Queue<Integer> q = new LinkedList<>();
            visited[start] = true;
            q.add(start);
            q.add(0);

            while (!q.isEmpty()) {
                int now = q.poll();
                int dist = q.poll();

                if (dist > max) {
                    max = dist;
                    answer = now;
                }
                if (dist == max) {
                    answer = Math.max(answer, now);
                }

                for (int next : graph[now]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                        q.add(dist + 1);
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }
}