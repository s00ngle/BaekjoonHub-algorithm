
import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static List<Node>[] list;
    static boolean[] visited;
    static int max;
    static int far;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, weight));
            list[to].add(new Node(from, weight));
        }

        max = 0;
        far = 0;
        visited = new boolean[N + 1];
        dfs(1, 0);

        max = 0;
        visited = new boolean[N + 1];
        dfs(far, 0);
        System.out.println(max);

    }

    static void dfs(int now, int sum) {
        visited[now] = true;
        if (sum > max) {
            max = sum;
            far = now;
        }

        for (Node node : list[now]) {
            if (!visited[node.to]) {
                dfs(node.to, sum + node.weight);
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
