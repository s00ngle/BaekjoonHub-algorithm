import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int n;
    static int m;
    static int[][] map;
    static Node[] cells;
    static int count;
 
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            map = new int[n + 2 * k][m + 2 * k];
            cells = new Node[11];
            count = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    map[i + k][j + k] = x;
                    if (x > 0) {
                        cells[x] = new Node(i + k, j + k, 2 * x, cells[x]);
                        count++;
                    }
                }
            }
            result(k);
            sb.append('#').append(t).append(' ').append(count).append('\n');
        }
        System.out.println(sb);
    }
 
    private static void result(int k) {
        for (int t = 0; t < k; t++) {
            for (int x = 10; x > 0; x--) {
                for (Node temp = cells[x]; temp != null; temp = temp.next) {
                    if (temp.state == 0) break; // 죽은 상태
                    temp.state--;
                    if (temp.state >= x) { // 비활성 상태
                        continue;
                    }
                    // 활성 상태
                    if (temp.state == 0) count--; // 끝나고 죽으면 count 감소
                    for (int d = 0; d < 4; d++) {
                        int nr = temp.r + dr[d];
                        int nc = temp.c + dc[d];
                        if (map[nr][nc] == 0) {
                            map[nr][nc] = x;
                            cells[x] = new Node(nr, nc, 2 * x, cells[x]);
                            count++;
                        }
                    }
                }
            }
        }
    }
     
    static class Node {
        int r;
        int c;
        int state;
        Node next;
         
        public Node(int r, int c, int state, Node next) {
            this.r = r;
            this.c = c;
            this.state = state;
            this.next = next;
        }
    }
}