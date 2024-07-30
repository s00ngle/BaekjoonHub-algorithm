import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static List<Node>[] list;
    static boolean[] visited;
    static int max;
    static int far;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) break;
                int w = Integer.parseInt(st.nextToken());
                list[m].add(new Node(v, w));
                list[v].add(new Node(m, w));
            }
        }

        max = 0;
        far = 0;
        visited = new boolean[N + 1];
        dfs(1, 1, 0);

        max = 0;
        visited = new boolean[N + 1];
        dfs(far, far, 0);
        System.out.println(max);

    }

    static void dfs(int start, int now, int sum) {
        visited[now] = true;
        if (sum > max) {
            max = sum;
            far = now;
        }

        for (Node node : list[now]) {
            if (!visited[node.to]) {
                dfs(start, node.to, sum + node.weight);
            }
        }
    }

    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + to + ", " + weight + ")";
        }
    }
}
