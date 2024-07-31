import java.util.*;
import java.io.*;

public class Main {

    static int V, E;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt((st.nextToken()));
            graph[start].add(new Edge(end, weight));
            graph[end].add(new Edge(start, weight));
        }

        System.out.println(prim(1, 0));
    }

    static int prim(int start, int weight) {
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);

        pq.add(new Edge(start, weight));

        int result = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if(visited[now.to]) {
                continue;
            }

            visited[now.to] = true;
            result += now.weight;

            for (Edge e: graph[now.to]) {
                if (!visited[e.to]) {
                    pq.add(e);
                }
            }
        }
        return result;
    }

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + to + ", " + weight + ")";
        }
    }
}
