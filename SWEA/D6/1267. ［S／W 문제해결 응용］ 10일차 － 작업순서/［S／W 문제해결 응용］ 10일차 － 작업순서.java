import java.io.*;
import java.util.*;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;
 
        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine());
            sb = new StringBuilder();
 
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
 
            ArrayList<Integer>[] graph = new ArrayList[V + 1];
            for (int i = 0; i <= V; i++) {
                graph[i] = new ArrayList<>();
            }
 
            int[] indegree = new int[V + 1];
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                indegree[b]++;
            }
 
            Queue<Integer> q = new LinkedList<>();
 
            for (int i = 1; i <= V; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }
 
            sb.append("#").append(t);
 
            while (!q.isEmpty()) {
                int front = q.poll();
 
                sb.append(" ").append(front);
 
                for (int next : graph[front]) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        q.add(next);
                    }
                }
            }
            System.out.println(sb);
        }
    }
}