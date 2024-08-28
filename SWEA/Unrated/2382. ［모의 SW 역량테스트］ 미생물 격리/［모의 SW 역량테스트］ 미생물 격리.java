import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, K;

    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    static List<V> list;
    static int[][] map;

    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                list.add(new V(r, c, m, d));
            }

            for (int i = 0; i < M; i++) {
                list.sort((o1, o2) -> (o2.m - o1.m));
                map = new int[N][N];

                ArrayList<V> toRemove = new ArrayList<>();

                for (V v : list) {
                    int dir = v.d;
                    int nr = v.r + dx[dir];
                    int nc = v.c + dy[dir];

                    if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
                        v.m /= 2;
                        if (dir == 1) {
                            v.d = 2;
                        } else if (dir == 2) {
                            v.d = 1;
                        } else if (dir == 3) {
                            v.d = 4;
                        } else if (dir == 4) {
                            v.d = 3;
                        }
                    }

                    v.r = nr;
                    v.c = nc;

                    if (map[nr][nc] == 0) {
                        map[nr][nc] = v.m;
                    } else {
                        map[nr][nc] += v.m;
                        toRemove.add(v);
                    }
                }

                list.removeAll(toRemove);

                for (V v : list) {
                    if (v.m != map[v.r][v.c]) {
                        v.m = map[v.r][v.c];
                    }
                }
            }

            answer = 0;
            for (V v : list) {
                answer += v.m;
            }

            sb.append("#").append(t).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }

    static class V {
        int r;
        int c;
        int m;
        int d;

        public V(int r, int c, int m, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.d = d;
        }
    }
}