import java.util.*;
import java.io.*;

public class Main {

    static int V, E;
    static Edge[] graph;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new Edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt((st.nextToken()));
            graph[i] = new Edge(start, end, weight);
        }

        System.out.println(kruskal());
    }

    static int kruskal() {
        Arrays.sort(graph, (o1, o2) -> o1.weight - o2.weight);

        parent = new int[V + 1];
        for (int i = 1 ; i <= V; i++) {
            parent[i] = i;
        }

        int result = 0;
        for (Edge e: graph) {
            if (find(e.start) != find(e.end)) {
                union(e.start, e.end);
                result += e.weight;
            }
        }
        return result;
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parent[b] = a;
        }
        else {
            parent[a] = b;
        }
    }

    static class Edge {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + start + ", " + end + ", " + weight + ")";
        }
    }
}
